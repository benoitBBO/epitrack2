package org.example.application;

import org.example.domaine.userselection.UserEpisode;

import java.util.List;

public interface IUserEpisodeService {
    void create(UserEpisode userEpisode);

    UserEpisode findById(Long id);

    UserEpisode update(UserEpisode userEpisode);


    void delete(Long id);
}
