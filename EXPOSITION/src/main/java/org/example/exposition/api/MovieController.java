package org.example.exposition.api;

import org.example.application.IMovieService;
import org.example.domaine.catalog.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "http://localhost:4200") // Remplacez par l'URL autorisée pour le cross-domain
public class MovieController {
    @Autowired
    IMovieService movieService;
    @PostMapping
    public void createMovie(@RequestBody Movie movie) {
        movieService.create(movie);
    }
    @GetMapping("/{id}")
    public Movie findById(@PathVariable("id")Long id) {
        return movieService.findById(id);
    }
    @GetMapping
    public List<Movie> findAll(){
        return movieService.findAll();
    }
    @PutMapping
    public Movie update(@RequestBody Movie movie){
        return movieService.update(movie);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        movieService.delete(id);
    }

    @GetMapping("/best4")
    public List<Movie> findFirst4ByOrderByTotalRatingDesc(){
        return movieService.findFirst4ByOrderByTotalRatingDesc();
    }

}
