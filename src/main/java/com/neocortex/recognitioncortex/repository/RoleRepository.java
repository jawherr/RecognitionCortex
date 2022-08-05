package com.neocortex.recognitioncortex.repository;

import com.neocortex.recognitioncortex.enums.ERole;
import com.neocortex.recognitioncortex.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@RepositoryRestResource
@CrossOrigin("*")
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNom(ERole roleName);
    Boolean existsByNom(ERole nom);
}