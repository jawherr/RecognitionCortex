package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.Calendrier;
import com.neocortex.recognitioncortex.reponses.MessageResponse;

import java.util.List;

public interface CalendrierService {
    public MessageResponse save(Calendrier calendrier);
    public MessageResponse update(Calendrier calendrier);
    public MessageResponse delete(Long id);
    public List<Calendrier> findAll();
    public Calendrier findById(Long id);
}
