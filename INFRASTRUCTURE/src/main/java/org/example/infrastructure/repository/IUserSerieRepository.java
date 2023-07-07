package org.example.infrastructure.repository;

import org.example.domaine.userselection.UserMovie;
import org.example.domaine.userselection.UserSerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserSerieRepository extends JpaRepository<UserSerie, Long> {
}
