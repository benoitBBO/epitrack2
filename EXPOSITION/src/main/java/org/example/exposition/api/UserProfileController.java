package org.example.exposition.api;

import org.example.application.IUserProfileService;
import org.example.domaine.user.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/users")
public class UserProfileController {

    @Autowired
    IUserProfileService userProfileService;

    @PostMapping
    public void createUser(@RequestBody UserProfile userProfile){
        userProfileService.createUserProfile(userProfile);
    }
    @GetMapping("/{id}")
    public UserProfile findUserById(@PathVariable("id") Long id){
        return userProfileService.findUserProfileById(id);
    }
    @PutMapping
    public void updateUser(@RequestBody UserProfile userProfile){
        userProfileService.updateUserProfile(userProfile);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userProfileService.deleteUserProfile(id);
    }
}