package org.example.exposition.userseason.converter;

import org.example.domaine.user.UserProfile;
import org.example.domaine.userselection.UserEpisode;
import org.example.domaine.userselection.UserSeason;
import org.example.exposition.episode.converter.EpisodeConverter;
import org.example.exposition.season.converter.SeasonConverter;
import org.example.exposition.season.dto.SeasonDetailDto;
import org.example.exposition.user.dto.UserDto;
import org.example.exposition.userepisode.converter.UserEpisodeConverter;
import org.example.exposition.userepisode.dto.UserEpisodeDto;
import org.example.exposition.userseason.dto.UserSeasonDto;
import org.example.exposition.userserie.dto.UserSerieDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserSeasonConverter {

    @Autowired
    SeasonConverter seasonConverter;
    @Autowired
    UserEpisodeConverter userEpisodeConverter;

    public UserSeasonDto convertEntityToDto (UserSeason entity){
        UserSeasonDto dto = new UserSeasonDto();
        dto.setId(entity.getId());
        dto.setSeason(seasonConverter.convertEntityToDetailWithoutEpisodeDto(entity.getSeason()));

        //Liste user_episode
        List<UserEpisodeDto> userEpisodeDtoList = new ArrayList<>();
        for (UserEpisode userEpisode : entity.getUserEpisodes()) {
            UserEpisodeDto userEpisodeDto = userEpisodeConverter.convertEntityToDto(userEpisode);
            userEpisodeDtoList.add(userEpisodeDto);
        }
        dto.setUserEpisodes(userEpisodeDtoList);

        dto.setStatus(entity.getStatus());
        dto.setStatusDate(entity.getStatusDate());

        UserDto userDto = new UserDto();
        userDto.setId(entity.getUser().getId());
        dto.setUser(userDto);

        return dto;
    }
}
