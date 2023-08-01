package org.example.application;

import org.example.application.util.ICalculService;
import org.example.domaine.catalog.Episode;
import org.example.domaine.catalog.Movie;
import org.example.domaine.catalog.Season;
import org.example.domaine.catalog.Serie;
import org.example.domaine.userselection.UserEpisode;
import org.example.domaine.userselection.UserRating;
import org.example.domaine.userselection.UserSeason;
import org.example.domaine.userselection.UserSerie;
import org.example.infrastructure.repository.ISerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieServiceImpl implements ISerieService {
    @Autowired
    ISerieRepository serieRepository;

    @Autowired
    ICalculService calculService;

    @Override
    public Long create(Serie newSerie) {
        return serieRepository.save(newSerie).getId();
    }

    @Override
    public Serie findById(Long id) {
        Optional<Serie> serieOptional = serieRepository.findById(id);
        if (!serieOptional.isPresent()){
            throw new EntityNotFoundException("La serie est instrouvable");
        }

        Serie serie = serieOptional.get();
        //trier la serie par numéro de saison et numéro d'épisode
        for (Season season : serie.getSeasons()) {
            List<Episode> episodeSortedList = sortByEpisodeNumber(season.getEpisodes());
            season.setEpisodes(episodeSortedList);
        }
        List<Season> seasonSortedList = sortBySeasonNumber(serie.getSeasons());
        serie.setSeasons(seasonSortedList);

        return serie;
    }
    List<Season> sortBySeasonNumber (List<Season> seasonList){
        return seasonList.stream()
                .sorted(Comparator.comparingInt( (season) -> season.getSeasonNumber()))
                .collect(Collectors.toList());
    }
    List<Episode> sortByEpisodeNumber (List<Episode> episodeList){
        return episodeList.stream()
                .sorted(Comparator.comparingInt( (episode) -> episode.getEpisodeNumber()))
                .collect(Collectors.toList());
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
