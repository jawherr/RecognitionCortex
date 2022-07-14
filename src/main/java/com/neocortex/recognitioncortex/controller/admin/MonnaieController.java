package com.neocortex.recognitioncortex.controller.admin;

import com.neocortex.recognitioncortex.entities.Monnaie;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.service.MonnaieServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@CrossOrigin("*")
@RequestMapping("/monnaie")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class MonnaieController {
    @Autowired
    private MonnaieServiceImpl monnaieService;

    @GetMapping
    @ApiOperation(value="Trouver tous les monnaie", notes="find monnaie")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="monnaie trouvé")
    })
    public List<Monnaie> findAll() {
        return monnaieService.findAll();
    }

    @PostMapping
    @ApiOperation(value="Enregister un monnaie", notes="save monnaie",authorizations = {@Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="monnaie enregistré")
    })
    public MessageResponse save(@RequestBody Monnaie monnaie) {
        return monnaieService.save(monnaie);
    }

    @PutMapping
    @ApiOperation(value="mettre à jour un equipe", notes="update equipe")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="equipe mis à jour")
    })
    public MessageResponse update(@RequestBody Monnaie monnaie) {
        return monnaieService.update(monnaie);
    }

    @GetMapping("/{code}")
    @ApiOperation(value="Trouver un monnaie par id", notes="find monnaie par id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="monnaie trouvé")
    })
    public Monnaie findById(@PathVariable("code") Long id) {
        return monnaieService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="supprimer un monnaie", notes="delete monnaie")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="monnaie supprimé")
    })
    public MessageResponse delete(@PathVariable Long id) {
        return monnaieService.delete(id);
    }
}


