package org.example.infrastructure.repository;

import org.example.domaine.userselection.UserMovie;
import org.example.domaine.userselection.UserSerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserSerieRepository extends JpaRepository<UserSerie, Long> {
    Optional<UserSerie> findByUserIdAndSerieId(Long userId, Long serieId);
}
