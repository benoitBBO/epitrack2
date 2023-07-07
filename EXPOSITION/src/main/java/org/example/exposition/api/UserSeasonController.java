package org.example.exposition.api;

import org.example.application.IUserSeasonService;
import org.example.domaine.userselection.UserSeason;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/userSeasons")
public class UserSeasonController {

    @Autowired
    IUserSeasonService UserSeasonService;

    @PostMapping
    public void createUser(@RequestBody UserSeason userSeason){
        UserSeasonService.createUserSeason(userSeason);
    }
    @GetMapping("/{id}")
    public UserSeason findUserById(@PathVariable("id") Long id){
        return UserSeasonService.findUserSeasonById(id);
    }
    @PutMapping
    public void updateUser(@RequestBody UserSeason userSeason){
        UserSeasonService.updateUserSeason(userSeason);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        UserSeasonService.deleteUserSeason(id);
    }
}
