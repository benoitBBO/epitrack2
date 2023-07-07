package org.example.domaine.userselection;

import org.example.domaine.catalog.Serie;
import org.example.domaine.user.UserProfile;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class UserSerie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Serie serie;
    @OneToMany(cascade = CascadeType.ALL)
    private List<UserSeason> userSeasons;
    private String status;
    private Integer userRating;
    private LocalDate statusDate;
    @ManyToOne
    private UserProfile user;

    public UserSerie(Serie serie, List<UserSeason> userSeasons, String status, Integer userRating, LocalDate statusDate, UserProfile user) {
        this.serie = serie;
        this.userSeasons = userSeasons;
        this.status = status;
        this.userRating = userRating;
        this.statusDate = statusDate;
        this.user = user;
    }

    public UserSerie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public List<UserSeason> getUserSeasons() {
        return userSeasons;
    }

    public void setUserSeasons(List<UserSeason> userSeasons) {
        this.userSeasons = userSeasons;
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
