package org.example.exposition.movie.converter;

import org.example.domaine.catalog.Movie;
import org.example.exposition.movie.dto.MovieDetailDto;
import org.example.exposition.movie.dto.MovieMinDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class MovieConverter {

    public Movie convertDetailDtoToEntity(MovieDetailDto dto){
        ModelMapper mapper=new ModelMapper();
        return mapper.map(dto,Movie.class);
    }
    public MovieDetailDto convertEntityToDetailDto(Movie entity){
        ModelMapper mapper=new ModelMapper();
        return mapper.map(entity,MovieDetailDto.class);
    }
    public Movie convertMinDtoToEntity(MovieMinDto dto){
        ModelMapper mapper=new ModelMapper();
        return mapper.map(dto,Movie.class);
    }
    public MovieMinDto convertEntityToMinDto(Movie entity){
        ModelMapper mapper=new ModelMapper();
        return mapper.map(entity,MovieMinDto.class);
    }
}
