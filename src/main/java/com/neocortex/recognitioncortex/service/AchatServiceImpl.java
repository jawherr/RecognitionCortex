package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.Achat;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.repository.AchatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class AchatServiceImpl implements AchatService {
    @Autowired
    AchatRepository achatRepository;

    @Transactional
    @Override
    public MessageResponse save(Achat achat) {
        boolean existe = achatRepository.existsByNom(achat.getNom());
        if (existe){
            return new MessageResponse(false,"Echec !","Cette achat existe déja !");
        }
        achatRepository.save(achat);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }


    @Transactional
    @Override
    public MessageResponse update(Achat achat) {
        boolean existe = achatRepository.existsById(achat.getId());
        if (!existe){
            boolean existe1 = achatRepository.existsByNom(achat.getNom());
            return new MessageResponse(false,"Echec !","Cette achat existe déja !");
        }
        achatRepository.save(achat);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }

    @Transactional
    @Override
    public MessageResponse delete(Long id) {
        Achat achat = findById(id);
        if (achat==null){
            return new MessageResponse(false,"Echec","Cet enregistrement n'existe pas !");
        }
        achatRepository.delete(achat);
        return new MessageResponse(true,"Succès", "L'enregistrement à été supprimé avec succès.");
    }

    @Override
    public List<Achat> findAll() {

        return achatRepository.findAll();
    }

    @Override
    public Achat findById(Long id) {
        Achat achat = achatRepository.findById(id).orElse(null);
        return achat;
    }
}
