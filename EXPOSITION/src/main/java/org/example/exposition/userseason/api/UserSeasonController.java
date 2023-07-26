package org.example.exposition.userseason.api;

import org.example.application.IUserSeasonService;
import org.example.application.IUserSerieService;
import org.example.domaine.userselection.UserSeason;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/userSeasons")
public class UserSeasonController {

    @Autowired
    IUserSeasonService userSeasonService;
    @Autowired
    IUserSerieService userSerieService;

    @PostMapping
    public void createUser(@RequestBody UserSeason userSeason){
        userSeasonService.createUserSeason(userSeason);
    }
    @GetMapping("/{id}")
    public UserSeason findUserById(@PathVariable("id") Long id){
        return userSeasonService.findUserSeasonById(id);
    }

    @PutMapping("/status/{userSerieId}/{userSeasonId}/{status}")
    public ResponseEntity<String> updateStatusUserSeasonAndEpisodeAndVerifySerie
            (@PathVariable("userSerieId") Long userSerieId,
            @PathVariable("userSeasonId") Long userSeasonId,
            @PathVariable("status") String status){
        userSerieService.updateStatusUserSeasonAndEpisodesAndVerifyStatusUserSerie(userSerieId, userSeasonId, status);
        return ResponseEntity.status(HttpStatus.OK).body("Le statut a bien été mis à jour");
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userSeasonService.deleteUserSeason(id);
    }
}
