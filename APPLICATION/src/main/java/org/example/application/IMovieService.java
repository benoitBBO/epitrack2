package org.example.application;

import org.example.domaine.catalog.Movie;
import org.example.domaine.userselection.UserRating;

import java.util.List;

public interface IMovieService {
    Long create(Movie movie);
    Movie findById(Long id);
    List<Movie> findAll();
    Movie update(Movie movie);
    void delete(Long id);
    List<Movie> findFirst4ByOrderByTotalRatingDesc();
    List<Movie> findByTitleContains(String title);

    void updateMovieTotalRatingAndVoteCount(Movie movie, UserRating userRating);
}
