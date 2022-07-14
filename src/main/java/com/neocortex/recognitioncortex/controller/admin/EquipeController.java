package com.neocortex.recognitioncortex.controller.admin;

import com.neocortex.recognitioncortex.entities.Equipe;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.service.EquipeServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@CrossOrigin("*")
@RequestMapping("/equipe")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class EquipeController {
    @Autowired
    private EquipeServiceImpl equipeService;

    @GetMapping
    @ApiOperation(value="Trouver tous les equipe", notes="find equipe")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="equipe trouvé")
    })
    public List<Equipe> findAll() {
        return equipeService.findAll();
    }

    @PostMapping
    @ApiOperation(value="Enregister un equipe", notes="save equipe",authorizations = {@Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="equipe enregistré")
    })
    public MessageResponse save(@RequestBody Equipe equipe) {
        return equipeService.save(equipe);
    }

    @PutMapping
    @ApiOperation(value="mettre à jour un equipe", notes="update equipe")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="equipe mis à jour")
    })
    public MessageResponse update(@RequestBody Equipe equipe) {
        return equipeService.update(equipe);
    }

    @GetMapping("/{code}")
    @ApiOperation(value="Trouver un equipe par id", notes="find equipe par id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="equipe trouvé")
    })
    public Equipe findById(@PathVariable("code") Long id) {
        return equipeService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="supprimer un equipe", notes="delete equipe")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="equipe supprimé")
    })
    public MessageResponse delete(@PathVariable Long id) {
        return equipeService.delete(id);
    }
}


