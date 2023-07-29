package org.example.domaine.userselection;

public class UserRating {
    Integer previousRating;

    Integer newRating;

    Long userId;

    Long userMovieId;

    Long movieId;

    public UserRating() {
    }

    public UserRating(Integer previousRating, Integer newRating, Long userId, Long userMovieId, Long movieId) {
        this.previousRating = previousRating;
        this.newRating = newRating;
        this.userId = userId;
        this.userMovieId = userMovieId;
        this.movieId = movieId;
    }

    public Integer getPreviousRating() {
        return previousRating;
    }

    public void setPreviousRating(Integer previousRating) {
        this.previousRating = previousRating;
    }

    public Integer getNewRating() {
        return newRating;
    }

    public void setNewRating(Integer newRating) {
        this.newRating = newRating;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserMovieId() {
        return userMovieId;
    }

    public void setUserMovieId(Long userMovieId) {
        this.userMovieId = userMovieId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}
