package org.example.application.user;

import org.example.domaine.user.UserProfile;
import org.example.infrastructure.repository.user.IUserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements IUserProfileService {

    @Autowired
    IUserProfileRepository userProfileRepository;

    @Override
    public void createUserProfile(UserProfile user) {
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
    public UserProfile findUserProfileByEmail(String email) {
        System.out.println("UserProfileServie rech par email: "+email);
        return userProfileRepository.findByEmail(email);
    }
}
