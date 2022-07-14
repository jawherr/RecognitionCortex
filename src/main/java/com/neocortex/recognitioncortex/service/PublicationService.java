package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.Publication;
import com.neocortex.recognitioncortex.reponses.MessageResponse;

import java.util.List;

public interface PublicationService {
    public MessageResponse save(Publication publication);
    public MessageResponse update(Publication publication);
    public MessageResponse delete(Long id);
    public List<Publication> findAll();
    public Publication findById(Long id);
}
