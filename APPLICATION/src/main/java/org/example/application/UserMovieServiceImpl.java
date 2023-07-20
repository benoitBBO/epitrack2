package org.example.application;

import org.example.domaine.exceptions.ResourceAlreadyExistsException;
import org.example.domaine.exceptions.ResourceNotFoundException;
import org.example.domaine.userselection.UserMovie;
import org.example.infrastructure.repository.IUserMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserMovieServiceImpl implements IUserMovieService {
    @Autowired
    IUserMovieRepository userMovieRepository;
    @Autowired
    IMovieService movieService;
    @Override
    public void create(UserMovie userMovie) {
        Optional<UserMovie> userMovieOptional = userMovieRepository.findByUserIdAndMovieId(userMovie.getUser().getId(), userMovie.getMovie().getId());
        if (userMovieOptional.isPresent()){
            throw new ResourceAlreadyExistsException();
        }
        userMovieRepository.save(userMovie);
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
    public void delete(Long id) {
        userMovieRepository.deleteById(id);
    }


    @Override
    public List<UserMovie> findFirst4ByUserIdOrderByUserRatingDesc(Long userId) {
        return userMovieRepository.findFirst4ByUserIdOrderByUserRatingDesc(userId);
    }

    @Override
    public List<UserMovie> findAllByUserIdOrderByUserRatingDesc(Long userId) {
        return userMovieRepository.findAllByUserIdOrderByUserRatingDesc(userId);
    }
    @Override
    public void updateUserRating(Long userId, Long videoId, Integer rating) {
        Optional<UserMovie> userMovieOptional = userMovieRepository.findByUserIdAndMovieId(userId, videoId);
        if (userMovieOptional.isEmpty()){
            throw new ResourceNotFoundException();
        }
        else {
            //mise à jour de la note utilisateur (rating)
            UserMovie userMovie = userMovieOptional.get();
            userMovie.setUserRating(rating);
            userMovieRepository.save(userMovie);

            //recalcul note global du film au catalogue
            movieService.updateMovieTotalRating(userMovie.getMovie(), rating);
        }
    }

    @Override
    public void updateUserMovieStatus(Long userMovieId, String status) {
        Optional<UserMovie> userMovieOptional = userMovieRepository.findById(userMovieId);
        if (userMovieOptional.isEmpty()){
            throw new ResourceNotFoundException();
        }
        else {
            UserMovie userMovie = userMovieOptional.get();
            userMovie.setStatus(status);
            userMovieRepository.save(userMovie);
        }
    }
}
