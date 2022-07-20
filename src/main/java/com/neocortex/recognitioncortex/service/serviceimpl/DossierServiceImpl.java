package com.neocortex.recognitioncortex.service.serviceimpl;

import com.neocortex.recognitioncortex.entities.Dossier;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.repository.DossierRepository;
import com.neocortex.recognitioncortex.service.DossierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DossierServiceImpl implements DossierService {
    @Autowired
    DossierRepository dossierRepository;

    @Transactional
    @Override
    public MessageResponse save(Dossier dossier) {
        dossierRepository.save(dossier);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }


    @Transactional
    @Override
    public MessageResponse update(Dossier dossier) {
        boolean existe = dossierRepository.existsById(dossier.getId());
        dossierRepository.save(dossier);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }

    @Transactional
    @Override
    public MessageResponse delete(Long id) {
        Dossier dossier = findById(id);
        if (dossier==null){
            return new MessageResponse(false,"Echec","Cet enregistrement n'existe pas !");
        }
        dossierRepository.delete(dossier);
        return new MessageResponse(true,"Succès", "L'enregistrement à été supprimé avec succès.");
    }

    @Override
    public List<Dossier> findAll() {
        return dossierRepository.findAll();
    }

    @Override
    public Dossier findById(Long id) {
        Dossier dossier = dossierRepository.findById(id).orElse(null);
        return dossier;
    }
}
