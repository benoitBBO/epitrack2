package org.example.domaine.userselection;

import org.example.domaine.catalog.Season;
import org.example.domaine.user.UserProfile;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class UserSeason {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Season season;
    @OneToMany(cascade = CascadeType.ALL)
    private List<UserEpisode> userEpisodes;
    private String status;
    private LocalDate statusDate;
    @ManyToOne
    private UserProfile user;

    public UserSeason(Season season, List<UserEpisode> userEpisodes, String status, LocalDate statusDate, UserProfile user) {
        this.season = season;
        this.userEpisodes = userEpisodes;
        this.status = status;
        this.statusDate = statusDate;
        this.user = user;
    }

    public UserSeason() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public List<UserEpisode> getUserEpisodes() {
        return userEpisodes;
    }

    public void setUserEpisodes(List<UserEpisode> userEpisodes) {
        this.userEpisodes = userEpisodes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
