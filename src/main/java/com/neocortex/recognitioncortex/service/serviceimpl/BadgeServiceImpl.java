package com.neocortex.recognitioncortex.service.serviceimpl;

import com.neocortex.recognitioncortex.entities.Badge;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.repository.BadgeRepository;
import com.neocortex.recognitioncortex.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class BadgeServiceImpl implements BadgeService {
    @Autowired
    BadgeRepository badgeRepository;

    @Transactional
    @Override
    public MessageResponse save(Badge badge) {
        boolean existe = badgeRepository.existsByNom(badge.getNom());
        if (existe){
            return new MessageResponse(false,"Echec !","Cette badge existe déja !");
        }
        badgeRepository.save(badge);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }


    @Transactional
    @Override
    public MessageResponse update(Badge badge) {
        boolean existe = badgeRepository.existsById(badge.getId());
        if (!existe){
            boolean existe1 = badgeRepository.existsByNom(badge.getNom());
            return new MessageResponse(false,"Echec !","Cette badge existe déja !");
        }
        badgeRepository.save(badge);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }

    @Transactional
    @Override
    public MessageResponse delete(Long id) {
        Badge badge = findById(id);
        if (badge==null){
            return new MessageResponse(false,"Echec","Cet enregistrement n'existe pas !");
        }
        badgeRepository.delete(badge);
        return new MessageResponse(true,"Succès", "L'enregistrement à été supprimé avec succès.");
    }

    @Override
    public List<Badge> findAll() {

        return badgeRepository.findAll();
    }

    @Override
    public Badge findById(Long id) {
        Badge badge = badgeRepository.findById(id).orElse(null);
        return badge;
    }
}
