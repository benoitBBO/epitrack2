package org.example.exposition.usermovie.converter;

import org.example.domaine.catalog.Actor;
import org.example.domaine.catalog.Genre;
import org.example.domaine.catalog.Movie;
import org.example.domaine.userselection.UserMovie;
import org.example.exposition.movie.converter.MovieConverter;
import org.example.exposition.movie.dto.MovieDetailDto;
import org.example.exposition.tmdb.dto.CastDto;
import org.example.exposition.tmdb.dto.GenreDto;
import org.example.exposition.tmdb.dto.TmdbDto;
import org.example.exposition.user.dto.UserDto;
import org.example.exposition.usermovie.dto.UserMovieDetailDto;
import org.example.exposition.usermovie.dto.UserMovieMinDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class UserMovieConverter {

    @Autowired
    MovieConverter movieConverter;

    public UserMovie convertDetailDtoToEntity(UserMovieDetailDto dto){
        ModelMapper mapper=new ModelMapper();
        return mapper.map(dto, UserMovie.class);
    }
    public UserMovieDetailDto convertEntityToDetailDto(UserMovie entity){
        UserDto userDto = new UserDto();
        userDto.setId(entity.getUser().getId());

        UserMovieDetailDto userMovieDto = new UserMovieDetailDto();
        userMovieDto.setId(entity.getId());
        userMovieDto.setUserRating(entity.getUserRating());
        userMovieDto.setStatus(entity.getStatus());
        userMovieDto.setStatusDate(entity.getStatusDate());
        userMovieDto.setMovie(movieConverter.convertEntityToDetailDto(entity.getMovie()));
        userMovieDto.setUser(userDto);

        return userMovieDto;
    }
    public UserMovie convertMinDtoToEntity(UserMovieMinDto dto){
        ModelMapper mapper=new ModelMapper();
        return mapper.map(dto,UserMovie.class);
    }
    public UserMovieMinDto convertEntityToMinDto(UserMovie entity){
        ModelMapper mapper=new ModelMapper();
        return mapper.map(entity,UserMovieMinDto.class);
    }

    //Converter TMDB / Entity
    public Movie convertTmdbDtoToEntity(TmdbDto json){
        Movie movie = new Movie();

        movie.setTitle(json.getTitle());
        movie.setOverview(json.getOverview());
        movie.setReleaseDate(json.getRelease_date());
        movie.setTotalRating(json.getVote_average());
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
