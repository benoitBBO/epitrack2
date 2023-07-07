package org.example.exposition.api;

import org.example.application.IUserMovieService;
import org.example.domaine.userselection.UserMovie;
import org.springframework.beans.factory.annotation.Autowired;
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
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")Long id){
        userMovieService.delete(id);
    }

}
