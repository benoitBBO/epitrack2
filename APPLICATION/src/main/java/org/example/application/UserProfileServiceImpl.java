package org.example.application;

import org.example.domaine.user.UserProfile;
import org.example.infrastructure.repository.IUserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserProfileServiceImpl implements IUserProfileService {
    @Autowired
    IUserProfileRepository userRepository;

    @Override
    public void createUserProfile(UserProfile user) {
        userRepository.save(user);
    }

    @Override
    public UserProfile findUserProfileById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void updateUserProfile(UserProfile user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUserProfile(Long id) {
        userRepository.deleteById(id);
    }
}