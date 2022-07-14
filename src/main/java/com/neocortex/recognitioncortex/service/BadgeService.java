package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.Badge;
import com.neocortex.recognitioncortex.reponses.MessageResponse;

import java.util.List;

public interface BadgeService {
    public MessageResponse save(Badge badge);
    public MessageResponse update(Badge badge);
    public MessageResponse delete(Long id);
    public List<Badge> findAll();
    public Badge findById(Long id);
}
