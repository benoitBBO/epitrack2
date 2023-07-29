package org.example.exposition.userepisode.converter;

import org.example.domaine.user.UserProfile;
import org.example.domaine.userselection.UserEpisode;
import org.example.exposition.episode.converter.EpisodeConverter;
import org.example.exposition.user.dto.UserDto;
import org.example.exposition.userepisode.dto.UserEpisodeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserEpisodeConverter {
    @Autowired
    EpisodeConverter episodeConverter;
    public UserEpisodeDto convertEntityToDto (UserEpisode entity){
        UserEpisodeDto dto = new UserEpisodeDto();
        dto.setId(entity.getId());
        dto.setEpisode(episodeConverter.convertEntityToDetailDto(entity.getEpisode()));
        dto.setStatus(entity.getStatus());
        dto.setStatusDate(entity.getStatusDate());

        UserDto userDto = new UserDto();
        userDto.setId(entity.getUser().getId());
        dto.setUser(userDto);
        return dto;
    }
}
