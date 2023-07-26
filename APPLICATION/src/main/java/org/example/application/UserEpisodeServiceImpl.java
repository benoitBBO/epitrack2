package org.example.application;

import org.example.domaine.userselection.UserEpisode;
import org.example.domaine.userselection.UserSeason;
import org.example.infrastructure.repository.IUserEpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserEpisodeServiceImpl implements IUserEpisodeService {

    @Autowired
    IUserEpisodeRepository userEpisodeRepository;
    @Override
    public void create(UserEpisode userEpisode) {userEpisodeRepository.save(userEpisode); }

    @Override
    public UserEpisode findById(Long id) {
        Optional<UserEpisode> optionalTask = userEpisodeRepository.findById(id);
        if (!optionalTask.isPresent()) {
            throw new EntityNotFoundException("L episode du user avec l'id "+id+" est introuvable");
        }
        return optionalTask.get();
    }

    @Override
    public UserEpisode update(UserEpisode userEpisode) {
        return userEpisodeRepository.save(userEpisode);
    }


    @Override
    public void delete(Long id) {userEpisodeRepository.deleteById(id); }

}
