package org.example.application;

import org.example.domaine.user.UserProfile;

public interface IUserProfileService {
    void createUserProfile(UserProfile userProfile);

    UserProfile findUserProfileById(Long id);

    void updateUserProfile(UserProfile userProfile);

    void deleteUserProfile(Long id);

}
