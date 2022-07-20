package com.neocortex.recognitioncortex.service.serviceimpl;

import com.neocortex.recognitioncortex.entities.Employe_speciality;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.repository.Employe_specialityRepository;
import com.neocortex.recognitioncortex.service.Employe_specialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class Employe_specialityServiceImpl implements Employe_specialityService {
    @Autowired
    Employe_specialityRepository employe_specialityRepository;

    @Transactional
    @Override
    public MessageResponse save(Employe_speciality employe_speciality) {
        boolean existe = employe_specialityRepository.existsByTitre(employe_speciality.getTitre());
        if (existe){
            return new MessageResponse(false,"Echec !","Cette employe specialities existe déja !");
        }
        employe_specialityRepository.save(employe_speciality);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }


    @Transactional
    @Override
    public MessageResponse update(Employe_speciality employe_speciality) {
        boolean existe = employe_specialityRepository.existsById(employe_speciality.getId());
        if (!existe){
            boolean existe1 = employe_specialityRepository.existsByTitre(employe_speciality.getTitre());
            return new MessageResponse(false,"Echec !","Cette employe specialities existe déja !");
        }
        employe_specialityRepository.save(employe_speciality);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }

    @Transactional
    @Override
    public MessageResponse delete(Long id) {
        Employe_speciality employe_speciality = findById(id);
        if (employe_speciality==null){
            return new MessageResponse(false,"Echec","Cet enregistrement n'existe pas !");
        }
        employe_specialityRepository.delete(employe_speciality);
        return new MessageResponse(true,"Succès", "L'enregistrement à été supprimé avec succès.");
    }

    @Override
    public List<Employe_speciality> findAll() {

        return employe_specialityRepository.findAll();
    }

    @Override
    public Employe_speciality findById(Long id) {
        Employe_speciality badge = employe_specialityRepository.findById(id).orElse(null);
        return badge;
    }
}
