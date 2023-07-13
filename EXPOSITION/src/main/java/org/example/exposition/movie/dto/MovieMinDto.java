package org.example.exposition.movie.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class MovieMinDto {
    private Long id;
    private String title;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;
    private Integer totalRating;
    private String imageLandscapeUrl;

    public MovieMinDto(Long id, String title, LocalDate releaseDate, Integer totalRating, String imageLandscapeUrl) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.totalRating = totalRating;
        this.imageLandscapeUrl = imageLandscapeUrl;
    }

    public MovieMinDto() {
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
