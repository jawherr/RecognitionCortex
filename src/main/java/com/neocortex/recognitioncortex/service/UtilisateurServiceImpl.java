package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.Utilisateur;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UtilisateurServiceImpl implements UtilisateurService{
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Transactional
    @Override
    public MessageResponse save(Utilisateur utilisateur) {
        boolean existe = utilisateurRepository.existsByUsername(utilisateur.getUsername());
        if (existe){
            return new MessageResponse(false,"Echec !","Cette utilisateur existe déja !");
        }
        utilisateurRepository.save(utilisateur);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }

    @Transactional
    @Override
    public MessageResponse update(Utilisateur utilisateur) {
        boolean existe = utilisateurRepository.existsById(utilisateur.getId());
        if (!existe){
            boolean existe1 = utilisateurRepository.existsByUsername(utilisateur.getUsername());
            return new MessageResponse(false,"Echec !","Cette utilisateur existe déja !");
        }
        utilisateurRepository.save(utilisateur);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }

    @Transactional
    @Override
    public MessageResponse delete(Long code) {
        Utilisateur utilisateur = findByCode(code);
        if (utilisateur==null){
            return new MessageResponse(false,"Echec","Cet enregistrement n'existe pas !");
        }
        utilisateurRepository.delete(utilisateur);
        return new MessageResponse(true,"Succès", "L'enregistrement à été supprimé avec succès.");
    }

    @Transactional
    @Override
    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

    @Transactional
    @Override
    public Utilisateur findByCode(Long code) {
        Utilisateur utilisateur = utilisateurRepository.findById(code).orElse(null);
        return utilisateur;
    }
}
