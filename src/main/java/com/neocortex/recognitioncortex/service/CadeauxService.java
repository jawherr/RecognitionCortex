package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.Cadeaux;
import com.neocortex.recognitioncortex.reponses.MessageResponse;

import java.util.List;

public interface CadeauxService {
    public MessageResponse save(Cadeaux cadeaux);
    public MessageResponse update(Cadeaux cadeaux);
    public MessageResponse delete(Long id);
    public List<Cadeaux> findAll();
    public Cadeaux findById(Long id);
}
