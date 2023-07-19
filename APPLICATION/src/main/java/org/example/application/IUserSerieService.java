package org.example.application;

import org.example.domaine.userselection.UserSerie;


public interface IUserSerieService {
    void create(UserSerie userSerie);
    UserSerie findById(Long id);
    UserSerie update(UserSerie userSerie);
    void delete(Long id);

    void updateUserRating(Long userId, Long videoId, Integer rating);
}
