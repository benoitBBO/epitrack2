package org.example.exposition.user.api;

import org.example.application.user.IUserProfileService;
import org.example.domaine.user.UserProfile;
import org.example.exposition.user.converter.UserConverter;
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

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody UserDto userDto){
        UserProfile user = userConverter.convertUserDtoToUserProfile(userDto);
        userProfileService.updateUserProfile(user);
        return ResponseEntity.status(HttpStatus.OK).body("Utilisateur modifié");
    }
    @DeleteMapping("/{id}")  //TODO 27/07 non utilisé au Front, à supprimer sauf si fonctionnalité suppres profil
    public void deleteUser(@PathVariable("id") Long id){
        userProfileService.deleteUserProfile(id);
    }

}