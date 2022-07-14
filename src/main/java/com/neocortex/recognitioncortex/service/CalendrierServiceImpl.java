package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.Calendrier;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.repository.CalendrierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CalendrierServiceImpl implements CalendrierService{
    @Autowired
    CalendrierRepository calendrierRepository;

    @Transactional
    @Override
    public MessageResponse save(Calendrier calendrier) {
        calendrierRepository.save(calendrier);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }


    @Transactional
    @Override
    public MessageResponse update(Calendrier calendrier) {
        boolean existe = calendrierRepository.existsById(calendrier.getId());
        calendrierRepository.save(calendrier);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }

    @Transactional
    @Override
    public MessageResponse delete(Long id) {
        Calendrier calendrier = findById(id);
        if (calendrier==null){
            return new MessageResponse(false,"Echec","Cet enregistrement n'existe pas !");
        }
        calendrierRepository.delete(calendrier);
        return new MessageResponse(true,"Succès", "L'enregistrement à été supprimé avec succès.");
    }

    @Override
    public List<Calendrier> findAll() {
        return calendrierRepository.findAll();
    }

    @Override
    public Calendrier findById(Long id) {
        Calendrier calendrier = calendrierRepository.findById(id).orElse(null);
        return calendrier;
    }
}
