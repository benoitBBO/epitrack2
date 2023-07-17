package org.example.application;


import org.example.domaine.catalog.Movie;
import org.example.domaine.catalog.Serie;

import java.util.List;

public interface ISerieService {

    void create(Serie newSerie);

    Serie findById(Long id);
    List<Serie> findAll();
    void deleteSerie(Long id);

    void updateSerie(Serie updatedSerie);
    List<Serie> findFirst4ByOrderByTotalRatingDesc();
    List<Serie> findByTitleContains(String title);

}
