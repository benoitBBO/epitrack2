package org.example.exposition.tmdb.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TmdbDto {
//    public boolean adult;
    public String backdrop_path;
//    public BelongsToCollectionDto belongs_to_collection;
    public int budget;
    public ArrayList<GenreDto> genres;
//    public String homepage;
    public int id;
    public String imdb_id;
//    public String original_language;
//    public String original_title;
    public String overview;
//    public double popularity;
    public String poster_path;
//    public ArrayList<ProductionCompany> production_companies;
//    public ArrayList<ProductionCountry> production_countries;
    public LocalDate release_date; //Movie Specific
    public LocalDate first_air_date; //Series Specific
//    public int revenue;
//    public int runtime;
//    public ArrayList<SpokenLanguage> spoken_languages;
//    public String status;
//    public String tagline;
    public String title; //Movie Specific
    public String name; //Series Specific
//    public boolean video;
    public int vote_average;
    public int vote_count;
    public CreditsDto credits;

    public List<SeasonsTmdbDto> seasons;

    public TmdbDto() {
    }

    public TmdbDto(String backdrop_path, int budget, ArrayList<GenreDto> genres, int id, String imdb_id, String overview, String poster_path, LocalDate release_date, LocalDate first_air_date, String title, String name, int vote_average, int vote_count, CreditsDto credits, List<SeasonsTmdbDto> seasons) {
        this.backdrop_path = backdrop_path;
        this.budget = budget;
        this.genres = genres;
        this.id = id;
        this.imdb_id = imdb_id;
        this.overview = overview;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.first_air_date = first_air_date;
        this.title = title;
        this.name = name;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
        this.credits = credits;
        this.seasons = seasons;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public ArrayList<GenreDto> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<GenreDto> genres) {
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    public LocalDate getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(LocalDate first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVote_average() {
        return vote_average;
    }

    public void setVote_average(int vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public CreditsDto getCredits() {
        return credits;
    }

    public void setCredits(CreditsDto credits) {
        this.credits = credits;
    }

    public List<SeasonsTmdbDto> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<SeasonsTmdbDto> seasons) {
        this.seasons = seasons;
    }
}
