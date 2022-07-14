package com.neocortex.recognitioncortex.controller.admin;

import com.neocortex.recognitioncortex.entities.Dossier;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.service.DossierServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@CrossOrigin("*")
@RequestMapping("/dossier")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class DossierController {

    @Autowired
    private DossierServiceImpl dossierService;

    @GetMapping
    @ApiOperation(value="Trouver tous les dossier", notes="find dossier")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="dossier trouvé")
    })
    public List<Dossier> findAll() {
        return dossierService.findAll();
    }

    @PostMapping
    @ApiOperation(value="Enregister un dossier", notes="save dossier",authorizations = {@Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="dossier enregistré")
    })
    public MessageResponse save(@RequestBody Dossier dossier) {
        return dossierService.save(dossier);
    }

    @PutMapping
    @ApiOperation(value="mettre à jour un dossier", notes="update dossier")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="dossier mis à jour")
    })
    public MessageResponse update(@RequestBody Dossier dossier) {
        return dossierService.update(dossier);
    }

    @GetMapping("/{code}")
    @ApiOperation(value="Trouver un dossier par id", notes="find dossier par id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="dossier trouvé")
    })
    public Dossier findById(@PathVariable("code") Long id) {
        return dossierService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="supprimer un dossier", notes="delete dossier")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="dossier supprimé")
    })
    public MessageResponse delete(@PathVariable Long id) {
        return dossierService.delete(id);
    }
}

