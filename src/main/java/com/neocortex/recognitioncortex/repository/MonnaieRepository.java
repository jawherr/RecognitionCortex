package com.neocortex.recognitioncortex.repository;

import com.neocortex.recognitioncortex.entities.Monnaie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface MonnaieRepository extends JpaRepository<Monnaie, Long> {
}
