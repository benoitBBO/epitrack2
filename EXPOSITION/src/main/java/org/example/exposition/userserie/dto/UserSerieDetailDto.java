package org.example.exposition.userserie.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.exposition.serie.dto.SerieDetailWithoutSeasonDto;
import org.example.exposition.user.dto.UserDto;
import org.example.exposition.userseason.dto.UserSeasonDto;

import java.time.LocalDate;
import java.util.List;

public class UserSerieDetailDto {

    private Long id;
    private SerieDetailWithoutSeasonDto serie;
    private List<UserSeasonDto> userSeasons;
    private String status;
    private Integer userRating;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate statusDate;
    private UserDto user;

    public UserSerieDetailDto() {
    }

    public UserSerieDetailDto(Long id, SerieDetailWithoutSeasonDto serie, List<UserSeasonDto> userSeasons, String status, Integer userRating, LocalDate statusDate, UserDto user) {
        this.id = id;
        this.serie = serie;
        this.userSeasons = userSeasons;
        this.status = status;
        this.userRating = userRating;
        this.statusDate = statusDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SerieDetailWithoutSeasonDto getSerie() {
        return serie;
    }

    public void setSerie(SerieDetailWithoutSeasonDto serie) {
        this.serie = serie;
    }

    public List<UserSeasonDto> getUserSeasons() {
        return userSeasons;
    }

    public void setUserSeasons(List<UserSeasonDto> userSeasons) {
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

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}

