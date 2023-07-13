package org.example.exposition.movie.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.domaine.catalog.Actor;
import org.example.domaine.catalog.Genre;

import java.time.LocalDate;
import java.util.List;

public class MovieDetailDto {

    private Long id;
    private String title;
    private String overview;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;
    private Integer totalRating;
    private Integer voteCount;
    private String imagePosterUrl;
    private String imageLandscapeUrl;
    private List<Genre> genres;
    private List<Actor> actors;
    private String imdbRef;

    public MovieDetailDto(Long id, String title, String overview, LocalDate releaseDate, Integer totalRating, Integer voteCount, String imagePosterUrl, String imageLandscapeUrl, List<Genre> genres, List<Actor> actors, String imdbRef) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.totalRating = totalRating;
        this.voteCount = voteCount;
        this.imagePosterUrl = imagePosterUrl;
        this.imageLandscapeUrl = imageLandscapeUrl;
        this.genres = genres;
        this.actors = actors;
        this.imdbRef = imdbRef;
    }

    public MovieDetailDto() {
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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
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

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public String getImagePosterUrl() {
        return imagePosterUrl;
    }

    public void setImagePosterUrl(String imagePosterUrl) {
        this.imagePosterUrl = imagePosterUrl;
    }

    public String getImageLandscapeUrl() {
        return imageLandscapeUrl;
    }

    public void setImageLandscapeUrl(String imageLandscapeUrl) {
        this.imageLandscapeUrl = imageLandscapeUrl;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public String getImdbRef() {
        return imdbRef;
    }

    public void setImdbRef(String imdbRef) {
        this.imdbRef = imdbRef;
    }
}