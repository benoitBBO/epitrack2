package org.example.application;


import org.example.domaine.userselection.UserSeason;

public interface IUserSeasonService {
    void createUserSeason(UserSeason userSeason);

    UserSeason findUserSeasonById(Long id);

    void updateUserSeason(UserSeason userSeason);

    void deleteUserSeason(Long id);



}
