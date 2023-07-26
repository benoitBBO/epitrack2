package org.example.application;

import org.example.application.util.ICalculService;
import org.example.domaine.catalog.Movie;
import org.example.domaine.catalog.Serie;
import org.example.domaine.userselection.UserRating;
import org.example.infrastructure.repository.ISerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieServiceImpl implements ISerieService {
    @Autowired
    ISerieRepository serieRepository;

    @Autowired
    ICalculService calculService;

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
    public void updateSerieTotalRatingAndVoteCount(Serie serie, UserRating userRating) {
        serieRepository.updateSerieRating(serie.getId(), calculService.computeAverage(serie.getTotalRating(), serie.getVoteCount(), userRating.getNewRating(), userRating.getPreviousRating()));
        serieRepository.updateSerieVotes(serie.getId(), userRating.getPreviousRating() == null ? serie.getVoteCount() + 1 : serie.getVoteCount()); //Si nouvelle notation, le nombre total de votes est incrémenté de 1, sinon il reste le même
    }
}
