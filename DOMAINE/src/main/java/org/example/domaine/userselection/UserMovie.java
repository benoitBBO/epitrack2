package org.example.domaine.userselection;

import org.example.domaine.catalog.Movie;
import org.example.domaine.user.UserProfile;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class UserMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Movie movie;
    private String status;
    private Integer userRating;
    private LocalDate statusDate;
    @ManyToOne
    private UserProfile user;

    public UserMovie(Movie movie, String status, Integer userRating, LocalDate statusDate, UserProfile user) {
        this.movie = movie;
        this.status = status;
        this.userRating = userRating;
        this.statusDate = statusDate;
        this.user = user;
    }

    public UserMovie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
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

    public UserProfile getUser() {
        return user;
    }

    public void setUser(UserProfile user) {
        this.user = user;
    }
}
