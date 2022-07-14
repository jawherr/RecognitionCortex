package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.Notification;
import com.neocortex.recognitioncortex.reponses.MessageResponse;

import java.util.List;

public interface NotificationService {
    public MessageResponse save(Notification notification);
    public MessageResponse update(Notification notification);
    public MessageResponse delete(Long id);
    public List<Notification> findAll();
    public Notification findById(Long id);
}
