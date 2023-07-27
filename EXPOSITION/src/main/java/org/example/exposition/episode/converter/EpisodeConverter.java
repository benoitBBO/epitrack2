package org.example.exposition.episode.converter;

import org.example.domaine.catalog.Episode;
import org.example.exposition.episode.dto.EpisodeDetailDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EpisodeConverter {

    public EpisodeDetailDto convertEntityToDetailDto(Episode entity){
        ModelMapper mapper=new ModelMapper();
        return mapper.map(entity,EpisodeDetailDto.class);
    }
}
