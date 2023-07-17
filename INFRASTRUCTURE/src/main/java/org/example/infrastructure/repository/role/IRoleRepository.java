package org.example.infrastructure.repository.role;

import org.example.domaine.user.Role;
import org.example.domaine.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
