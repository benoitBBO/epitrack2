package org.example.exposition.tmdb.dto;

import java.time.LocalDate;
import java.util.ArrayList;

public class EpisodeTmdbDto {
    public LocalDate air_date;
    public int episode_number;
    public int id;
    public String name;
    public String overview;
    public String production_code;
    public int runtime;
    public int season_number;
    public int show_id;
    public String still_path;
    public double vote_average;
    public int vote_count;
    public ArrayList<CrewDto> crew;
    public ArrayList<GuestStarDto> guest_stars;

    public EpisodeTmdbDto() {
    }

    public EpisodeTmdbDto(LocalDate air_date, int episode_number, int id, String name, String overview, String production_code, int runtime, int season_number, int show_id, String still_path, double vote_average, int vote_count, ArrayList<CrewDto> crew, ArrayList<GuestStarDto> guest_stars) {
        this.air_date = air_date;
        this.episode_number = episode_number;
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.production_code = production_code;
        this.runtime = runtime;
        this.season_number = season_number;
        this.show_id = show_id;
        this.still_path = still_path;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
        this.crew = crew;
        this.guest_stars = guest_stars;
    }

    public LocalDate getAir_date() {
        return air_date;
    }

    public void setAir_date(LocalDate air_date) {
        this.air_date = air_date;
    }

    public int getEpisode_number() {
        return episode_number;
    }

    public void setEpisode_number(int episode_number) {
        this.episode_number = episode_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getProduction_code() {
        return production_code;
    }

    public void setProduction_code(String production_code) {
        this.production_code = production_code;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public int getSeason_number() {
        return season_number;
    }

    public void setSeason_number(int season_number) {
        this.season_number = season_number;
    }

    public int getShow_id() {
        return show_id;
    }

    public void setShow_id(int show_id) {
        this.show_id = show_id;
    }

    public String getStill_path() {
        return still_path;
    }

    public void setStill_path(String still_path) {
        this.still_path = still_path;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public ArrayList<CrewDto> getCrew() {
        return crew;
    }

    public void setCrew(ArrayList<CrewDto> crew) {
        this.crew = crew;
    }

    public ArrayList<GuestStarDto> getGuest_stars() {
        return guest_stars;
    }

    public void setGuest_stars(ArrayList<GuestStarDto> guest_stars) {
        this.guest_stars = guest_stars;
    }
}
