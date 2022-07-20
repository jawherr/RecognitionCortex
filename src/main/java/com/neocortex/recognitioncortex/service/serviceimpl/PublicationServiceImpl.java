package com.neocortex.recognitioncortex.service.serviceimpl;

import com.neocortex.recognitioncortex.entities.Publication;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.repository.PublicationRepository;
import com.neocortex.recognitioncortex.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class PublicationServiceImpl implements PublicationService {
    @Autowired
    PublicationRepository publicationRepository;

    @Transactional
    @Override
    public MessageResponse save(Publication publication) {
        publicationRepository.save(publication);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }


    @Transactional
    @Override
    public MessageResponse update(Publication publication) {
        boolean existe = publicationRepository.existsById(publication.getId());
        publicationRepository.save(publication);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }

    @Transactional
    @Override
    public MessageResponse delete(Long id) {
        Publication publication = findById(id);
        if (publication==null){
            return new MessageResponse(false,"Echec","Cet enregistrement n'existe pas !");
        }
        publicationRepository.delete(publication);
        return new MessageResponse(true,"Succès", "L'enregistrement à été supprimé avec succès.");
    }

    @Override
    public List<Publication> findAll() {
        return publicationRepository.findAll();
    }

    @Override
    public Publication findById(Long id) {
        Publication publication = publicationRepository.findById(id).orElse(null);
        return publication;
    }
}
