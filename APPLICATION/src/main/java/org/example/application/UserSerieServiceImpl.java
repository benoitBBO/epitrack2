package org.example.application;

import org.example.application.util.ICalculService;
import org.example.domaine.catalog.Movie;
import org.example.domaine.catalog.Serie;
import org.example.domaine.exceptions.ResourceAlreadyExistsException;
import org.example.domaine.exceptions.ResourceNotFoundException;
import org.example.domaine.user.UserProfile;
import org.example.domaine.userselection.UserEpisode;
import org.example.domaine.userselection.UserMovie;
import org.example.domaine.userselection.UserRating;
import org.example.domaine.userselection.UserSeason;
import org.example.domaine.userselection.UserSerie;
import org.example.infrastructure.repository.ISerieRepository;
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
    @Autowired
    IUserSeasonService userSeasonService;
    @Autowired
    IUserEpisodeService userEpisodeService;

    @Override
    public void create(UserSerie userSerie) {
        Optional<UserSerie> optionalUserSerie = userSerieRepository.findByUserIdAndSerieId(userSerie.getUser().getId(), userSerie.getSerie().getId());
        if (optionalUserSerie.isPresent()){
            throw new ResourceAlreadyExistsException();
        }
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
    public void delete(Long serieId, Long userId) {
        Optional<UserSerie> userSerieOptional = userSerieRepository.findByUserIdAndSerieId(userId, serieId);
        if (!userSerieOptional.isPresent()) {
            throw new EntityNotFoundException("Le film du user avec l'id "+serieId+" est introuvable");
        }
        userSerieRepository.deleteById(userSerieOptional.get().getId());
    }

    @Override
    public List<UserSerie> findFirst4ByUserIdOrderByUserRatingDesc(Long userId){
        Optional<List<UserSerie>> optional = userSerieRepository.findFirst4ByUserIdOrderByUserRatingDesc(userId);
        if (optional.isPresent()){
            return optional.get();
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public void updateUserRating(UserRating userRating) {
        //Update UserSerieRating
        Optional<UserSerie> userSerieOptional = userSerieRepository.findByUserIdAndSerieId(userRating.getUserId(), userRating.getMovieId());
        if (userSerieOptional.isEmpty()){
            throw new ResourceNotFoundException();
        }
        else {
            //mise à jour de la note utilisateur (rating)
            userSerieRepository.updateUserSerieRating(userRating.getUserMovieId(), userRating.getNewRating());

            //Update TotalRating et VoteCount
            UserSerie userSerie = userSerieOptional.get();
            serieService.updateSerieTotalRatingAndVoteCount(userSerie.getSerie(), userRating);
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
    public void updateStatusUserSerieAndSeasonsAndEpisodes(Long userSerieId, String status) {

        UserSerie userSerie = findById(userSerieId);
        // si non trouvé, l'exception est déjà levée dans la méthode findById

        //mise à jour de la serie avec le bon statut
        userSerie.setStatus(status);
        userSerie.setStatusDate(LocalDate.now());
        update(userSerie);

        for (UserSeason userSeason : userSerie.getUserSeasons()) {
            //Pour chaque season : si status <> attendu , mettre à jour la userseason avec le bon status
            System.out.println("userSeason= "+ userSeason);

                if (userSeason.getStatus() != status) {
                   userSeason.setStatus(status);
                   userSeason.setStatusDate(LocalDate.now());
                   userSeasonService.updateUserSeason(userSeason);
               }

            for (UserEpisode userEpisode : userSeason.getUserEpisodes()) {
                //Pour chaque épisode : si status <> attendu , mettre à jour le userepisode avec le bon status
                if (userEpisode.getStatus() != status) {
                   userEpisode.setStatus(status);
                   userEpisode.setStatusDate(LocalDate.now());
                   userEpisodeService.update(userEpisode);
                }
            }
        }
    }

    @Override
    @Transactional
    public void updateStatusUserSeasonAndEpisodesAndVerifyStatusUserSerie(Long userSerieId, Long userSeasonId, String status) {
        String newStatusForSerie = status;
        Boolean userSeasonIdFound = false;

        UserSerie userSerie = findById(userSerieId);

        if (userSerie.getUserSeasons().size() > 0) {

            for (UserSeason userSeason : userSerie.getUserSeasons()) {
                if (userSeason.getId().equals(userSeasonId)) {
                    //pour la user-saison concernée, mettre à jour le statut
                    userSeason.setStatus(status);
                    userSeason.setStatusDate(LocalDate.now());
                    userSeasonService.updateUserSeason(userSeason);
                    //boolean user-Season ok
                    userSeasonIdFound = true;

                    //Pour chaque épisode : si status <> attendu , mettre à jour le user-episode avec le bon status
                    for (UserEpisode userEpisode : userSeason.getUserEpisodes()) {
                        if (userEpisode.getStatus() != status) {
                            userEpisode.setStatus(status);
                            userEpisode.setStatusDate(LocalDate.now());
                            userEpisodeService.update(userEpisode);
                        }
                    }
                } else {
                    //pour les autres saisons, on balaye leur statut pour vérifier si la user-série doit être mis à ONGOING (en cours)
                    if (!userSeason.getStatus().equals(status)) {
                        newStatusForSerie = "ONGOING";
                    }
                }
            }
        }

        if (userSeasonIdFound) {
            //mettre à jour le statut de user-serie si besoin
            if (!userSerie.getStatus().equals(newStatusForSerie)) {
                userSerie.setStatus(newStatusForSerie);
                userSerie.setStatusDate(LocalDate.now());
                update(userSerie);
            }
        } else {
            throw new ResourceNotFoundException("La user-season est introuvable");
        }

    }

    @Override
    public void updateStatusUserEpisodeAndVerifyStatusUserSeasonAndSerie(Long userSerieId, Long userSeasonId, Long userEpisodeId, String status) {
        String newStatusForSerie = status;
        String newStatusForSeason = status;
        Boolean userEpisodeIdFound = false;

        UserSerie userSerie = findById(userSerieId);

        if (userSerie.getUserSeasons().size() > 0) {

            for (UserSeason userSeason : userSerie.getUserSeasons()) {
                if (userSeason.getId().equals(userSeasonId)) {
                    //pour la user-saison concernée, balayer épisodes
                    for (UserEpisode userEpisode : userSeason.getUserEpisodes()) {
                        if (userEpisode.getId().equals(userEpisodeId)) {
                            // pour le user-episode concerné : màj statut
                            userEpisode.setStatus(status);
                            userEpisode.setStatusDate(LocalDate.now());
                            userEpisodeService.update(userEpisode);
                            //boolean user-Episode ok
                            userEpisodeIdFound = true;
                        } else {
                            //pour les autres épisodes, on balaye leur statut
                            //pour vérifier si user-season et user-serie doivent être mis à ONGOING (en cours)
                            if (!userEpisode.getStatus().equals(status)) {
                                newStatusForSeason = "ONGOING";
                                newStatusForSerie = "ONGOING";
                            }
                        }
                    }
                    if (userEpisodeIdFound) {
                        //mettre à jour le statut de user-season si besoin
                        if (!userSeason.getStatus().equals(newStatusForSeason)) {
                            userSeason.setStatus(newStatusForSerie);
                            userSeason.setStatusDate(LocalDate.now());
                            userSeasonService.updateUserSeason(userSeason);
                        }
                    } else {
                        throw new ResourceNotFoundException("Le user-episode est introuvable");
                    }

                } else {
                    //pour les autres seasons, on balaye leur statut pour vérifier si user-serie doit être màs à ONGOING
                    if (!userSeason.getStatus().equals(status) && !newStatusForSerie.equals("ONGOING")) {
                        newStatusForSerie = "ONGOING";
                    }
                }
            }
            if (userEpisodeIdFound) {
                //mettre à jour le statut de user-serie si besoin
                if (!userSerie.getStatus().equals(newStatusForSerie)) {
                    userSerie.setStatus(newStatusForSerie);
                    userSerie.setStatusDate(LocalDate.now());
                    update(userSerie);
                }
            } else {
                throw new ResourceNotFoundException("La user-season est introuvable");
            }
        }

    }


}
