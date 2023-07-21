package org.example.exposition.season.converter;

import org.example.domaine.catalog.Episode;
import org.example.domaine.catalog.Season;
import org.example.exposition.episode.converter.EpisodeConverter;
import org.example.exposition.episode.dto.EpisodeDetailDto;
import org.example.exposition.season.dto.SeasonDetailDto;
import org.example.exposition.season.dto.SeasonDetailWithoutEpisodeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeasonConverter {
    @Autowired
    EpisodeConverter episodeConverter;

    public SeasonDetailWithoutEpisodeDto convertEntityToDetailWithoutEpisodeDto(Season entity){
        ModelMapper mapper=new ModelMapper();
        return mapper.map(entity,SeasonDetailWithoutEpisodeDto.class);
    }
    public SeasonDetailDto convertEntityToDetailDto(Season entity){
        SeasonDetailDto dto = new SeasonDetailDto();
        dto.setSeason(convertEntityToDetailWithoutEpisodeDto(entity));
        List<EpisodeDetailDto> episodeDetailDtoList = new ArrayList<>();
        for (Episode episode : entity.getEpisodes()) {
            episodeDetailDtoList.add(episodeConverter.convertEntityToDetailDto(episode));
        }
        return dto;
    }
}
