package org.example.infrastructure.repository;

import org.example.domaine.catalog.Movie;
import org.example.domaine.userselection.UserMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;

@Repository
public interface IUserMovieRepository extends JpaRepository<UserMovie, Long> {
    List<UserMovie> findAllByUserIdOrderByUserRatingDesc(Long userId);
    List<UserMovie> findFirst4ByUserIdOrderByUserRatingDesc(Long userId);
    Optional<UserMovie> findByUserIdAndMovieId (Long userId, Long movieId);
    @Modifying
    @Query("update UserMovie userMovie set userMovie.userRating = :newrating where userMovie.id = :userMovieId")
    void updateUserMovieRating(@Param(value = "userMovieId")Long userMovieId, @Param(value = "newrating")Integer newRating);
}
