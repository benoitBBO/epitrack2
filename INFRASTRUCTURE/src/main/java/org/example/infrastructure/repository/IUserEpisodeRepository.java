package org.example.infrastructure.repository;

import org.example.domaine.userselection.UserEpisode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserEpisodeRepository extends JpaRepository<UserEpisode, Long> {
}
