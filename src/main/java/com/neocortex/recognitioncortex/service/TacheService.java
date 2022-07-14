package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.Tache;
import com.neocortex.recognitioncortex.reponses.MessageResponse;

import java.util.List;

public interface TacheService {
    public MessageResponse save(Tache tache);
    public MessageResponse update(Tache tache);
    public MessageResponse delete(Long id);
    public List<Tache> findAll();
    public Tache findById(Long id);
}
