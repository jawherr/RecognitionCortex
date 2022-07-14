package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.Utilisateur;
import com.neocortex.recognitioncortex.reponses.MessageResponse;

import java.util.List;

public interface UtilisateurService {
    public MessageResponse save(Utilisateur utilisateur);
    public MessageResponse update(Utilisateur utilisateur);
    public MessageResponse delete(Long code);
    public List<Utilisateur> findAll();
    public Utilisateur findByCode(Long code);
}
