package org.example.domaine.catalog;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer episodeNumber;
    @Column(columnDefinition = "TEXT")
    private String overview;
    private String imageUrl;
    private LocalDate airDate;

    public Episode(String title, Integer episodeNumber, String overview, String imageUrl, LocalDate airDate) {
        this.title = title;
        this.episodeNumber = episodeNumber;
        this.overview = overview;
        this.imageUrl = imageUrl;
        this.airDate = airDate;
    }

    public Episode() {
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

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
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
