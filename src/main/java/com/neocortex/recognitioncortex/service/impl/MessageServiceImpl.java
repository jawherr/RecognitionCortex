package com.neocortex.recognitioncortex.service.impl;

import com.neocortex.recognitioncortex.entities.Message;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.repository.MessageRepository;
import com.neocortex.recognitioncortex.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageRepository messageRepository;

    @Transactional
    @Override
    public MessageResponse save(Message message) {
        messageRepository.save(message);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }


    @Transactional
    @Override
    public MessageResponse update(Message message) {
        boolean existe = messageRepository.existsById(message.getId());
        messageRepository.save(message);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }

    @Transactional
    @Override
    public MessageResponse delete(Long id) {
        Message message = findById(id);
        if (message==null){
            return new MessageResponse(false,"Echec","Cet enregistrement n'existe pas !");
        }
        messageRepository.delete(message);
        return new MessageResponse(true,"Succès", "L'enregistrement à été supprimé avec succès.");
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message findById(Long id) {
        Message message = messageRepository.findById(id).orElse(null);
        return message;
    }
}
