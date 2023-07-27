package org.example.exposition.userseason.api;

import org.example.application.IUserSeasonService;
import org.example.domaine.userselection.UserSeason;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/userSeasons")
public class UserSeasonController {

    @Autowired
    IUserSeasonService userSeasonService;

    @PostMapping
    public void createUser(@RequestBody UserSeason userSeason){
        userSeasonService.createUserSeason(userSeason);
    }
    @GetMapping("/{id}")
    public UserSeason findUserById(@PathVariable("id") Long id){
        return userSeasonService.findUserSeasonById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userSeasonService.deleteUserSeason(id);
    }
}
