package org.example.exposition.api;

import org.example.application.IUserSerieService;
import org.example.domaine.userselection.UserSerie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userserie")
public class UserSerieController {
    @Autowired
    IUserSerieService userSerieService;
    @PostMapping
    public void create(@RequestBody UserSerie userSerie) {
        userSerieService.create(userSerie);
    }
    @GetMapping("/{id}")
    public UserSerie findById(@PathVariable("id")Long id) {
        return userSerieService.findById(id);
    }
    @PutMapping
    public UserSerie update(@RequestBody UserSerie userSerie){
        return userSerieService.update(userSerie);
    }

    @PutMapping("/vote/{userId}/{serieId}/{vote}")
    public ResponseEntity<String> updateUserMovieRating(@PathVariable("userId") Long userId,
                                                        @PathVariable("serieId") Long serieId,
                                                        @PathVariable("vote") Integer rating){
        userSerieService.updateUserRating(userId, serieId, rating);
        return ResponseEntity.status(HttpStatus.OK).body("Vote bien pris en compte");
    }
    //@PutMapping("/status/{userId}/{movieId}/{status}")
    //public ResponseEntity<String> updateUserMovieStatus (@PathVariable("userId") Long userId,
    //                                                @PathVariable("movieId") Long movieId,
    //                                                @PathVariable("status") Integer status){
    //    userMovieService.updateUserMovieStatus(userId, movieId, status);
    //    return ResponseEntity.status(HttpStatus.OK).body("Le statut a bien été mis à jour");
    //}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")Long id){
        userSerieService.delete(id);
    }

}
