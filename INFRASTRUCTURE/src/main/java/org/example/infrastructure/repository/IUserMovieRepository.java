package org.example.infrastructure.repository;

import org.example.domaine.userselection.UserMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserMovieRepository extends JpaRepository<UserMovie, Long> {
}
