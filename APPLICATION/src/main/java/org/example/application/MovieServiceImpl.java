package org.example.application;

import org.example.domaine.catalog.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.infrastructure.repository.IMovieRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements IMovieService {
    @Autowired
    IMovieRepository movieRepository;
    @Override
    @Transactional
    public void create(Movie movie) {
        movieRepository.save(movie);
    }
    @Override
    public Movie findById(Long id) {
        Optional<Movie> optionalTask = movieRepository.findById(id);
        if (!optionalTask.isPresent()) {
            throw new EntityNotFoundException("Le films avec l'id "+id+" est introuvable");
        }
        return optionalTask.get();
    }
    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }
    @Override
    public Movie update(Movie movie) {
        return movieRepository.save(movie);
    }
    @Override
    public void delete(Long id) {
        movieRepository.deleteById(id);
    }
}
