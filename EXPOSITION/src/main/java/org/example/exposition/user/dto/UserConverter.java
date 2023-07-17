package org.example.exposition.user.dto;

import org.example.application.role.IRoleService;
import org.example.domaine.user.Role;
import org.example.domaine.user.UserProfile;
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
        userProfile.setUserName(userDto.getUserName());
        userProfile.setEmail(userDto.getEmail());
        userProfile.setFirstName(userDto.getFirstName());
        userProfile.setLastName(userDto.getLastName());
        //recherche role User
        Role role = roleService.findByName("ROLE_USER");
        userProfile.setRole(role);
        //encode du password
        passwordEncoder = new BCryptPasswordEncoder();
        userProfile.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userProfile;
    }
}
