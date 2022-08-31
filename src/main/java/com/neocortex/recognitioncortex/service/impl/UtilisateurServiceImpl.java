package com.neocortex.recognitioncortex.service.impl;

import com.neocortex.recognitioncortex.entities.Cart;
import com.neocortex.recognitioncortex.entities.Utilisateur;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.repository.CartRepository;
import com.neocortex.recognitioncortex.repository.UtilisateurRepository;
import com.neocortex.recognitioncortex.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@DependsOn("passwordEncoder")
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    CartRepository cartRepository;

    @Transactional
    @Override
    public MessageResponse save(Utilisateur utilisateur) {
        boolean existe = utilisateurRepository.existsByUsername(utilisateur.getUsername());
        if (existe){
            return new MessageResponse(false,"Echec !","Cette utilisateur existe déja !");
        }
        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
        Cart savedCart = cartRepository.save(new Cart(savedUtilisateur));
        savedUtilisateur.setCart(savedCart);
        utilisateurRepository.save(savedUtilisateur);
        return new MessageResponse(true,"Succès","Opération réalisée avec succès.");
    }

    @Override
    public Utilisateur findOne(String username) {
        return utilisateurRepository.findByUsername(username);
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

    public Optional<UtilisateurDetailsImpl> getCurrentUser() {

        UtilisateurDetailsImpl principal = (UtilisateurDetailsImpl)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return Optional.of(principal);
    }

    @Transactional
    @Override
    public Utilisateur findByCode(Long code) {
        Utilisateur utilisateur = utilisateurRepository.findById(code).orElse(null);
        return utilisateur;
    }
}
