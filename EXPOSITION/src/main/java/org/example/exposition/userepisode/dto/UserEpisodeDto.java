package org.example.exposition.userepisode.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.exposition.episode.dto.EpisodeDetailDto;
import org.example.exposition.user.dto.UserDto;
import java.time.LocalDate;

public class UserEpisodeDto {

    private Long id;
    private EpisodeDetailDto episode;
    private String status;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate statusDate;
    private UserDto user;

    public UserEpisodeDto() {
    }

    public UserEpisodeDto(Long id, EpisodeDetailDto episode, String status, LocalDate statusDate, UserDto user) {
        this.id = id;
        this.episode = episode;
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

    public EpisodeDetailDto getEpisode() {
        return episode;
    }

    public void setEpisode(EpisodeDetailDto episode) {
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

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
