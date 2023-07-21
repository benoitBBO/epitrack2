package org.example.exposition.userseason.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.exposition.season.dto.SeasonDetailDto;
import org.example.exposition.season.dto.SeasonDetailWithoutEpisodeDto;
import org.example.exposition.user.dto.UserDto;
import org.example.exposition.userepisode.dto.UserEpisodeDto;

import java.time.LocalDate;
import java.util.List;


public class UserSeasonDto {
    private Long id;
    private SeasonDetailWithoutEpisodeDto season;
    private List<UserEpisodeDto> userEpisodes;
    private String status;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate statusDate;
    private UserDto user;

    public UserSeasonDto() {
    }

    public UserSeasonDto(Long id, SeasonDetailWithoutEpisodeDto season, List<UserEpisodeDto> userEpisodes, String status, LocalDate statusDate, UserDto user) {
        this.id = id;
        this.season = season;
        this.userEpisodes = userEpisodes;
        this.status = status;
        this.statusDate = statusDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SeasonDetailWithoutEpisodeDto getSeason() {
        return season;
    }

    public void setSeason(SeasonDetailWithoutEpisodeDto season) {
        this.season = season;
    }

    public List<UserEpisodeDto> getUserEpisodes() {
        return userEpisodes;
    }

    public void setUserEpisodes(List<UserEpisodeDto> userEpisodes) {
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

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
