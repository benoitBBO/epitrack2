package org.example.application;

import org.example.domaine.userselection.UserSerie;

import java.util.List;


public interface IUserSerieService {
    void create(UserSerie userSerie);
    UserSerie findById(Long id);
    UserSerie update(UserSerie userSerie);
    void delete(Long id);
    List<UserSerie> findFirst4ByUserIdOrderByUserRatingDesc(Long userId);
    void updateUserRating(Long userId, Long videoId, Integer rating);

    List<UserSerie> findAllByUserIdOrderByUserRatingDesc(Long userId);

}
