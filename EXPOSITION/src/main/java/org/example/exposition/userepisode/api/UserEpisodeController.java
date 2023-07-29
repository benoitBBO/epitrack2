package org.example.exposition.userepisode.api;

import org.example.application.IUserEpisodeService;
import org.example.domaine.userselection.UserEpisode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/userepisode")
public class UserEpisodeController {
    @Autowired
    IUserEpisodeService userEpisodeService;

    @PostMapping
    public void createEpisode(@RequestBody UserEpisode userEpisode) {
        userEpisodeService.create(userEpisode);
    }
    @GetMapping("/{id}")
    public UserEpisode findById(@PathVariable("id")Long id) {
        return userEpisodeService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")Long id){
        userEpisodeService.delete(id);
    }
}
