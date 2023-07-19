package org.example.infrastructure.repository;

import org.example.domaine.userselection.UserMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserMovieRepository extends JpaRepository<UserMovie, Long> {

    Optional<UserMovie> findByUserIdAndMovieId (Long userId, Long movieId);

}
