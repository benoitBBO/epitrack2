package org.example.exposition.season.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.exposition.episode.dto.EpisodeDetailDto;
import org.example.exposition.episode.dto.EpisodeMinDto;

import java.time.LocalDate;
import java.util.List;

public class SeasonDetailDto {
    private SeasonDetailWithoutEpisodeDto season;
    private List<EpisodeDetailDto> episodes;

    public SeasonDetailDto() {
    }

    public SeasonDetailDto(SeasonDetailWithoutEpisodeDto season, List<EpisodeDetailDto> episodes) {
        this.season = season;
        this.episodes = episodes;
    }

    public SeasonDetailWithoutEpisodeDto getSeason() {
        return season;
    }

    public void setSeason(SeasonDetailWithoutEpisodeDto season) {
        this.season = season;
    }

    public List<EpisodeDetailDto> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<EpisodeDetailDto> episodes) {
        this.episodes = episodes;
    }
}
