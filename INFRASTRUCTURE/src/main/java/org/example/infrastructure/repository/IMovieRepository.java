package org.example.infrastructure.repository;

import org.example.domaine.catalog.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findFirst4ByOrderByTotalRatingDesc();
}
