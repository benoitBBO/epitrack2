package org.example.application;

import org.example.domaine.catalog.Movie;
import org.example.domaine.catalog.Serie;
import org.example.infrastructure.repository.ISerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieServiceImpl implements ISerieService {
    @Autowired
    ISerieRepository repository;

    @Override
    public void create(Serie newSerie) {
        repository.save(newSerie);
    }

    @Override
    public Serie findById(Long id) {
        Optional<Serie> clientOptional = repository.findById(id);
        return clientOptional.orElse(null);
    }

    @Override
    public void deleteSerie(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void updateSerie(Serie updatedSerie) {
        repository.save(updatedSerie);
    }
    @Override
    public List<Serie> findFirst4ByOrderByTotalRatingDesc() {
        return repository.findFirst4ByOrderByTotalRatingDesc();
    }
}
