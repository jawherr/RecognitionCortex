package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.Equipe;
import com.neocortex.recognitioncortex.reponses.MessageResponse;

import java.util.List;

public interface EquipeService {
    public MessageResponse save(Equipe equipe);
    public MessageResponse update(Equipe equipe);
    public MessageResponse delete(Long id);
    public List<Equipe> findAll();
    public Equipe findById(Long id);
}
