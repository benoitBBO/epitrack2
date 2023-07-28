package org.example.application.user;

import org.example.domaine.exceptions.InputMissingException;
import org.example.domaine.exceptions.ResourceAlreadyExistsException;
import org.example.domaine.exceptions.ResourceNotFoundException;
import org.example.domaine.user.UserProfile;
import org.example.infrastructure.repository.user.IUserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements IUserProfileService {

    @Autowired
    IUserProfileRepository userProfileRepository;

    @Override
    public void createUserProfile(UserProfile user) {
        if (user.getUserName().isEmpty() || user.getPassword().isEmpty() || user.getEmail().isEmpty()){
            throw new InputMissingException();
        }
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findByUserName(user.getUserName());
        if (optionalUserProfile.isPresent()){
            throw new ResourceAlreadyExistsException();
        }
        userProfileRepository.save(user);
    }

    @Override
    public UserProfile findUserProfileByUsername(String username) {
        System.out.println("UserProfileService rech par username: "+ username);
        if (username.isEmpty()){
            throw new InputMissingException();
        }
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findByUserName(username);
        if (!optionalUserProfile.isPresent()){
            throw new ResourceNotFoundException();
        }
        return optionalUserProfile.get();
    }

    @Override
    public UserProfile findUserProfileById(Long id) {
        return userProfileRepository.findById(id).get();
    }

    @Override
    public void updateUserProfile(UserProfile user) {
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findByUserName(user.getUserName());
        if (!optionalUserProfile.isPresent()){
            throw new ResourceNotFoundException();
        }
        UserProfile oldUser = optionalUserProfile.get();
        UserProfile newUser = oldUser;
        if (oldUser.getPassword() != user.getPassword() && user.getPassword() != null){
            newUser.setPassword(user.getPassword());
        }
        if (oldUser.getEmail() != user.getEmail()){
            newUser.setEmail(user.getEmail());
        }
        if (oldUser.getFirstName() != user.getFirstName()){
            newUser.setFirstName(user.getFirstName());
        }
        if (oldUser.getLastName() != user.getLastName()){
            newUser.setLastName(user.getLastName());
        }
        System.out.println("newUser= "+newUser);
        userProfileRepository.save(newUser);
    }

    @Override
    public void deleteUserProfile(Long id) {
        userProfileRepository.deleteById(id);
    }

}
