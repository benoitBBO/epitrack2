package org.example.exposition.tmdb.dto;

import java.time.LocalDate;
import java.util.ArrayList;

public class SeasonsTmdbDto {
    public String _id;
    public LocalDate air_date;
    public ArrayList<EpisodeTmdbDto> episodes;
    public String name;
    public String overview;
    public int id;
    public String poster_path;
    public int season_number;
    public CreditsDto credits;

    public SeasonsTmdbDto() {
    }

    public SeasonsTmdbDto(String _id, LocalDate air_date, ArrayList<EpisodeTmdbDto> episodes, String name, String overview, int id, String poster_path, int season_number, CreditsDto credits) {
        this._id = _id;
        this.air_date = air_date;
        this.episodes = episodes;
        this.name = name;
        this.overview = overview;
        this.id = id;
        this.poster_path = poster_path;
        this.season_number = season_number;
        this.credits = credits;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public LocalDate getAir_date() {
        return air_date;
    }

    public void setAir_date(LocalDate air_date) {
        this.air_date = air_date;
    }

    public ArrayList<EpisodeTmdbDto> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(ArrayList<EpisodeTmdbDto> episodes) {
        this.episodes = episodes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public int getSeason_number() {
        return season_number;
    }

    public void setSeason_number(int season_number) {
        this.season_number = season_number;
    }

    public CreditsDto getCredits() {
        return credits;
    }

    public void setCredits(CreditsDto credits) {
        this.credits = credits;
    }
}
