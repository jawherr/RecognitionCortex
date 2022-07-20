package com.neocortex.recognitioncortex.service.serviceimpl;

import com.neocortex.recognitioncortex.entities.Tache;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.repository.TacheRepository;
import com.neocortex.recognitioncortex.service.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class TacheServiceImpl implements TacheService {
    @Autowired
    TacheRepository tacheRepository;

    @Transactional
    @Override
    public MessageResponse save(Tache tache) {
        boolean existe = tacheRepository.existsByNom(tache.getNom());
        if (existe){
            return new MessageResponse(false,"Echec !","Cette tache existe déja !");
        }
        tacheRepository.save(tache);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }


    @Transactional
    @Override
    public MessageResponse update(Tache tache) {
        boolean existe = tacheRepository.existsById(tache.getId());
        if (!existe){
            boolean existe1 = tacheRepository.existsByNom(tache.getNom());
            return new MessageResponse(false,"Echec !","Cette tache existe déja !");
        }
        tacheRepository.save(tache);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }

    @Transactional
    @Override
    public MessageResponse delete(Long id) {
        Tache tache = findById(id);
        if (tache==null){
            return new MessageResponse(false,"Echec","Cet enregistrement n'existe pas !");
        }
        tacheRepository.delete(tache);
        return new MessageResponse(true,"Succès", "L'enregistrement à été supprimé avec succès.");
    }

    @Override
    public List<Tache> findAll() {
        return tacheRepository.findAll();
    }

    @Override
    public Tache findById(Long id) {
        Tache tache = tacheRepository.findById(id).orElse(null);
        return tache;
    }
}
