package com.neocortex.recognitioncortex.controller.user;

import com.neocortex.recognitioncortex.entities.Publication;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.service.impl.PublicationServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/publication")
//@PreAuthorize("hasRole('ROLE_USER')")
public class PublicationController{

    @Autowired
    private PublicationServiceImpl publicationService;

    @GetMapping
    @ApiOperation(value="Trouver tous les publication", notes="find publication")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="publication trouvé")
    })
    public List<Publication> findAll() {
        return publicationService.findAll();
    }

    @PostMapping
    @ApiOperation(value="Enregister un publication", notes="save publication",authorizations = {@Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="publication enregistré")
    })
    public MessageResponse save(@RequestBody Publication publication) {
        return publicationService.save(publication);
    }

    @PutMapping
    @ApiOperation(value="mettre à jour un cadeaux", notes="update cadeaux")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="cadeaux mis à jour")
    })
    public MessageResponse update(@RequestBody Publication publication) {
        return publicationService.update(publication);
    }

    @GetMapping("/{code}")
    @ApiOperation(value="Trouver un publication par id", notes="find publication par id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="publication trouvé")
    })
    public Publication findById(@PathVariable("code") Long id) {
        return publicationService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="supprimer un publication", notes="delete publication")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="publication supprimé")
    })
    public MessageResponse delete(@PathVariable Long id) {
        return publicationService.delete(id);
    }
}

