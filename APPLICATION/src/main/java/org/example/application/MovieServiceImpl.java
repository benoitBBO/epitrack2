package org.example.application;

import org.example.application.util.ICalculService;
import org.example.domaine.catalog.Movie;
import org.example.domaine.userselection.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.infrastructure.repository.IMovieRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements IMovieService {
    @Autowired
    IMovieRepository movieRepository;

    @Autowired
    ICalculService calculService;
    @Override
    @Transactional
    public Long create(Movie movie) {
        return movieRepository.save(movie).getId();
    }
    @Override
    public Movie findById(Long id) {
        Optional<Movie> optionalItem = movieRepository.findById(id);
        if (!optionalItem.isPresent()) {
            throw new EntityNotFoundException("Le film avec l'id "+id+" est introuvable");
        }
        return optionalItem.get();
    }
    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }
    @Override
    public Movie update(Movie movie) {
        return movieRepository.save(movie);
    }
    @Override
    public void delete(Long id) {
        movieRepository.deleteById(id);
    }
    @Override
    public List<Movie> findFirst4ByOrderByTotalRatingDesc() {
        return movieRepository.findFirst4ByOrderByTotalRatingDesc();
    }
    @Override
    public List<Movie> findByTitleContains(String title){
        return movieRepository.findByTitleContainsIgnoreCase(title);
    }

    @Override
    public void updateMovieTotalRatingAndVoteCount(Movie movie, UserRating userRating) {
        movieRepository.updateMovieRating(movie.getId(), calculService.computeAverage(movie.getTotalRating(), movie.getVoteCount(), userRating.getNewRating(), userRating.getPreviousRating()));
        movieRepository.updateMovieVotes(movie.getId(), userRating.getPreviousRating() == null ? movie.getVoteCount() + 1 : movie.getVoteCount()); //Si nouvelle notation, le nombre total de votes est incrémenté de 1, sinon il reste le même
    }
}
