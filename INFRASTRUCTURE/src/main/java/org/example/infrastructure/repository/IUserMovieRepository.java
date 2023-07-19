package org.example.infrastructure.repository;

import org.example.domaine.catalog.Movie;
import org.example.domaine.userselection.UserMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserMovieRepository extends JpaRepository<UserMovie, Long> {
    List<UserMovie> findAllByUserIdOrderByUserRatingDesc(Long userId);
    List<UserMovie> findFirst4ByUserIdOrderByUserRatingDesc(Long userId);
}
