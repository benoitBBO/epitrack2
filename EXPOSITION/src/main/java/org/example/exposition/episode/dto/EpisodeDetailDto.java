package org.example.exposition.episode.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class EpisodeDetailDto {
    private Long id;
    private String title;
    private Integer episodeNumber;
    private String overview;
    private String imageUrl;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate airDate;

    public EpisodeDetailDto(Long id, String title, Integer episodeNumber, String overview, String imageUrl, LocalDate airDate) {
        this.id = id;
        this.title = title;
        this.episodeNumber = episodeNumber;
        this.overview = overview;
        this.imageUrl = imageUrl;
        this.airDate = airDate;
    }

    public EpisodeDetailDto() {
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

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
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
}
