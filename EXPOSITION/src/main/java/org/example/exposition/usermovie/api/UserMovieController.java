package org.example.exposition.usermovie.api;

import org.example.application.IUserMovieService;
import org.example.domaine.userselection.UserMovie;
import org.example.domaine.userselection.UserRating;
import org.example.domaine.userselection.UserSerie;
import org.example.exposition.usermovie.converter.UserMovieConverter;
import org.example.exposition.usermovie.dto.UserMovieDetailDto;
import org.example.exposition.usermovie.dto.UserMovieMinDto;
import org.example.exposition.userserie.dto.UserSerieDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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
    public ResponseEntity< List<UserMovieDetailDto> > createMovie(@RequestBody UserMovieDetailDto userMovieDetailDto) {
        List<UserMovie> updatedUserMoviesEntity = userMovieService.create(userMovieConverter.convertDetailDtoToEntity(userMovieDetailDto));
        List<UserMovieDetailDto> userMoviesDetailDto = new ArrayList<>();

        for (UserMovie userMovie:updatedUserMoviesEntity) {
            userMoviesDetailDto.add(userMovieConverter.convertEntityToDetailDto(userMovie));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userMoviesDetailDto);
    }
    @GetMapping("/{id}")
    public UserMovieDetailDto findById(@PathVariable("id")Long id) {
        return userMovieConverter.convertEntityToDetailDto(userMovieService.findById(id));
    }

    @DeleteMapping("/{movieId}/{userId}")
    public ResponseEntity< List<UserMovieDetailDto> > delete(@PathVariable("movieId")Long movieId, @PathVariable("userId")Long userId){
        List<UserMovie> updatedUserMoviesEntity = userMovieService.delete(movieId, userId);
        List<UserMovieDetailDto> userMoviesDetailDto = new ArrayList<>();

        for (UserMovie userMovie:updatedUserMoviesEntity) {
            userMoviesDetailDto.add(userMovieConverter.convertEntityToDetailDto(userMovie));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userMoviesDetailDto);
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
    @GetMapping("/user/{userId}")
    public List<UserMovieDetailDto> findAllByUserIdOrderByUserRatingDesc(@PathVariable("userId") Long userId) {
        List<UserMovie> userMoviesEntity = userMovieService.findAllByUserIdOrderByUserRatingDesc(userId);
        List<UserMovieDetailDto> userMoviesDetailDto = new ArrayList<>();
        for (UserMovie userMovie:userMoviesEntity) {
            UserMovieDetailDto userMovieDetailDto = userMovieConverter.convertEntityToDetailDto(userMovie);
            userMoviesDetailDto.add(userMovieDetailDto);
        }
        return userMoviesDetailDto;
    }

    @PutMapping("/rating")
    public ResponseEntity<String> userMovieRating(@RequestBody UserRating userRating) {
        userMovieService.updateUserRating(userRating);

        return ResponseEntity.status(HttpStatus.OK).body("Vote bien pris en compte");
    }

    @PutMapping("/status/{userMovieId}/{status}")
    public ResponseEntity<UserMovieDetailDto> updateUserMovieStatus(@PathVariable("userMovieId") Long userMovieId,
                                                        @PathVariable("status") String status) {
        UserMovie userMovie = userMovieService.updateUserMovieStatus(userMovieId, status);
        UserMovieDetailDto userMovieDetailDto = userMovieConverter.convertEntityToDetailDto(userMovie);

        return ResponseEntity.status(HttpStatus.OK).body(userMovieDetailDto);
    }
}
