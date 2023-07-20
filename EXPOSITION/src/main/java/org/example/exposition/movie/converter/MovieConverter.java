package org.example.exposition.movie.converter;

import org.example.domaine.catalog.Actor;
import org.example.domaine.catalog.Genre;
import org.example.domaine.catalog.Movie;
import org.example.exposition.movie.dto.MovieDetailDto;
import org.example.exposition.movie.dto.MovieMinDto;
import org.example.exposition.tmdb.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


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

    //Converter TMDB / Entity
    public Movie convertTmdbDtoToEntity(TmdbDto json){
        Movie movie = new Movie();

        movie.setTitle(json.getTitle());
        movie.setOverview(json.getOverview());
        movie.setReleaseDate(json.getRelease_date());
        movie.setTotalRating(json.getVote_average()* json.getVote_count()/2);
        movie.setVoteCount(json.getVote_count());
        movie.setImagePosterUrl(json.getPoster_path());
        movie.setImageLandscapeUrl(json.getBackdrop_path());
        movie.setImdbRef(json.getImdb_id());

        //Set genres
        List<Genre> genreList = new ArrayList<>();
        for (GenreDto genreDto : json.getGenres()){
            Genre genre = new Genre();
            genre.setName(genreDto.getName());
            genreList.add(genre);
        }
        movie.setGenres(genreList);

        //Set actors
        List<Actor> actorList = new ArrayList<>();
        for (CastDto castDto : json.getCredits().getCast()){
            Actor actor = new Actor();
//            actor.setId(castDto.getId());
            actor.setName(castDto.getName());
            actor.setTmdbRef((castDto.getId().toString()));
            actor.setPhotoUrl(castDto.getProfile_path());
            actorList.add(actor);
        }
        movie.setActors(actorList);


        return movie;
    }
}
