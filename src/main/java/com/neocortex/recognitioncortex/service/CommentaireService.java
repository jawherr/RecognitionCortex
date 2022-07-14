package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.Commentaire;
import com.neocortex.recognitioncortex.reponses.MessageResponse;

import java.util.List;

public interface CommentaireService {
    public MessageResponse save(Commentaire commentaire);
    public MessageResponse update(Commentaire commentaire);
    public MessageResponse delete(Long id);
    public List<Commentaire> findAll();
    public Commentaire findById(Long id);
}
