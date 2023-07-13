package org.example.exposition.episode.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class EpisodeMinDto {
    private Long id;
    private String title;
    private Integer episodeNumber;
    private String imageUrl;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate airDate;

    public EpisodeMinDto(Long id, String title, Integer episodeNumber, String imageUrl, LocalDate airDate) {
        this.id = id;
        this.title = title;
        this.episodeNumber = episodeNumber;
        this.imageUrl = imageUrl;
        this.airDate = airDate;
    }

    public EpisodeMinDto() {
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
