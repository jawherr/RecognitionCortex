package com.neocortex.recognitioncortex.service.impl;

import com.neocortex.recognitioncortex.entities.Utilisateur;
import com.neocortex.recognitioncortex.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UtilisateurDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Utilisateur utilisateur = utilisateurRepository.findByUsername(username);
            return UtilisateurDetailsImpl.build(utilisateur);
        } catch (Exception e){
            return (UserDetails) new UsernameNotFoundException("Utilisateur Not Found with username: " + username);
        }
    }
}
