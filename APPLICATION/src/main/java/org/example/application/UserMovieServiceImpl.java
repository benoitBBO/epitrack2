package org.example.application;

import org.example.application.util.CalculServiceImpl;
import org.example.domaine.exceptions.ResourceAlreadyExistsException;
import org.example.domaine.exceptions.ResourceNotFoundException;
import org.example.domaine.userselection.UserMovie;
import org.example.domaine.userselection.UserRating;
import org.example.infrastructure.repository.IMovieRepository;
import org.example.infrastructure.repository.IUserMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class UserMovieServiceImpl implements IUserMovieService {
    @Autowired
    IUserMovieRepository userMovieRepository;

    @Autowired
    IMovieRepository movieRepository;
    @Autowired
    IMovieService movieService;

    @Autowired
    CalculServiceImpl calculService;
    @Override
    public List<UserMovie> create(UserMovie userMovie) {
        Optional<UserMovie> userMovieOptional = userMovieRepository.findByUserIdAndMovieId(userMovie.getUser().getId(), userMovie.getMovie().getId());
        if (userMovieOptional.isPresent()){
            throw new ResourceAlreadyExistsException();
        }
        userMovieRepository.save(userMovie);

        return userMovieRepository.findAllByUserIdOrderByUserRatingDesc(userMovie.getUser().getId());
    }

    @Override
    public UserMovie findById(Long id) {
        Optional<UserMovie> optionalTask = userMovieRepository.findById(id);
        if (!optionalTask.isPresent()) {
            throw new EntityNotFoundException("Le film du user avec l'id "+id+" est introuvable");
        }
        return optionalTask.get();
    }

    @Override
    public List<UserMovie> findAll() {
        return userMovieRepository.findAll();
    }

    @Override
    public UserMovie update(UserMovie userMovie) {
        return userMovieRepository.save(userMovie);
    }

    @Override
    public List<UserMovie> delete(Long movieId, Long userId) {
        Optional<UserMovie> userMovieOptional = userMovieRepository.findByUserIdAndMovieId(userId, movieId);
        if (!userMovieOptional.isPresent()) {
            throw new EntityNotFoundException("Le film du user avec l'id "+movieId+" est introuvable");
        }
        userMovieRepository.deleteById(userMovieOptional.get().getId());

        return userMovieRepository.findAllByUserIdOrderByUserRatingDesc(userMovieOptional.get().getUser().getId());
    }


    @Override
    public List<UserMovie> findFirst4ByUserIdOrderByUserRatingDesc(Long userId) {
        return userMovieRepository.findFirst4ByUserIdOrderByUserRatingDesc(userId);
    }

    @Override
    public List<UserMovie> findAllByUserIdOrderByUserRatingDesc(Long userId) {
        return userMovieRepository.findAllByUserIdOrderByUserRatingDesc(userId);
    }

    @Transactional
    @Override
    public void updateUserRating(UserRating userRating) {
        //Update UserMovieRating
        Optional<UserMovie> userMovieOptional = userMovieRepository.findByUserIdAndMovieId(userRating.getUserId(), userRating.getMovieId());
        if (userMovieOptional.isEmpty()){
            throw new ResourceNotFoundException();
        }
        else {
            //mise à jour de la note utilisateur (rating)
            userMovieRepository.updateUserMovieRating(userRating.getUserMovieId(), userRating.getNewRating());

            //Update TotalRating et VoteCount
            UserMovie userMovie = userMovieOptional.get();
            movieService.updateMovieTotalRatingAndVoteCount(userMovie.getMovie(), userRating);
        }

    }
    @Override
    public UserMovie updateUserMovieStatus(Long userMovieId, String status) {
        Optional<UserMovie> userMovieOptional = userMovieRepository.findById(userMovieId);
        if (userMovieOptional.isEmpty()){
            throw new ResourceNotFoundException();
        }
        else {
            UserMovie userMovie = userMovieOptional.get();
            userMovie.setStatus(status);
            userMovie.setStatusDate(LocalDate.now());
            return userMovieRepository.save(userMovie);
        }
    }
}
