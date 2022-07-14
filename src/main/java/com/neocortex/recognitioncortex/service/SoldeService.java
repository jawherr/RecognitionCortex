package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.Solde;
import com.neocortex.recognitioncortex.reponses.MessageResponse;

import java.util.List;

public interface SoldeService {
    public MessageResponse save(Solde solde);
    public MessageResponse update(Solde solde);
    public MessageResponse delete(Long id);
    public List<Solde> findAll();
    public Solde findById(Long id);
}
