package org.example.infrastructure.repository;

import org.example.domaine.catalog.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISerieRepository extends JpaRepository <Serie, Long> {
}
