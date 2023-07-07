package org.example.domaine.userselection;

import org.example.domaine.catalog.Episode;
import org.example.domaine.user.UserProfile;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class UserEpisode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Episode episode;
    private String status;
    private LocalDate statusDate;
    @ManyToOne
    private UserProfile user;

    public UserEpisode(Episode episode, String status, LocalDate statusDate, UserProfile user) {
        this.episode = episode;
        this.status = status;
        this.statusDate = statusDate;
        this.user = user;
    }

    public UserEpisode() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
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
