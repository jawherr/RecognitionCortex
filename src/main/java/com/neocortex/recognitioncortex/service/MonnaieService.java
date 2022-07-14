package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.Monnaie;
import com.neocortex.recognitioncortex.reponses.MessageResponse;

import java.util.List;

public interface MonnaieService {
    public MessageResponse save(Monnaie monnaie);
    public MessageResponse update(Monnaie message);
    public MessageResponse delete(Long id);
    public List<Monnaie> findAll();
    public Monnaie findById(Long id);
}
