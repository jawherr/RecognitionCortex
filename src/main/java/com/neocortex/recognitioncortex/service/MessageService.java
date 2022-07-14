package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.Message;
import com.neocortex.recognitioncortex.reponses.MessageResponse;

import java.util.List;

public interface MessageService {
    public MessageResponse save(Message messagerie);
    public MessageResponse update(Message messagerie);
    public MessageResponse delete(Long id);
    public List<Message> findAll();
    public Message findById(Long id);
}
