package org.example.application;

import org.example.domaine.exceptions.ResourceNotFoundException;
import org.example.domaine.userselection.UserMovie;
import org.example.domaine.userselection.UserSerie;
import org.example.infrastructure.repository.IUserSerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserSerieServiceImpl implements IUserSerieService {
    @Autowired
    IUserSerieRepository userSerieRepository;
    @Autowired
    ISerieService serieService;

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


    public List<UserSerie> findFirst4ByUserIdOrderByUserRatingDesc(Long userId){
        Optional<List<UserSerie>> optional = userSerieRepository.findFirst4ByUserIdOrderByUserRatingDesc(userId);
        if (optional.isPresent()){
            return optional.get();
        } else {
            return null;
        }
    }
    @Override
    public void updateUserRating(Long userId, Long videoId, Integer rating) {
        Optional<UserSerie> userSerieOptional = userSerieRepository.findByUserIdAndSerieId(userId, videoId);
        if (userSerieOptional.isEmpty()){
            throw new ResourceNotFoundException();
        }
        else {
            //mise à jour de la note utilisateur (rating)
            UserSerie userSerie = userSerieOptional.get();
            userSerie.setUserRating(rating);
            userSerieRepository.save(userSerie);

            //recalcul note global du film au catalogue
            serieService.updateSerieTotalRating(userSerie.getSerie(), rating);
        }
    }

    @Override
    public List<UserSerie> findAllByUserIdOrderByUserRatingDesc(Long userId) {
        Optional<List<UserSerie>> optional = userSerieRepository.findAllByUserIdOrderByUserRatingDesc(userId);
        if (optional.isPresent()){
            return optional.get();
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void updateUserSerieStatus(Long userSerieId, String status) {
        System.out.println("upddateUserSerieStatus: userSerieId="+userSerieId);
        Optional<UserSerie> userSerieOptional = userSerieRepository.findById(userSerieId);
        // vérif que le find By Id est en eager sur seasons / episodes. Sinon le forcer avec un join fetch
        if (userSerieOptional.isEmpty()){
            throw new ResourceNotFoundException();
        }
        else {
            UserSerie userSerie = userSerieOptional.get();
            userSerie.setStatus(status);
            userSerie.setStatusDate(LocalDate.now());
            userSerieRepository.save(userSerie);

            // Pour chaque UserSeasons
            //        find by id
            //        màj du status si existe et status <> nv status
            //        Pour chaque UserEpisodes
            //            find by id
            //            màj du status si existe et status <> nv status
        }
    }
}
