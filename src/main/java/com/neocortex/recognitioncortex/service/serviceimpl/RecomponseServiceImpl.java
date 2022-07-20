package com.neocortex.recognitioncortex.service.serviceimpl;

import com.neocortex.recognitioncortex.entities.Recomponse;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.repository.RecomponseRepository;
import com.neocortex.recognitioncortex.service.RecomponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class RecomponseServiceImpl implements RecomponseService {
    @Autowired
    RecomponseRepository recomponseRepository;

    @Transactional
    @Override
    public MessageResponse save(Recomponse recomponse) {
        boolean existe = recomponseRepository.existsByNom(recomponse.getNom());
        if (existe){
            return new MessageResponse(false,"Echec !","Cette recomponse existe déja !");
        }
        recomponseRepository.save(recomponse);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }


    @Transactional
    @Override
    public MessageResponse update(Recomponse recomponse) {
        boolean existe = recomponseRepository.existsById(recomponse.getId());
        if (!existe){
            boolean existe1 = recomponseRepository.existsByNom(recomponse.getNom());
            return new MessageResponse(false,"Echec !","Cette recomponse existe déja !");
        }
        recomponseRepository.save(recomponse);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }

    @Transactional
    @Override
    public MessageResponse delete(Long id) {
        Recomponse recomponse = findById(id);
        if (recomponse==null){
            return new MessageResponse(false,"Echec","Cet enregistrement n'existe pas !");
        }
        recomponseRepository.delete(recomponse);
        return new MessageResponse(true,"Succès", "L'enregistrement à été supprimé avec succès.");
    }

    @Override
    public List<Recomponse> findAll() {

        return recomponseRepository.findAll();
    }

    @Override
    public Recomponse findById(Long id) {
        Recomponse recomponse = recomponseRepository.findById(id).orElse(null);
        return recomponse;
    }
}
