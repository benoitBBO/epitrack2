package org.example.infrastructure.repository.user;

import org.example.domaine.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserProfileRepository extends JpaRepository<UserProfile, Long> {
    UserProfile findByEmail(String email);
    Optional<UserProfile> findByUserName(String username);
}
