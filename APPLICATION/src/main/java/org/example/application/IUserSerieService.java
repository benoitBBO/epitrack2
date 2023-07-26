package org.example.application;

import org.example.domaine.catalog.Serie;
import org.example.domaine.userselection.UserRating;
import org.example.domaine.userselection.UserSerie;

import java.util.List;


public interface IUserSerieService {
    void create(UserSerie userSerie);
    UserSerie findById(Long id);
    UserSerie update(UserSerie userSerie);
    void delete(Long id);
    List<UserSerie> findFirst4ByUserIdOrderByUserRatingDesc(Long userId);
    void updateUserRating(UserRating userRating);
    List<UserSerie> findAllByUserIdOrderByUserRatingDesc(Long userId);
    void updateUserSerieStatus(Long userSerieId, String status);

}
