package com.neocortex.recognitioncortex.repository;

import com.neocortex.recognitioncortex.entities.Employe_speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface Employe_specialityRepository extends JpaRepository<Employe_speciality, Long> {
    boolean existsByTitre(String titre);
}
