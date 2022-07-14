package com.neocortex.recognitioncortex.repository;

import com.neocortex.recognitioncortex.entities.Achat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface AchatRepository extends JpaRepository<Achat, Long> {
    boolean existsByNom(String nom);
}
