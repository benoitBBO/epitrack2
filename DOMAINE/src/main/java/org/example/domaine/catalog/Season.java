package org.example.domaine.catalog;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer seasonNumber;
    @Column(columnDefinition = "TEXT")
    private String overview;
    private String imageUrl;
    private LocalDate airDate;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Episode> episodes;

    public Season(String title, Integer seasonNumber, String overview, String imageUrl, LocalDate airDate, List<Episode> episodes) {
        this.title = title;
        this.seasonNumber = seasonNumber;
        this.overview = overview;
        this.imageUrl = imageUrl;
        this.airDate = airDate;
        this.episodes = episodes;
    }

    public Season() {
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

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
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

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
}
