package com.neocortex.recognitioncortex.controller.user;

import com.neocortex.recognitioncortex.entities.Tache;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.service.serviceimpl.TacheServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/tache")
@PreAuthorize("hasRole('ROLE_USER')")
public class TacheController{

    @Autowired
    private TacheServiceImpl tacheService;

    @GetMapping
    @ApiOperation(value="Trouver tous les taches", notes="find taches")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="tache trouvé")
    })
    public List<Tache> findAll() {
        return tacheService.findAll();
    }

    @PostMapping
    @ApiOperation(value="Enregister un tache", notes="save tache",authorizations = {@Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="tache enregistré")
    })
    public MessageResponse save(@RequestBody Tache tache) {
        return tacheService.save(tache);
    }

    @PutMapping
    @ApiOperation(value="mettre à jour un cadeaux", notes="update cadeaux")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="cadeaux mis à jour")
    })
    public MessageResponse update(@RequestBody Tache tache) {
        return tacheService.update(tache);
    }

    @GetMapping("/{code}")
    @ApiOperation(value="Trouver un cadeaux par id", notes="find cadeaux par id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="cadeaux trouvé")
    })
    public Tache findById(@PathVariable("code") Long id) {
        return tacheService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="supprimer un tache", notes="delete tache")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="tache supprimé")
    })
    public MessageResponse delete(@PathVariable Long id) {
        return tacheService.delete(id);
    }
}

