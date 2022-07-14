package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.Commentaire;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.repository.CommentaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentaireServiceImpl implements CommentaireService{
    @Autowired
    CommentaireRepository commentaireRepository;

    @Transactional
    @Override
    public MessageResponse save(Commentaire commentaire) {
        commentaireRepository.save(commentaire);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }


    @Transactional
    @Override
    public MessageResponse update(Commentaire commentaire) {
        boolean existe = commentaireRepository.existsById(commentaire.getId());
        commentaireRepository.save(commentaire);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }

    @Transactional
    @Override
    public MessageResponse delete(Long id) {
        Commentaire commentaire = findById(id);
        if (commentaire==null){
            return new MessageResponse(false,"Echec","Cet enregistrement n'existe pas !");
        }
        commentaireRepository.delete(commentaire);
        return new MessageResponse(true,"Succès", "L'enregistrement à été supprimé avec succès.");
    }

    @Override
    public List<Commentaire> findAll() {
        return commentaireRepository.findAll();
    }

    @Override
    public Commentaire findById(Long id) {
        Commentaire commentaire = commentaireRepository.findById(id).orElse(null);
        return commentaire;
    }
}
