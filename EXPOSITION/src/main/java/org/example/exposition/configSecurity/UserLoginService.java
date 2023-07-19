package org.example.exposition.configSecurity;

import org.example.application.user.IUserProfileService;
import org.example.domaine.user.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserLoginService implements UserDetailsService {
    @Autowired
    IUserProfileService userProfileService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //appel service user, find by email
        UserProfile userProfile = userProfileService.findUserProfileByUsername(username);
        System.out.println("retour requête by username: " + userProfile.getUserName()
                           + " " + userProfile.getPassword()
                            + " " + userProfile.getRole());
        if(userProfile!=null){
            //on alimente la liste des rôles (1 seul rôle) dans authorities
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(userProfile.getRole().getName()));
            //on charge le User pour SpringSecurity
            return new User(userProfile.getUserName(), userProfile.getPassword(), authorities);
        }
        else {
            throw new UsernameNotFoundException("profil utilisateur non trouvé");
        }


    }
}
