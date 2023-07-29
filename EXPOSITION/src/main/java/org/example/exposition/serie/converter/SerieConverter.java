package org.example.exposition.serie.converter;

import org.example.application.util.ICalculService;
import org.example.domaine.catalog.*;
import org.example.exposition.season.converter.SeasonConverter;
import org.example.exposition.season.dto.SeasonDetailDto;
import org.example.exposition.serie.dto.SerieDetailDto;
import org.example.exposition.serie.dto.SerieDetailWithoutSeasonDto;
import org.example.exposition.serie.dto.SerieMinDto;
import org.example.exposition.tmdb.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SerieConverter {

    @Autowired
    SeasonConverter seasonConverter;
    @Autowired
    ICalculService calculService;


    public Serie convertDetailWithoutSeasonDtoToEntity(SerieDetailWithoutSeasonDto dto){
        Serie entity = new Serie();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setOverview(dto.getOverview());
        entity.setReleaseDate(dto.getReleaseDate());
        entity.setTotalRating(dto.getTotalRating());
        entity.setImagePosterUrl(dto.getImagePosterUrl());
        entity.setImageLandscapeUrl(dto.getImageLandscapeUrl());
        entity.setImdbRef(dto.getImdbRef());
        entity.setGenres(dto.getGenres());
        entity.setActors(dto.getActors());
        return entity;
    }

    public SerieDetailWithoutSeasonDto convertEntityToDetailWithoutSeasonDto(Serie entity){
        SerieDetailWithoutSeasonDto dto = new SerieDetailWithoutSeasonDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setOverview(entity.getOverview());
        dto.setReleaseDate(entity.getReleaseDate());
        dto.setTotalRating(entity.getTotalRating());
        dto.setImagePosterUrl(entity.getImagePosterUrl());
        dto.setImageLandscapeUrl(entity.getImageLandscapeUrl());
        dto.setImdbRef(entity.getImdbRef());
        dto.setGenres(entity.getGenres());
        dto.setActors(entity.getActors());
        return dto;
    }
    public SerieDetailDto convertEntityToDetailDto(Serie entity){
        SerieDetailDto dto = new SerieDetailDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setOverview(entity.getOverview());
        dto.setReleaseDate(entity.getReleaseDate());
        dto.setTotalRating(entity.getTotalRating());
        dto.setImagePosterUrl(entity.getImagePosterUrl());
        dto.setImageLandscapeUrl(entity.getImageLandscapeUrl());
        dto.setImdbRef(entity.getImdbRef());
        dto.setGenres(entity.getGenres());
        dto.setActors(entity.getActors());
        List<SeasonDetailDto> seasonDetailDtoList = new ArrayList<>();
        for (Season season: entity.getSeasons()) {
            seasonDetailDtoList.add(seasonConverter.convertEntityToDetailDto(season));
        }
        dto.setSeasons(seasonDetailDtoList);
        return dto;
    }
    public Serie convertMinDtoToEntity(SerieMinDto dto){
        ModelMapper mapper=new ModelMapper();
        return mapper.map(dto,Serie.class);
    }
    public SerieMinDto convertEntityToMinDto(Serie entity){
        ModelMapper mapper=new ModelMapper();
        return mapper.map(entity,SerieMinDto.class);
    }

    //Converter TMDB / Entity
    public Serie convertTmdbDtoToEntity(TmdbDto json){
        Serie serie = new Serie();

        serie.setTitle(json.getName());
        serie.setOverview(json.getOverview());
        serie.setReleaseDate(json.getFirst_air_date());
        serie.setTotalRating(json.getVote_average()/2);
        serie.setVoteCount(json.getVote_count());
        serie.setImagePosterUrl(json.getPoster_path());
        serie.setImageLandscapeUrl(json.getBackdrop_path());
        serie.setImdbRef(json.getImdb_id());

        //Set genres
        List<Genre> genreList = new ArrayList<>();
        for (GenreDto genreDto : json.getGenres()){
            Genre genre = new Genre();
            genre.setName(genreDto.getName());
            genreList.add(genre);
        }
        serie.setGenres(genreList);

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
        serie.setActors(actorList);

        //Set Seasons
        List<Season> seasonList = new ArrayList<>();
        for (SeasonsTmdbDto seasonDto : json.getSeasons()){
            Season season = new Season();
            season.setTitle(seasonDto.getName());
            season.setSeasonNumber(seasonDto.getSeason_number());
            season.setOverview(seasonDto.getOverview());
            season.setImageUrl(seasonDto.getPoster_path());
            season.setAirDate(seasonDto.getAir_date());

            //Set episodes
            List<Episode> episodeList = new ArrayList<>();
            for (EpisodeTmdbDto episodeDto : seasonDto.getEpisodes()){
                Episode episode = new Episode();
                episode.setTitle(episodeDto.getName());
                episode.setEpisodeNumber(episodeDto.getEpisode_number());
                episode.setOverview(episodeDto.getOverview());
                episode.setImageUrl(episodeDto.getStill_path());
                episode.setAirDate(episodeDto.getAir_date());
                episodeList.add(episode);

            }
            season.setEpisodes(episodeList);

            seasonList.add(season);

        }
        serie.setSeasons(seasonList);


        return serie;
    }
}
