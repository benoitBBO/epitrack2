package org.example.application;

import org.example.domaine.userselection.UserEpisode;
import org.example.domaine.userselection.UserSeason;
import org.example.domaine.userselection.UserSerie;
import org.example.infrastructure.repository.IUserSeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserSeasonServiceImpl implements IUserSeasonService {
    @Autowired
    IUserSeasonRepository repository;


    @Override
    public void createUserSeason(UserSeason userSeason) {
        repository.save(userSeason);
    }

    @Override
    public UserSeason findUserSeasonById(Long id) {
        Optional<UserSeason> clientOptional = repository.findById(id);
        return clientOptional.orElse(null);
    }

    @Override
    public void updateUserSeason(UserSeason userSeason) {
        repository.save(userSeason);

    }

    @Override
    public void deleteUserSeason(Long id) {
        repository.deleteById(id);
    }




}
