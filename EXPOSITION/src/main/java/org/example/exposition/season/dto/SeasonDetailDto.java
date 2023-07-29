package org.example.exposition.season.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.exposition.episode.dto.EpisodeDetailDto;
import org.example.exposition.episode.dto.EpisodeMinDto;

import java.time.LocalDate;
import java.util.List;

public class SeasonDetailDto {
    private Long id;
    private String title;
    private Integer seasonNumber;
    private String overview;
    private String imageUrl;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate airDate;
    private List<EpisodeDetailDto> episodes;

    public SeasonDetailDto() {
    }

    public SeasonDetailDto(Long id, String title, Integer seasonNumber, String overview, String imageUrl, LocalDate airDate, List<EpisodeDetailDto> episodes) {
        this.id = id;
        this.title = title;
        this.seasonNumber = seasonNumber;
        this.overview = overview;
        this.imageUrl = imageUrl;
        this.airDate = airDate;
        this.episodes = episodes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDate getAirDate() {
        return airDate;
    }

    public void setAirDate(LocalDate airDate) {
        this.airDate = airDate;
    }

    public List<EpisodeDetailDto> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<EpisodeDetailDto> episodes) {
        this.episodes = episodes;
    }
}
