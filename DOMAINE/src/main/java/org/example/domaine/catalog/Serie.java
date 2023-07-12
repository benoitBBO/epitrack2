package org.example.domaine.catalog;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Serie extends Video {

    @OneToMany(cascade = CascadeType.ALL)
    private List<Season> seasons;

    public Serie(String title, String overview, LocalDate releaseDate, Integer totalRating, Integer voteCount, String imagePosterUrl, String imageLandscapeUrl, List<Genre> genres, List<Actor> actors, String imdbRef) {
        super(title, overview, releaseDate, totalRating, voteCount, imagePosterUrl, imageLandscapeUrl, genres, actors, imdbRef);
    }

    public Serie() {
        super();
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

}
