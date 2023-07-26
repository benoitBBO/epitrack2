package org.example.application;

import org.example.domaine.userselection.UserMovie;
import org.example.domaine.userselection.UserRating;

import java.util.List;

public interface IUserMovieService {
    List<UserMovie> create(UserMovie userMovie);
    UserMovie findById(Long id);
    List<UserMovie> findAll();
    UserMovie update(UserMovie userMovie);
    void delete(Long movieId, Long userId);
    List<UserMovie> findFirst4ByUserIdOrderByUserRatingDesc(Long userId);
    List<UserMovie> findAllByUserIdOrderByUserRatingDesc(Long userId);
    void updateUserRating(UserRating userRating);
    void updateUserMovieStatus(Long userMovieId, String status);


}
