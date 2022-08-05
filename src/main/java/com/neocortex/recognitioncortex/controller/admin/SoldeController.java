package com.neocortex.recognitioncortex.controller.admin;

import com.neocortex.recognitioncortex.entities.Solde;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.service.impl.SoldeServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@CrossOrigin("*")
@RequestMapping("/solde")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class SoldeController {
    @Autowired
    private SoldeServiceImpl soldeService;

    @GetMapping
    @ApiOperation(value="Trouver tous les solde", notes="find solde")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="solde trouvé")
    })
    public List<Solde> findAll() {
        return soldeService.findAll();
    }

    @PostMapping
    @ApiOperation(value="Enregister un solde", notes="save solde",authorizations = {@Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="solde enregistré")
    })
    public MessageResponse save(@RequestBody Solde Recompense) {
        return soldeService.save(Recompense);
    }

    @PutMapping
    @ApiOperation(value="mettre à jour un Recompense", notes="update Recompense")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Recompense mis à jour")
    })
    public MessageResponse update(@RequestBody Solde solde) {
        return soldeService.update(solde);
    }

    @GetMapping("/{code}")
    @ApiOperation(value="Trouver un solde par id", notes="find solde par id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="solde trouvé")
    })
    public Solde findById(@PathVariable("code") Long id) {
        return soldeService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="supprimer un solde", notes="delete solde")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="solde supprimé")
    })
    public MessageResponse delete(@PathVariable Long id) {
        return soldeService.delete(id);
    }
}


