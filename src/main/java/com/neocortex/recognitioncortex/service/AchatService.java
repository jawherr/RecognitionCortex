package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.Achat;
import com.neocortex.recognitioncortex.reponses.MessageResponse;

import java.util.List;

public interface AchatService {
    public MessageResponse save(Achat achat);
    public MessageResponse update(Achat achat);
    public MessageResponse delete(Long id);
    public List<Achat> findAll();
    public Achat findById(Long id);
}
