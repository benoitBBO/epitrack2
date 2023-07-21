package org.example.exposition.serie.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.domaine.catalog.Actor;
import org.example.domaine.catalog.Genre;
import org.example.exposition.season.dto.SeasonDetailDto;
import org.example.exposition.season.dto.SeasonMinDto;

import java.time.LocalDate;
import java.util.List;

public class SerieDetailDto {
    private SerieDetailWithoutSeasonDto serie;
    private List<SeasonDetailDto> seasons;

    public SerieDetailDto(SerieDetailWithoutSeasonDto serie, List<SeasonDetailDto> seasons) {
        this.serie = serie;
        this.seasons = seasons;
    }

    public SerieDetailDto() {
    }

    public SerieDetailWithoutSeasonDto getSerie() {
        return serie;
    }

    public void setSerie(SerieDetailWithoutSeasonDto serie) {
        this.serie = serie;
    }

    public List<SeasonDetailDto> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<SeasonDetailDto> seasons) {
        this.seasons = seasons;
    }
}
