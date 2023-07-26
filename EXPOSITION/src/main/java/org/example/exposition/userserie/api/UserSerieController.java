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
    @PostMapping //TODO mettre un DTO + converter
    public void create(@RequestBody UserSerie userSerie) {
        userSerieService.create(userSerie);
    }

    @GetMapping("/{id}")
    public UserSerieDetailDto findById(@PathVariable("id")Long id) {
        return userSerieConverter.convertEntityToDetailDto(userSerieService.findById(id));
    }

    //@PutMapping
    //public UserSerie update(@RequestBody UserSerie userSerie){
    //    return userSerieService.update(userSerie);
    //}

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

//    @PutMapping("/vote/{userId}/{serieId}/{vote}")
//    public ResponseEntity<String> updateUserMovieRating(@PathVariable("userId") Long userId,
//                                                        @PathVariable("serieId") Long serieId,
//                                                        @PathVariable("vote") Integer rating){
//        userSerieService.updateUserRating(userId, serieId, rating);
//        return ResponseEntity.status(HttpStatus.OK).body("Vote bien pris en compte");
//    }

    @PutMapping("/status/{userSerieId}/{status}")
    public ResponseEntity<String> updateUserSerieStatus (@PathVariable("userSerieId") Long userSerieId,
                                                         @PathVariable("status") String status){
        userSerieService.updateUserSerieStatus(userSerieId, status);
        return ResponseEntity.status(HttpStatus.OK).body("Le statut a bien été mis à jour");
        //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le statut n'a pas été mis à jour");
    }

    @Transactional
    @PutMapping("/rating")
    public ResponseEntity<String> userSerieRating(@RequestBody UserRating userRating) {
        userSerieService.updateUserRating(userRating);

        return ResponseEntity.status(HttpStatus.OK).body("Vote bien pris en compte");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")Long id){
        userSerieService.delete(id);
    }

}
