package org.example.application;

import org.example.domaine.catalog.Serie;

public interface ISerieService {

    void create(Serie newSerie);

    Serie findById(Long id);

    void deleteSerie(Long id);

    void updateSerie(Serie updatedSerie);


}
