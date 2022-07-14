package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.Solde;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.repository.SoldeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class SoldeServiceImpl implements SoldeService{
    @Autowired
    SoldeRepository soldeRepository;

    @Transactional
    @Override
    public MessageResponse save(Solde solde) {
        soldeRepository.save(solde);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }


    @Transactional
    @Override
    public MessageResponse update(Solde solde) {
        boolean existe = soldeRepository.existsById(solde.getId());
        soldeRepository.save(solde);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }

    @Transactional
    @Override
    public MessageResponse delete(Long id) {
        Solde solde = findById(id);
        if (solde==null){
            return new MessageResponse(false,"Echec","Cet enregistrement n'existe pas !");
        }
        soldeRepository.delete(solde);
        return new MessageResponse(true,"Succès", "L'enregistrement à été supprimé avec succès.");
    }

    @Override
    public List<Solde> findAll() {
        return soldeRepository.findAll();
    }

    @Override
    public Solde findById(Long id) {
        Solde solde = soldeRepository.findById(id).orElse(null);
        return solde;
    }
}
