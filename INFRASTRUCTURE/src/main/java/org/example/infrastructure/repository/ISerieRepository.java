package org.example.infrastructure.repository;

import org.example.domaine.catalog.Movie;
import org.example.domaine.catalog.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISerieRepository extends JpaRepository <Serie, Long> {
    List<Serie> findFirst4ByOrderByTotalRatingDesc();
    List<Serie> findByTitleContainsIgnoreCase(String title);
}
