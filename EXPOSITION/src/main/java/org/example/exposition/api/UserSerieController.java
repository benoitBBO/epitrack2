package org.example.exposition.api;

import org.example.application.IUserSerieService;
import org.example.domaine.userselection.UserSerie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userserie")
public class UserSerieController {
    @Autowired
    IUserSerieService userSerieService;
    @PostMapping
    public void create(@RequestBody UserSerie userSerie) {
        userSerieService.create(userSerie);
    }
    @GetMapping("/{id}")
    public UserSerie findById(@PathVariable("id")Long id) {
        return userSerieService.findById(id);
    }
    @PutMapping
    public UserSerie update(@RequestBody UserSerie userSerie){
        return userSerieService.update(userSerie);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")Long id){
        userSerieService.delete(id);
    }

}
