package org.example.exposition.usermovie.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.exposition.movie.dto.MovieDetailDto;

import java.time.LocalDate;

public class UserMovieDetailDto {
    private Long id;
    private MovieDetailDto movie;
    private String status;
    private Integer userRating;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate statusDate;

    public UserMovieDetailDto(Long id, MovieDetailDto movie, String status, Integer userRating, LocalDate statusDate) {
        this.id = id;
        this.movie = movie;
        this.status = status;
        this.userRating = userRating;
        this.statusDate = statusDate;
    }

    public UserMovieDetailDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MovieDetailDto getMovie() {
        return movie;
    }

    public void setMovie(MovieDetailDto movie) {
        this.movie = movie;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUserRating() {
        return userRating;
    }

    public void setUserRating(Integer userRating) {
        this.userRating = userRating;
    }

    public LocalDate getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(LocalDate statusDate) {
        this.statusDate = statusDate;
    }
}
