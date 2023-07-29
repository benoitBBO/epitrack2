package org.example.exposition.userserie.api;

import org.example.application.IUserSerieService;
import org.example.domaine.userselection.UserMovie;
import org.example.domaine.userselection.UserRating;
import org.example.domaine.userselection.UserSerie;
import org.example.exposition.usermovie.dto.UserMovieDetailDto;
import org.example.exposition.userserie.converter.UserSerieConverter;
import org.example.exposition.userserie.dto.UserSerieDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/userserie")
public class UserSerieController {
    @Autowired
    IUserSerieService userSerieService;
    @Autowired
    UserSerieConverter userSerieConverter;

    @PostMapping ("/{serieId}/{userId}")
    public ResponseEntity< List<UserSerieDetailDto> > create(@PathVariable("serieId")Long serieId, @PathVariable("userId")Long userId) {
        List<UserSerie> updatedUserSeriesEntity = userSerieService.create(serieId, userId);
        List<UserSerieDetailDto> userSeriesDetailDto = new ArrayList<>();

        for (UserSerie userSerie:updatedUserSeriesEntity) {
            userSeriesDetailDto.add(userSerieConverter.convertEntityToDetailDto(userSerie));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userSeriesDetailDto);
    }

    @GetMapping("/{id}")
    public UserSerieDetailDto findById(@PathVariable("id")Long id) {
        return userSerieConverter.convertEntityToDetailDto(userSerieService.findById(id));
    }

    @GetMapping("/best4/{userId}")
    public List<UserSerieDetailDto> findFirst4ByUserIdOrderByUserRatingDesc(@PathVariable("userId") Long userId){
        System.out.println("UserSerie Controller find First 4 with userId= "+userId);
        List<UserSerie> userSerieList = userSerieService.findFirst4ByUserIdOrderByUserRatingDesc(userId);
        System.out.println("UserSerieList en retour du service= "+userSerieList);
        List<UserSerieDetailDto> userSerieDetailDtos = new ArrayList<>();
        for (UserSerie userSerie:userSerieList) {
            UserSerieDetailDto userSerieDetailDto = userSerieConverter.convertEntityToDetailDto(userSerie);
            userSerieDetailDtos.add(userSerieDetailDto);
        }
        System.out.println("UserSerieDetailsDtos= "+userSerieDetailDtos);
        return userSerieDetailDtos;
    }

    @GetMapping("/user/{userId}")
    public List<UserSerieDetailDto> findAllByUserIdOrderByUserRatingDesc(@PathVariable("userId") Long userId) {
        List<UserSerie> userSerieList = userSerieService.findAllByUserIdOrderByUserRatingDesc(userId);
        List<UserSerieDetailDto> userSerieDetailDtoList = new ArrayList<>();
        for (UserSerie userSerie:userSerieList) {
            UserSerieDetailDto userSerieDetailDto = userSerieConverter.convertEntityToDetailDto(userSerie);
            userSerieDetailDtoList.add(userSerieDetailDto);
        }
        return userSerieDetailDtoList;
    }

    @PutMapping("/status/{userSerieId}/{status}")
    public ResponseEntity<UserSerieDetailDto> updateUserSerieStatus (@PathVariable("userSerieId") Long userSerieId,
                                                         @PathVariable("status") String status){
        UserSerie userSerie = userSerieService.updateStatusUserSerieAndSeasonsAndEpisodes(userSerieId, status);
        UserSerieDetailDto userSerieDetailDto = userSerieConverter.convertEntityToDetailDto(userSerie);
        return ResponseEntity.status(HttpStatus.OK).body(userSerieDetailDto);
        //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le statut n'a pas été mis à jour");
    }
    @PutMapping("/status/{userSerieId}/{userSeasonId}/{status}")
    public ResponseEntity<UserSerieDetailDto> updateStatusUserSeasonAndEpisodeAndVerifySerie
            (@PathVariable("userSerieId") Long userSerieId,
             @PathVariable("userSeasonId") Long userSeasonId,
             @PathVariable("status") String status){
        UserSerie userSerie = userSerieService.updateStatusUserSeasonAndEpisodesAndVerifyStatusUserSerie(userSerieId, userSeasonId, status);
        UserSerieDetailDto userSerieDetailDto = userSerieConverter.convertEntityToDetailDto(userSerie);
        return ResponseEntity.status(HttpStatus.OK).body(userSerieDetailDto);
    }
    @PutMapping("/status/{userSerieId}/{userSeasonId}/{userEpisodeId}/{status}")
    public ResponseEntity<UserSerieDetailDto> updateStatusUserEpisodeAndVerifySeasonAndSerie
            (@PathVariable("userSerieId") Long userSerieId,
             @PathVariable("userSeasonId") Long userSeasonId,
             @PathVariable("userEpisodeId") Long userEpisodeId,
             @PathVariable("status") String status){
        UserSerie userSerie = userSerieService.updateStatusUserEpisodeAndVerifyStatusUserSeasonAndSerie(userSerieId, userSeasonId, userEpisodeId, status);
        UserSerieDetailDto userSerieDetailDto = userSerieConverter.convertEntityToDetailDto(userSerie);
        return ResponseEntity.status(HttpStatus.OK).body(userSerieDetailDto);
    }

    @PutMapping("/rating")
    public ResponseEntity<String> userSerieRating(@RequestBody UserRating userRating) {
        userSerieService.updateUserRating(userRating);

        return ResponseEntity.status(HttpStatus.OK).body("Vote bien pris en compte");
    }

    @DeleteMapping("/{serieId}/{userId}")
    public ResponseEntity< List<UserSerieDetailDto> > delete(@PathVariable("serieId")Long serieId, @PathVariable("userId")Long userId){
        List<UserSerie> updatedUserSeriesEntity = userSerieService.delete(serieId, userId);
        List<UserSerieDetailDto> userSeriesDetailDto = new ArrayList<>();

        for (UserSerie userSerie:updatedUserSeriesEntity) {
            userSeriesDetailDto.add(userSerieConverter.convertEntityToDetailDto(userSerie));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userSeriesDetailDto);
    }

}
