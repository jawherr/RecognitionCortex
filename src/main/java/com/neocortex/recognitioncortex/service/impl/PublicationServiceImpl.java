package com.neocortex.recognitioncortex.service.impl;

import com.neocortex.recognitioncortex.dtos.PublicationDto;
import com.neocortex.recognitioncortex.entities.Publication;
import com.neocortex.recognitioncortex.exception.PostNotFoundException;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.repository.PublicationRepository;
import com.neocortex.recognitioncortex.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PublicationServiceImpl implements PublicationService {
    @Autowired
    PublicationRepository publicationRepository;

    @Autowired
    private UtilisateurServiceImpl utilisateurService;

    @Transactional
    @Override
    public MessageResponse save(Publication publication) {
        publicationRepository.save(publication);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }

    public void createPost(PublicationDto postDto){
        Publication post = new Publication();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        UtilisateurDetailsImpl userName =  utilisateurService.getCurrentUser().orElseThrow(
                ()->new IllegalArgumentException("No user logged in"));
        post.setUsername(userName.getUsername());
        //post.setCreatedOn(Date);
        publicationRepository.save(post);

    }
    public List<PublicationDto> showAllPosts() {
        List<Publication> posts = publicationRepository.findAll();
        return posts.stream().map(this::mapFromPostToDto).collect(toList());

    }
    private PublicationDto mapFromPostToDto(Publication post) {

        PublicationDto postDto = new PublicationDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setUsername(post.getUsername());
        postDto.setCreatedOn(post.getCreatedOn());

        return postDto;
    }
    public PublicationDto readSinglePost(Long id) {

        Publication post = publicationRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id" + id));


        return mapFromPostToDto(post);
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
