package org.example.exposition.user.api;

import org.example.application.user.IUserProfileService;
import org.example.domaine.user.UserProfile;
import org.example.exposition.user.dto.UserConverter;
import org.example.exposition.user.dto.UserDto;
import org.example.exposition.user.dto.UserLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/users")
public class UserProfileController {

    @Autowired
    IUserProfileService userProfileService;

    @Autowired
    UserConverter userConverter;

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto){
        userProfileService.createUserProfile(userConverter.convertUserDtoToUserProfile(userDto));
        return ResponseEntity.status(HttpStatus.CREATED).body("Utilisateur créé");
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> findUserByUsername(@PathVariable("username") String username){
        UserDto userDto = userConverter.convertUserProfileToUserDto(userProfileService.findUserProfileByUsername(username));
        return ResponseEntity.ok().body(userDto);

    }

    @GetMapping("/id/{id}")
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