package org.example.exposition.user.converter;

import org.example.application.role.IRoleService;
import org.example.domaine.user.Role;
import org.example.domaine.user.UserProfile;
import org.example.exposition.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    IRoleService roleService;

    public UserProfile convertUserDtoToUserProfile (UserDto userDto){
        UserProfile userProfile = new UserProfile();
        if(userDto.getId() != null){
            userProfile.setId(userDto.getId());
        }
        userProfile.setUserName(userDto.getUserName());
        userProfile.setEmail(userDto.getEmail());
        userProfile.setFirstName(userDto.getFirstName());
        userProfile.setLastName(userDto.getLastName());
        //recherche role User
        Role role = roleService.findByName("ROLE_USER");
        userProfile.setRole(role);
        //encode du password si reçu
        if (userDto.getPassword() != null){
            passwordEncoder = new BCryptPasswordEncoder();
            userProfile.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        return userProfile;
    }

    public UserDto convertUserProfileToUserDto (UserProfile userProfile){
        UserDto userDto = new UserDto();
        userDto.setId(userProfile.getId());
        userDto.setUserName(userProfile.getUserName());
        userDto.setEmail(userProfile.getEmail());
        userDto.setFirstName(userProfile.getFirstName());
        userDto.setLastName(userProfile.getLastName());
        //Ne pas remonter le password dans le DTO
        return userDto;
    }
}
