package org.example.exposition.serie.api;

import org.example.application.ISerieService;
import org.example.domaine.catalog.Movie;
import org.example.domaine.catalog.Serie;
import org.example.exposition.movie.dto.MovieMinDto;
import org.example.exposition.serie.converter.SerieConverter;
import org.example.exposition.serie.dto.SerieDetailDto;
import org.example.exposition.serie.dto.SerieMinDto;
import org.example.exposition.tmdb.dto.TmdbDto;
import org.springframework.beans.factory.annotation.Autowired;
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
//    public void createSerie(@RequestBody Serie serie){
//        service.create(serie);
//    }
    public void createSerie(@RequestBody TmdbDto json){
        service.create(serieConverter.convertTmdbDtoToEntity(json));
    }

    @GetMapping("/{id}")
    public SerieDetailDto findyById(@PathVariable("id") Long id){
        return serieConverter.convertEntityToDetailDto(service.findById(id));
    }

    @PutMapping
    public void updateSerie(@RequestBody TmdbDto json){
        service.updateSerie(serieConverter.convertTmdbDtoToEntity(json));
    }

    @DeleteMapping("/{id}")
    public void deleteSerie(@PathVariable("id") Long id){
        service.deleteSerie(id);
    }
    @GetMapping("/best4")
    public List<Serie> findFirst4ByOrderByTotalRatingDesc(){
        return service.findFirst4ByOrderByTotalRatingDesc();
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
