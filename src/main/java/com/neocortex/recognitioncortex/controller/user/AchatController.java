package com.neocortex.recognitioncortex.controller.user;

import com.neocortex.recognitioncortex.entities.Achat;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.service.AchatServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/achat")
@PreAuthorize("hasRole('ROLE_USER')")
public class AchatController {

    @Autowired
    private AchatServiceImpl achatService;

    @GetMapping
    @ApiOperation(value="Trouver tous les achat", notes="find achat")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="achat trouvé")
    })
    public List<Achat> findAll() {
        return achatService.findAll();
    }

    @PostMapping
    @ApiOperation(value="Enregister un achat", notes="save achat",authorizations = {@Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="achat enregistré")
    })
    public MessageResponse save(@RequestBody Achat achat) {
        return achatService.save(achat);
    }

    @PutMapping
    @ApiOperation(value="mettre à jour un achat", notes="update achat")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="achat mis à jour")
    })
    public MessageResponse update(@RequestBody Achat achat) {
        return achatService.update(achat);
    }

    @GetMapping("/{code}")
    @ApiOperation(value="Trouver un achat par id", notes="find achat par id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="achat trouvé")
    })
    public Achat findById(@PathVariable("code") Long id) {
        return achatService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="supprimer un achat", notes="delete achat")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="achat supprimé")
    })
    public MessageResponse delete(@PathVariable Long id) {
        return achatService.delete(id);
    }
}

