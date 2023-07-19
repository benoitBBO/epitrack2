package org.example.application;

import org.example.domaine.userselection.UserMovie;

import java.util.List;

public interface IUserMovieService {
    void create(UserMovie userMovie);
    UserMovie findById(Long id);
    List<UserMovie> findAll();
    UserMovie update(UserMovie userMovie);
    void delete(Long id);
    List<UserMovie> findFirst4ByUserIdOrderByUserRatingDesc(Long userId);
    List<UserMovie> findAllByUserIdOrderByUserRatingDesc(Long userId);
}
