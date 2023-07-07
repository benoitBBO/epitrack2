package org.example.application;

import org.example.domaine.userselection.UserSerie;
import org.example.infrastructure.repository.IUserSerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserSerieServiceImpl implements IUserSerieService {
    @Autowired
    IUserSerieRepository userSerieRepository;
    @Override
    public void create(UserSerie userSerie) {
        userSerieRepository.save(userSerie);
    }
    @Override
    public UserSerie findById(Long id) {
        Optional<UserSerie> optionalTask = userSerieRepository.findById(id);
        if (!optionalTask.isPresent()) {
            throw new EntityNotFoundException("La serie du user avec l'id "+id+" est introuvable");
        }
        return optionalTask.get();
    }
    @Override
    public UserSerie update(UserSerie userSerie) {
        return userSerieRepository.save(userSerie);
    }
    @Override
    public void delete(Long id) {
        userSerieRepository.deleteById(id);
    }
}
