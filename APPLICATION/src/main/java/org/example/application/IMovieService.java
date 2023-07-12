package org.example.application;

import org.example.domaine.catalog.Movie;

import java.util.List;

public interface IMovieService {
    void create(Movie movie);
    Movie findById(Long id);
    List<Movie> findAll();
    Movie update(Movie movie);
    void delete(Long id);
    List<Movie> findFirst4ByOrderByTotalRatingDesc();
}
