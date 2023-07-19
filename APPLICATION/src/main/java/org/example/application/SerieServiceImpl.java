package org.example.application;

import org.example.domaine.catalog.Serie;
import org.example.infrastructure.repository.ISerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieServiceImpl implements ISerieService {
    @Autowired
    ISerieRepository serieRepository;

    @Override
    public void create(Serie newSerie) {
        serieRepository.save(newSerie);
    }

    @Override
    public Serie findById(Long id) {
        Optional<Serie> clientOptional = serieRepository.findById(id);
        return clientOptional.orElse(null);
    }
    @Override
    public List<Serie> findAll() {
        return serieRepository.findAll();
    }
    @Override
    public void deleteSerie(Long id) {
        serieRepository.deleteById(id);
    }

    @Override
    public void updateSerie(Serie updatedSerie) {
        serieRepository.save(updatedSerie);
    }
    @Override
    public List<Serie> findFirst4ByOrderByTotalRatingDesc() {
        return serieRepository.findFirst4ByOrderByTotalRatingDesc();
    }
    @Override
    public List<Serie> findByTitleContains(String title){
        return serieRepository.findByTitleContainsIgnoreCase(title);
    }

    @Override
    public void updateSerieTotalRating(Serie serie, Integer userRating) {
        serie.setTotalRating(serie.getTotalRating()+userRating);
        serie.setVoteCount(serie.getVoteCount()+1);
        serieRepository.save(serie);
    }
}
