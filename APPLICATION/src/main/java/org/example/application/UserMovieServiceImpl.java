package org.example.application;

import org.example.domaine.userselection.UserMovie;
import org.example.infrastructure.repository.IUserMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserMovieServiceImpl implements IUserMovieService {
    @Autowired
    IUserMovieRepository userMovieRepository;


    @Override
    public void create(UserMovie userMovie) {
        userMovieRepository.save(userMovie);
    }

    @Override
    public UserMovie findById(Long id) {
        Optional<UserMovie> optionalTask = userMovieRepository.findById(id);
        if (!optionalTask.isPresent()) {
            throw new EntityNotFoundException("Le film du user avec l'id "+id+" est introuvable");
        }
        return optionalTask.get();
    }

    @Override
    public List<UserMovie> findAll() {
        return userMovieRepository.findAll();
    }

    @Override
    public UserMovie update(UserMovie userMovie) {
        return userMovieRepository.save(userMovie);
    }

    @Override
    public void delete(Long id) {
        userMovieRepository.deleteById(id);
    }
}
