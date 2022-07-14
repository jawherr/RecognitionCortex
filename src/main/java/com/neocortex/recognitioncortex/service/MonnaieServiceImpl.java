package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.Monnaie;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.repository.MonnaieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class MonnaieServiceImpl implements MonnaieService{
    @Autowired
    MonnaieRepository monnaieRepository;

    @Transactional
    @Override
    public MessageResponse save(Monnaie monnaie) {
        monnaieRepository.save(monnaie);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }


    @Transactional
    @Override
    public MessageResponse update(Monnaie monnaie) {
        boolean existe = monnaieRepository.existsById(monnaie.getId());
        monnaieRepository.save(monnaie);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }

    @Transactional
    @Override
    public MessageResponse delete(Long id) {
        Monnaie monnaie = findById(id);
        if (monnaie==null){
            return new MessageResponse(false,"Echec","Cet enregistrement n'existe pas !");
        }
        monnaieRepository.delete(monnaie);
        return new MessageResponse(true,"Succès", "L'enregistrement à été supprimé avec succès.");
    }

    @Override
    public List<Monnaie> findAll() {
        return monnaieRepository.findAll();
    }

    @Override
    public Monnaie findById(Long id) {
        Monnaie monnaie = monnaieRepository.findById(id).orElse(null);
        return monnaie;
    }
}
