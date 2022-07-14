package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.Notification;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    NotificationRepository notificationRepository;

    @Transactional
    @Override
    public MessageResponse save(Notification notification) {
        notificationRepository.save(notification);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }


    @Transactional
    @Override
    public MessageResponse update(Notification notification) {
        boolean existe = notificationRepository.existsById(notification.getId());
        notificationRepository.save(notification);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }

    @Transactional
    @Override
    public MessageResponse delete(Long id) {
        Notification notification = findById(id);
        if (notification==null){
            return new MessageResponse(false,"Echec","Cet enregistrement n'existe pas !");
        }
        notificationRepository.delete(notification);
        return new MessageResponse(true,"Succès", "L'enregistrement à été supprimé avec succès.");
    }

    @Override
    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification findById(Long id) {
        Notification notification = notificationRepository.findById(id).orElse(null);
        return notification;
    }
}
