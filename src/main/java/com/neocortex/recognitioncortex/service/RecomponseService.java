package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.Recomponse;
import com.neocortex.recognitioncortex.reponses.MessageResponse;

import java.util.List;

public interface RecomponseService {
    public MessageResponse save(Recomponse recomponse);
    public MessageResponse update(Recomponse recomponse);
    public MessageResponse delete(Long id);
    public List<Recomponse> findAll();
    public Recomponse findById(Long id);
}
