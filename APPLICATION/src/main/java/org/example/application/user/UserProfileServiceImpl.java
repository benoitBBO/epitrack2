package org.example.application.user;

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
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findByUserName(user.getUserName());
        if (optionalUserProfile.isPresent()){
            throw new ResourceAlreadyExistsException();
        }
        userProfileRepository.save(user);
    }

    @Override
    public UserProfile findUserProfileById(Long id) {
        return userProfileRepository.findById(id).get();
    }

    @Override
    public void updateUserProfile(UserProfile user) {
        userProfileRepository.save(user);
    }

    @Override
    public void deleteUserProfile(Long id) {
        userProfileRepository.deleteById(id);
    }
    @Override
    public UserProfile findUserProfileByUsername(String username) {
        System.out.println("UserProfileServie rech par username: "+ username);
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findByUserName(username);
        if (!optionalUserProfile.isPresent()){
            throw new ResourceNotFoundException();
        }
        return optionalUserProfile.get();
    }
}
