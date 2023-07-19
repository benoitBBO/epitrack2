package org.example.exposition.api;

import org.example.application.IUserMovieService;
import org.example.domaine.userselection.UserMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usermovie")
public class UserMovieController {
    @Autowired
    IUserMovieService userMovieService;
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
    @PutMapping("/vote/{userId}/{movieId}/{vote}")
    public ResponseEntity<String> updateUserMovieRating(@PathVariable("userId") Long userId,
                                                        @PathVariable("movieId") Long movieId,
                                                        @PathVariable("vote") Integer rating){
        userMovieService.updateUserRating(userId, movieId, rating);
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
        userMovieService.delete(id);
    }

}
