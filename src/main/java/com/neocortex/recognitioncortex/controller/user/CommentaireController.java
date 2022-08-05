package com.neocortex.recognitioncortex.controller.user;

import com.neocortex.recognitioncortex.entities.Commentaire;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.service.impl.CommentaireServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/commentaire")
//@PreAuthorize("hasRole('ROLE_USER')")
public class CommentaireController{

    @Autowired
    private CommentaireServiceImpl commentaireService;

    @GetMapping
    @ApiOperation(value="Trouver tous les commentaire", notes="find commentaire")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="badges commentaire")
    })
    public List<Commentaire> findAll() {
        return commentaireService.findAll();
    }

    @PostMapping
    @ApiOperation(value="Enregister un commentaire", notes="save commentaire",authorizations = {@Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="commentaire enregistré")
    })
    public MessageResponse save(@RequestBody Commentaire commentaire) {
        return commentaireService.save(commentaire);
    }

    @PutMapping
    @ApiOperation(value="mettre à jour un commentaire", notes="update commentaire")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="commentaire mis à jour")
    })
    public MessageResponse update(@RequestBody Commentaire commentaire) {
        return commentaireService.update(commentaire);
    }

    @GetMapping("/{code}")
    @ApiOperation(value="Trouver un commentaire par id", notes="find commentaire par id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="commentaire trouvé")
    })
    public Commentaire findById(@PathVariable("code") Long id) {
        return commentaireService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="supprimer un commentaire", notes="delete commentaire")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="commentaire supprimé")
    })
    public MessageResponse delete(@PathVariable Long id) {
        return commentaireService.delete(id);
    }
}

