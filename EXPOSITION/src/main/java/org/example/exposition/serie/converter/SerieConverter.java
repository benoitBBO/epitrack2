package org.example.exposition.serie.converter;

import org.example.domaine.catalog.Serie;
import org.example.exposition.serie.dto.SerieDetailDto;
import org.example.exposition.serie.dto.SerieMinDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SerieConverter {
    public Serie convertDetailDtoToEntity(SerieDetailDto dto){
        ModelMapper mapper=new ModelMapper();
        return mapper.map(dto,Serie.class);
    }
    public SerieDetailDto convertEntityToDetailDto(Serie entity){
        ModelMapper mapper=new ModelMapper();
        return mapper.map(entity,SerieDetailDto.class);
    }
    public Serie convertMinDtoToEntity(SerieMinDto dto){
        ModelMapper mapper=new ModelMapper();
        return mapper.map(dto,Serie.class);
    }
    public SerieMinDto convertEntityToMinDto(Serie entity){
        ModelMapper mapper=new ModelMapper();
        return mapper.map(entity,SerieMinDto.class);
    }
}
