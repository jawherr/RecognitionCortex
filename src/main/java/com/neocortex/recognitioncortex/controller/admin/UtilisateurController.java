package com.neocortex.recognitioncortex.controller.admin;

import com.neocortex.recognitioncortex.dtos.UtilisateurDTO;
import com.neocortex.recognitioncortex.entities.Utilisateur;
import com.neocortex.recognitioncortex.exception.UserNotFoundException;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.service.CortexAccountService;
import com.neocortex.recognitioncortex.service.impl.UtilisateurServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Api
@RestController
@CrossOrigin("*")
@RequestMapping("/utilisateur")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UtilisateurController {
    @Autowired
    private UtilisateurServiceImpl utilisateurServiceImpl;

    @GetMapping
    @PutMapping
    @ApiOperation(value="Trouver tout les utilisateur", notes="trouver utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Utilisateurs trouvés")
    })
    public List<Utilisateur> findAll() {
        return utilisateurServiceImpl.findAll();
    }

    @PostMapping
    @ApiOperation(value="Enregistrer un utilisateur", notes="save user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="utilisateur enregistrer")
    })
    public MessageResponse save(@RequestBody Utilisateur utilisateur) {
        return utilisateurServiceImpl.save(utilisateur);
    }

    @PutMapping
    @ApiOperation(value="Mettre à jour utilisateur", notes="mise à jour user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="utilisateur mis à jour")
    })
    public MessageResponse update(@RequestBody Utilisateur utilisateur) {
        return utilisateurServiceImpl.update(utilisateur);
    }

    @GetMapping("/profile/{username}")
    public ResponseEntity<Utilisateur> getProfile(@PathVariable("username") String username, Principal principal) {

            return ResponseEntity.ok(utilisateurServiceImpl.findOne(username));

    }

    @GetMapping("/{code}")
    @ApiOperation(value="Trouver utilisateur par id", notes="trouver user par id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Utilisateur trouvé")
    })
    public Utilisateur findById(@PathVariable("code") Long code) {
        return utilisateurServiceImpl.findByCode(code);
    }

    @DeleteMapping("/{code}")
    @ApiOperation(value="supprimer un utilisateur", notes="supprimer utilisateur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="utilisateur supprimé")
    })
    public MessageResponse delete(@PathVariable Long code) {
        return utilisateurServiceImpl.delete(code);
    }

}
