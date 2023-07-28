package org.example.application;

import org.example.domaine.catalog.Serie;
import org.example.domaine.userselection.UserEpisode;
import org.example.domaine.userselection.UserRating;
import org.example.domaine.userselection.UserSeason;
import org.example.domaine.userselection.UserSerie;

import java.util.List;


public interface IUserSerieService {
    List<UserSerie> create(Long serieId, Long userId);
    UserSerie findById(Long id);
    UserSerie update(UserSerie userSerie);
    List<UserSerie> delete(Long serieId, Long userId);
    List<UserSerie> findFirst4ByUserIdOrderByUserRatingDesc(Long userId);
    void updateUserRating(UserRating userRating);
    List<UserSerie> findAllByUserIdOrderByUserRatingDesc(Long userId);
    UserSerie updateStatusUserSerieAndSeasonsAndEpisodes(Long userSerieId, String status);
    //void verifyStatusAllSeasonsForUpdateSerie(Long userSerieId, String status);

    UserSerie updateStatusUserSeasonAndEpisodesAndVerifyStatusUserSerie(Long userSerieId, Long userSeasonId, String status);

    UserSerie updateStatusUserEpisodeAndVerifyStatusUserSeasonAndSerie(Long userSerieId, Long userSeasonId, Long userEpisodeId, String status);

    List<UserSeason> sortBySeasonNumber (List<UserSeason> userSeasonList);
    List<UserEpisode> sortByEpisodeNumber (List<UserEpisode> userEpisodeList);
}
