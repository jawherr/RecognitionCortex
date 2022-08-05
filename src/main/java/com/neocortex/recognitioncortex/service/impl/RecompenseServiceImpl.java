package com.neocortex.recognitioncortex.service.impl;

import com.neocortex.recognitioncortex.entities.Recompense;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.service.RecompenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class RecompenseServiceImpl implements RecompenseService {
    @Autowired
    com.neocortex.recognitioncortex.repository.RecompenseRepository RecompenseRepository;

    @Transactional
    @Override
    public MessageResponse save(Recompense Recompense) {
        boolean existe = RecompenseRepository.existsByNom(Recompense.getNom());
        if (existe){
            return new MessageResponse(false,"Echec !","Cette Recompense existe déja !");
        }
        RecompenseRepository.save(Recompense);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }


    @Transactional
    @Override
    public MessageResponse update(Recompense Recompense) {
        boolean existe = RecompenseRepository.existsById(Recompense.getId());
        if (!existe){
            boolean existe1 = RecompenseRepository.existsByNom(Recompense.getNom());
            return new MessageResponse(false,"Echec !","Cette Recompense existe déja !");
        }
        RecompenseRepository.save(Recompense);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }

    @Transactional
    @Override
    public MessageResponse delete(Long id) {
        Recompense Recompense = findById(id);
        if (Recompense==null){
            return new MessageResponse(false,"Echec","Cet enregistrement n'existe pas !");
        }
        RecompenseRepository.delete(Recompense);
        return new MessageResponse(true,"Succès", "L'enregistrement à été supprimé avec succès.");
    }

    @Override
    public List<Recompense> findAll() {

        return RecompenseRepository.findAll();
    }

    @Override
    public Recompense findById(Long id) {
        Recompense Recompense = RecompenseRepository.findById(id).orElse(null);
        return Recompense;
    }
}
