package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.ProductInOrder;
import com.neocortex.recognitioncortex.entities.Recompense;
import com.neocortex.recognitioncortex.reponses.MessageResponse;

import java.util.List;

public interface RecompenseService {
    public MessageResponse save(Recompense Recompense);
    public MessageResponse update(Recompense Recompense);
    public MessageResponse delete(Long id);
    public List<Recompense> findAll();
    public Recompense findById(Long id);
}
