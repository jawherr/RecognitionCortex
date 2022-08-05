package com.neocortex.recognitioncortex.controller.admin;

import com.neocortex.recognitioncortex.entities.Recompense;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.service.impl.RecompenseServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@CrossOrigin("*")
@RequestMapping("/recompense")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public class RecompenseController {
    @Autowired
    private RecompenseServiceImpl RecompenseService;

    @GetMapping
    @ApiOperation(value="Trouver tous les Recompense", notes="find Recompense")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Recompense trouvé")
    })
    public List<Recompense> findAll() {
        return RecompenseService.findAll();
    }

    @PostMapping
    @ApiOperation(value="Enregister un Recompense", notes="save Recompense",authorizations = {@Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Recompense enregistré")
    })
    public MessageResponse save(@RequestBody Recompense Recompense) {
        return RecompenseService.save(Recompense);
    }

    @PutMapping
    @ApiOperation(value="mettre à jour un Recompense", notes="update Recompense")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Recompense mis à jour")
    })
    public MessageResponse update(@RequestBody Recompense Recompense) {
        return RecompenseService.update(Recompense);
    }

    @GetMapping("/{code}")
    @ApiOperation(value="Trouver un Recompense par id", notes="find Recompense par id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Recompense trouvé")
    })
    public Recompense findById(@PathVariable("code") Long id) {
        return RecompenseService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="supprimer un Recompense", notes="delete Recompense")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="Recompense supprimé")
    })
    public MessageResponse delete(@PathVariable Long id) {
        return RecompenseService.delete(id);
    }
}


