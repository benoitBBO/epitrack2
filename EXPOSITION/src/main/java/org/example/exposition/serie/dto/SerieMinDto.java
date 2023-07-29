package org.example.exposition.serie.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;


public class SerieMinDto {
    private Long id;
    private String title;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;
    private Integer totalRating;
    private String imageLandscapeUrl;

    public SerieMinDto(Long id, String title, LocalDate releaseDate, Integer totalRating, String imageLandscapeUrl) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.totalRating = totalRating;
        this.imageLandscapeUrl = imageLandscapeUrl;
    }

    public SerieMinDto() {
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(Integer totalRating) {
        this.totalRating = totalRating;
    }

    public String getImageLandscapeUrl() {
        return imageLandscapeUrl;
    }

    public void setImageLandscapeUrl(String imageLandscapeUrl) {
        this.imageLandscapeUrl = imageLandscapeUrl;
    }
}
