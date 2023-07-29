package org.example.application;

import org.example.application.util.ICalculService;
import org.example.domaine.catalog.Episode;
import org.example.domaine.catalog.Movie;
import org.example.domaine.catalog.Season;
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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserSerieServiceImpl implements IUserSerieService {
    @Autowired
    IUserSerieRepository userSerieRepository;

    @Autowired
    ISerieRepository serieRepository;
    @Autowired
    ISerieService serieService;
    @Autowired
    IUserSeasonService userSeasonService;
    @Autowired
    IUserEpisodeService userEpisodeService;

    @Override
    public List<UserSerie> create(Long serieId, Long userId) {
        Optional<UserSerie> optionalUserSerie = userSerieRepository.findByUserIdAndSerieId(userId, serieId);
        if (optionalUserSerie.isPresent()){
            throw new ResourceAlreadyExistsException();
        }

        //Creation du UserSerie
        UserSerie userSerie = new UserSerie();
        Serie serieOriginale = serieRepository.findById(serieId).get();

        //Creation de l'objet User à passer en table
        UserProfile user = new UserProfile();
        user.setId(userId);

        //Creation de l'objet UserSeason à passer en table
        List<UserSeason> userSeasons = new ArrayList<>();

        List<Season> seasons = serieOriginale.getSeasons();
        for(Season season : seasons){
            UserSeason userSeason = new UserSeason();

            //Creation de l'objet Season à passer en table
            Season seasonTable = new Season();
            seasonTable.setId(season.getId());

            userSeason.setSeason(seasonTable);

            //Creation de l'objet UserEpisodes à passer en table
            List<UserEpisode> userEpisodes = new ArrayList<>();
            for(Episode episode : season.getEpisodes()){
                UserEpisode userEpisode = new UserEpisode();

                //Creation de l'objet Episode à passer en table
                Episode episodeTable = new Episode();
                episodeTable.setId(episode.getId());

                userEpisode.setEpisode(episodeTable);
                userEpisode.setStatus("UNWATCHED");
                userEpisode.setStatusDate(LocalDate.now());
                userEpisode.setUser(user);

                //Push du UserEpisode dans UserEpisodes
                userEpisodes.add(userEpisode);

            }

            userSeason.setUserEpisodes(userEpisodes);

            //Creation de l'objet Episode à passer en table
            Episode episode = new Episode();


            userSeason.setStatus("UNWATCHED");
            userSeason.setStatusDate(LocalDate.now());
            userSeason.setUser(user);

            //Push du UserSeason dans UserSeasons
            userSeasons.add(userSeason);

        }

        //Creation de l'objet Serie à passer en table
        Serie serie = new Serie();
        serie.setId(serieId);

        //Consitution de la couche principale
        userSerie.setSerie(serie);
        userSerie.setUserSeasons(userSeasons);
        userSerie.setStatus("UNWATCHED");
        userSerie.setStatusDate(LocalDate.now());
        userSerie.setUserRating(0);
        userSerie.setUser(user);


        userSerieRepository.save(userSerie);

        return userSerieRepository.findAllByUserIdOrderByUserRatingDesc(userSerie.getUser().getId()).get();
    }
    @Override
    public UserSerie findById(Long id) {
        Optional<UserSerie> optionalTask = userSerieRepository.findById(id);
        if (!optionalTask.isPresent()) {
            throw new EntityNotFoundException("La serie du user avec l'id "+id+" est introuvable");
        }
        UserSerie userSerie = optionalTask.get();
        //trier la user-serie par numéro de saison et numéro d'épisode
        for (UserSeason userSeason : userSerie.getUserSeasons()) {
            List<UserEpisode> userEpisodeSortedList = sortByEpisodeNumber(userSeason.getUserEpisodes());
            userSeason.setUserEpisodes(userEpisodeSortedList);
        }
        List<UserSeason> userSeasonSortedList = sortBySeasonNumber(userSerie.getUserSeasons());
        userSerie.setUserSeasons(userSeasonSortedList);

        return userSerie;
    }

    @Override
    public List<UserSeason> sortBySeasonNumber (List<UserSeason> userSeasonList){
        return userSeasonList.stream()
                .sorted(Comparator.comparingInt( (userSeason) -> userSeason.getSeason().getSeasonNumber()))
                .collect(Collectors.toList());
    }
    @Override
    public List<UserEpisode> sortByEpisodeNumber (List<UserEpisode> userEpisodeList){
        return userEpisodeList.stream()
                .sorted(Comparator.comparingInt( (userEpisode) -> userEpisode.getEpisode().getEpisodeNumber()))
                .collect(Collectors.toList());
    }

    @Override
    public UserSerie update(UserSerie userSerie) {
        return userSerieRepository.save(userSerie);
    }
    @Override
    public List<UserSerie> delete(Long serieId, Long userId) {
        Optional<UserSerie> userSerieOptional = userSerieRepository.findByUserIdAndSerieId(userId, serieId);
        if (!userSerieOptional.isPresent()) {
            throw new EntityNotFoundException("Le film du user avec l'id "+serieId+" est introuvable");
        }
        userSerieRepository.deleteById(userSerieOptional.get().getId());

        return userSerieRepository.findAllByUserIdOrderByUserRatingDesc(userSerieOptional.get().getUser().getId()).get();
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
    public UserSerie updateStatusUserSerieAndSeasonsAndEpisodes(Long userSerieId, String status) {

        UserSerie userSerie = findById(userSerieId);
        // si non trouvé, l'exception est déjà levée dans la méthode findById
        // le userSerie est trié par numéro de saison / épisode

        //mise à jour de la serie avec le bon statut
        userSerie.setStatus(status);
        userSerie.setStatusDate(LocalDate.now());
        update(userSerie);

        for (UserSeason userSeason : userSerie.getUserSeasons()) {
            //Pour chaque season : si status <> attendu , mettre à jour la userseason avec le bon status
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

        //renvoie en sortie de la user-serie mise à jour
        return userSerie;
    }

    @Override
    @Transactional
    public UserSerie updateStatusUserSeasonAndEpisodesAndVerifyStatusUserSerie(Long userSerieId, Long userSeasonId, String status) {
        String newStatusForSerie = status;
        Boolean userSeasonIdFound = false;

        UserSerie userSerie = findById(userSerieId);  //triée par numéro saison/épisode

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
        return userSerie;
    }

    @Override
    public UserSerie updateStatusUserEpisodeAndVerifyStatusUserSeasonAndSerie(Long userSerieId, Long userSeasonId, Long userEpisodeId, String status) {
        String newStatusForSerie = status;
        String newStatusForSeason = status;
        Boolean userEpisodeIdFound = false;

        UserSerie userSerie = findById(userSerieId);  //triée par numéro saison/épisode

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
        return userSerie;
    }


}
