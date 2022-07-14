package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.Dossier;
import com.neocortex.recognitioncortex.reponses.MessageResponse;

import java.util.List;

public interface DossierService {
    public MessageResponse save(Dossier dossier);
    public MessageResponse update(Dossier dossier);
    public MessageResponse delete(Long id);
    public List<Dossier> findAll();
    public Dossier findById(Long id);
}
