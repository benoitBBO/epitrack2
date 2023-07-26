package org.example.exposition.userepisode.api;

import org.example.application.IUserEpisodeService;
import org.example.application.IUserSeasonService;
import org.example.application.IUserSerieService;
import org.example.domaine.userselection.UserEpisode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/userepisode")
public class UserEpisodeController {
    @Autowired
    IUserEpisodeService userEpisodeService;
    @Autowired
    IUserSerieService userSerieService;
    @PostMapping
    public void createEpisode(@RequestBody UserEpisode userEpisode) {
        userEpisodeService.create(userEpisode);
    }
    @GetMapping("/{id}")
    public UserEpisode findById(@PathVariable("id")Long id) {
        return userEpisodeService.findById(id);
    }

    @PutMapping("/status/{userSerieId}/{userSeasonId}/{userEpisodeId}/{status}")
    public ResponseEntity<String> updateStatusUserEpisodeAndVerifySeasonAndSerie
            (@PathVariable("userSerieId") Long userSerieId,
             @PathVariable("userSeasonId") Long userSeasonId,
             @PathVariable("userEpisodeId") Long userEpisodeId,
             @PathVariable("status") String status){
        userSerieService.updateStatusUserEpisodeAndVerifyStatusUserSeasonAndSerie(userSerieId, userSeasonId, userEpisodeId, status);
        return ResponseEntity.status(HttpStatus.OK).body("Le statut a bien été mis à jour");
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")Long id){
        userEpisodeService.delete(id);
    }
}
