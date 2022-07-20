package com.neocortex.recognitioncortex.service.serviceimpl;

import com.neocortex.recognitioncortex.entities.Cadeaux;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.repository.CadeauxRepository;
import com.neocortex.recognitioncortex.service.CadeauxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CadeauxServiceImpl implements CadeauxService {
    @Autowired
    CadeauxRepository cadeauxRepository;

    @Transactional
    @Override
    public MessageResponse save(Cadeaux cadeaux) {
        boolean existe = cadeauxRepository.existsByNom(cadeaux.getNom());
        if (existe){
            return new MessageResponse(false,"Echec !","Cette cadeaux existe déja !");
        }
        cadeauxRepository.save(cadeaux);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }


    @Transactional
    @Override
    public MessageResponse update(Cadeaux cadeaux) {
        boolean existe = cadeauxRepository.existsById(cadeaux.getId());
        if (!existe){
            boolean existe1 = cadeauxRepository.existsByNom(cadeaux.getNom());
            return new MessageResponse(false,"Echec !","Cette cadeaux existe déja !");
        }
        cadeauxRepository.save(cadeaux);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }

    @Transactional
    @Override
    public MessageResponse delete(Long id) {
        Cadeaux cadeaux = findById(id);
        if (cadeaux==null){
            return new MessageResponse(false,"Echec","Cet enregistrement n'existe pas !");
        }
        cadeauxRepository.delete(cadeaux);
        return new MessageResponse(true,"Succès", "L'enregistrement à été supprimé avec succès.");
    }

    @Override
    public List<Cadeaux> findAll() {
        return cadeauxRepository.findAll();
    }

    @Override
    public Cadeaux findById(Long id) {
        Cadeaux cadeaux = cadeauxRepository.findById(id).orElse(null);
        return cadeaux;
    }
}
