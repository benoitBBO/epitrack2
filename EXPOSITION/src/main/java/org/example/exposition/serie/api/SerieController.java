package org.example.exposition.serie.api;

import org.example.application.ISerieService;
import org.example.domaine.catalog.Movie;
import org.example.domaine.catalog.Serie;
import org.example.exposition.movie.dto.MovieDetailDto;
import org.example.exposition.movie.dto.MovieMinDto;
import org.example.exposition.serie.converter.SerieConverter;
import org.example.exposition.serie.dto.SerieDetailDto;
import org.example.exposition.serie.dto.SerieMinDto;
import org.example.exposition.tmdb.dto.TmdbDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/series")
@CrossOrigin(origins = "http://localhost:4200") // Remplacez par l'URL autorisée pour le cross-domain
public class SerieController {
    @Autowired
    ISerieService service;
    @Autowired
    SerieConverter serieConverter;

    @PostMapping
    public void createSerie(@RequestBody TmdbDto json){
        service.create(serieConverter.convertTmdbDtoToEntity(json));
    }

    @PostMapping("/mass")
    public void createMovies(@RequestBody List<TmdbDto> dtoList) {
        dtoList.forEach(dto -> {
            service.create(serieConverter.convertTmdbDtoToEntity(dto));
        });
    }

    @GetMapping("/{id}")
    public ResponseEntity<SerieDetailDto> findyById(@PathVariable("id") Long id){
        SerieDetailDto serieDetailDto = serieConverter.convertEntityToDetailDto(service.findById(id));
        return ResponseEntity.ok().body(serieDetailDto);
    }

    @PutMapping
    public void updateSerie(@RequestBody TmdbDto json){
        service.updateSerie(serieConverter.convertTmdbDtoToEntity(json));
    }

    @DeleteMapping("/{id}")
    public void deleteSerie(@PathVariable("id") Long id){
        service.deleteSerie(id);
    }

    @GetMapping
    public List<SerieDetailDto> findAll(){
        List<Serie> seriesEntity = service.findAll();
        List<SerieDetailDto> seriesDetailDto = new ArrayList<>();
        for (Serie serie:seriesEntity) {
            SerieDetailDto serieDetailDto = serieConverter.convertEntityToDetailDto(serie);
            seriesDetailDto.add(serieDetailDto);
        }
        return seriesDetailDto;
    }
    @GetMapping("/best4")
    public List<SerieDetailDto> findFirst4ByOrderByTotalRatingDesc(){
        List<Serie> series = service.findFirst4ByOrderByTotalRatingDesc();
        List<SerieDetailDto> serieDetailDtoList = new ArrayList<>();
        for (Serie serie : series) {
            SerieDetailDto serieDetailDto = serieConverter.convertEntityToDetailDto(serie);
            serieDetailDtoList.add(serieDetailDto);
        }
        return serieDetailDtoList;
    }

    @GetMapping("/search")
    public List<SerieMinDto> findByTitleContains(@RequestParam("query") String title){
        List<Serie> seriesEntity = service.findByTitleContains(title);
        List<SerieMinDto> seriesMinDto = new ArrayList<>();
        for (Serie serie:seriesEntity) {
            SerieMinDto serieMinDto = serieConverter.convertEntityToMinDto(serie);
            seriesMinDto.add(serieMinDto);
        }
        return seriesMinDto;
    }
}
