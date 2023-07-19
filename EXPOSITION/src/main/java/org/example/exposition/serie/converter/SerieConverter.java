package org.example.exposition.serie.converter;

import org.example.domaine.catalog.*;
import org.example.exposition.episode.dto.EpisodeDetailDto;
import org.example.exposition.episode.dto.EpisodeMinDto;
import org.example.exposition.season.dto.SeasonDetailDto;
import org.example.exposition.serie.dto.SerieDetailDto;
import org.example.exposition.serie.dto.SerieMinDto;
import org.example.exposition.tmdb.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    //Converter TMDB / Entity
    public Serie convertTmdbDtoToEntity(TmdbDto json){
        Serie serie = new Serie();

        serie.setTitle(json.getName());
        serie.setOverview(json.getOverview());
        serie.setReleaseDate(json.getFirst_air_date());
        serie.setTotalRating(json.getVote_average());
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
