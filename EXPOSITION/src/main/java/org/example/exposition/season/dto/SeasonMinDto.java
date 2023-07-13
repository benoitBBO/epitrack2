package org.example.exposition.season.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class SeasonMinDto {
    private Long id;
    private String title;
    private Integer seasonNumber;
    private String imageUrl;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate airDate;

    public SeasonMinDto() {
    }

    public SeasonMinDto(Long id, String title, Integer seasonNumber, String imageUrl, LocalDate airDate) {
        this.id = id;
        this.title = title;
        this.seasonNumber = seasonNumber;
        this.imageUrl = imageUrl;
        this.airDate = airDate;
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
