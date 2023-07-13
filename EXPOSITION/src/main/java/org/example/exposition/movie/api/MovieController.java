package org.example.exposition.movie.api;

import org.example.application.IMovieService;
import org.example.domaine.catalog.Movie;
import org.example.exposition.movie.converter.MovieConverter;
import org.example.exposition.movie.dto.MovieDetailDto;
import org.example.exposition.movie.dto.MovieMinDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "http://localhost:4200") // Remplacez par l'URL autorisée pour le cross-domain
public class MovieController {
    @Autowired
    IMovieService movieService;
    @Autowired
    MovieConverter movieConverter;
    @PostMapping
    public void createMovie(@RequestBody MovieDetailDto detailDto) {
        movieService.create(movieConverter.convertDetailDtoToEntity(detailDto));
    }
    @GetMapping("/{id}")
    public MovieDetailDto findById(@PathVariable("id")Long id) {
        return movieConverter.convertEntityToDetailDto(movieService.findById(id));
    }
    @GetMapping
    public List<MovieDetailDto> findAll(){
        List<Movie> moviesEntity = movieService.findAll();
        List<MovieDetailDto> moviesDetailDto = new ArrayList<>();
        for (Movie movie:moviesEntity) {
            MovieDetailDto movieDetailDto = movieConverter.convertEntityToDetailDto(movie);
            moviesDetailDto.add(movieDetailDto);
        }
        return moviesDetailDto;
    }
    @PutMapping
    public MovieDetailDto update(@RequestBody MovieDetailDto detailDto){
        return movieConverter.convertEntityToDetailDto(movieService.update(movieConverter.convertDetailDtoToEntity(detailDto)));
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        movieService.delete(id);
    }

    @GetMapping("/best4")
    public List<MovieMinDto> findFirst4ByOrderByTotalRatingDesc(){
        List<Movie> moviesEntity = movieService.findFirst4ByOrderByTotalRatingDesc();
        List<MovieMinDto> moviesMinDto = new ArrayList<>();
        for (Movie movie:moviesEntity) {
            MovieMinDto movieMinDto = movieConverter.convertEntityToMinDto(movie);
            moviesMinDto.add(movieMinDto);
        }
        return moviesMinDto;
    }
    @GetMapping("/search")
    public List<MovieMinDto> findByTitleContains(@RequestParam("query") String title){
        List<Movie> moviesEntity = movieService.findByTitleContains(title);
        List<MovieMinDto> moviesMinDto = new ArrayList<>();
        for (Movie movie:moviesEntity) {
            MovieMinDto movieMinDto = movieConverter.convertEntityToMinDto(movie);
            moviesMinDto.add(movieMinDto);
        }
        return moviesMinDto;
    }

}