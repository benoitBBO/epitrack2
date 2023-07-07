package org.example.exposition.api;

import org.example.application.ISerieService;
import org.example.domaine.catalog.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/series")
public class SerieController {
    @Autowired
    ISerieService service;

    @PostMapping
    public void createSerie(@RequestBody Serie serie){
        service.create(serie);
    }

    @GetMapping("/{id}")
    public Serie findyById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @PutMapping
    public void updateSerie(@RequestBody Serie updatedSerie){
        service.updateSerie(updatedSerie);
    }

    @DeleteMapping("/{id}")
    public void deleteSerie(@PathVariable("id") Long id){
        service.deleteSerie(id);
    }
}
