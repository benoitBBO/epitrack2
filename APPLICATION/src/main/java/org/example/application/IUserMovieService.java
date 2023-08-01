package org.example.application;

import org.example.domaine.userselection.UserMovie;
import org.example.domaine.userselection.UserRating;

import java.util.List;

public interface IUserMovieService {
    List<UserMovie> create(UserMovie userMovie);
    UserMovie findById(Long id);
    List<UserMovie> findAll();
    UserMovie update(UserMovie userMovie);
    List<UserMovie> delete(Long movieId, Long userId);
    List<UserMovie> findFirst4ByUserIdOrderByUserRatingDesc(Long userId);
    List<UserMovie> findAllByUserIdOrderByUserRatingDesc(Long userId);
    void updateUserRating(UserRating userRating);
    UserMovie updateUserMovieStatus(Long userMovieId, String status);


}
