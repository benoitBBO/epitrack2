package org.example.exposition.usermovie.api;

import org.example.application.IUserMovieService;
import org.example.domaine.catalog.Movie;
import org.example.domaine.userselection.UserMovie;
import org.example.exposition.movie.dto.MovieMinDto;
import org.example.exposition.usermovie.converter.UserMovieConverter;
import org.example.exposition.usermovie.dto.UserMovieDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usermovie")
public class UserMovieController {
    @Autowired
    IUserMovieService userMovieService;
    @Autowired
    UserMovieConverter userMovieConverter;
    @PostMapping
    public void createMovie(@RequestBody UserMovie userMovie) {
        userMovieService.create(userMovie);
    }
    @GetMapping("/{id}")
    public UserMovie findById(@PathVariable("id")Long id) {
        return userMovieService.findById(id);
    }
    @GetMapping
    public List<UserMovie> findAll(){
        return userMovieService.findAll();
    }
    @PutMapping
    public UserMovie update(@RequestBody UserMovie userMovie){
        return userMovieService.update(userMovie);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")Long id){
        userMovieService.delete(id);
    }
    @GetMapping("/best4/{userId}")
    public List<UserMovieDetailDto> findFirst4ByUserIdOrderByUserRatingDesc(@PathVariable("userId") Long userId){
        List<UserMovie> userMoviesEntity = userMovieService.findFirst4ByUserIdOrderByUserRatingDesc(userId);
        List<UserMovieDetailDto> userMoviesDetailDto = new ArrayList<>();
        for (UserMovie userMovie:userMoviesEntity) {
            UserMovieDetailDto userMovieDetailDto = userMovieConverter.convertEntityToDetailDto(userMovie);
            userMoviesDetailDto.add(userMovieDetailDto);
        }
        return userMoviesDetailDto;
    }
    @GetMapping("/{userId}")
    public List<UserMovieDetailDto> findAllByUserIdOrderByUserRatingDesc(@PathVariable("userId") Long userId) {
        List<UserMovie> userMoviesEntity = userMovieService.findAllByUserIdOrderByUserRatingDesc(userId);
        List<UserMovieDetailDto> userMoviesDetailDto = new ArrayList<>();
        for (UserMovie userMovie:userMoviesEntity) {
            UserMovieDetailDto userMovieDetailDto = userMovieConverter.convertEntityToDetailDto(userMovie);
            userMoviesDetailDto.add(userMovieDetailDto);
        }
        return userMoviesDetailDto;
    }
}
