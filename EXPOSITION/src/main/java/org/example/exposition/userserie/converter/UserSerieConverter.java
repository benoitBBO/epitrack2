package org.example.exposition.userserie.converter;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.domaine.user.UserProfile;
import org.example.domaine.userselection.UserSeason;
import org.example.domaine.userselection.UserSerie;
import org.example.exposition.serie.converter.SerieConverter;
import org.example.exposition.serie.dto.SerieDetailWithoutSeasonDto;
import org.example.exposition.user.dto.UserDto;
import org.example.exposition.userseason.converter.UserSeasonConverter;
import org.example.exposition.userseason.dto.UserSeasonDto;
import org.example.exposition.userserie.dto.UserSerieDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserSerieConverter {

    @Autowired
    SerieConverter serieConverter;
    @Autowired
    UserSeasonConverter userSeasonConverter;
    public UserSerieDetailDto convertEntityToDetailDto (UserSerie entity){
        UserSerieDetailDto dto = new UserSerieDetailDto();
        dto.setId(entity.getId());
        dto.setSerie(serieConverter.convertEntityToDetailWithoutSeasonDto(entity.getSerie()));

        //Liste user_season
        List<UserSeasonDto> userSeasonDtoList = new ArrayList<>();
        for (UserSeason userSeason : entity.getUserSeasons()) {
            UserSeasonDto userSeasonDto = userSeasonConverter.convertEntityToDto(userSeason);
            userSeasonDtoList.add(userSeasonDto);
        }
        dto.setUserSeasons(userSeasonDtoList);

        dto.setStatus(entity.getStatus());
        dto.setUserRating(entity.getUserRating());
        dto.setStatusDate(entity.getStatusDate());

        UserDto userDto = new UserDto();
        userDto.setId(entity.getUser().getId());
        dto.setUser(userDto);

        return dto;
    }
}
