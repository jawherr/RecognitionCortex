package com.neocortex.recognitioncortex.controller.admin;

import com.neocortex.recognitioncortex.entities.Recomponse;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.service.serviceimpl.RecomponseServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@CrossOrigin("*")
@RequestMapping("/recomponse")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class RecomponseController {
    @Autowired
    private RecomponseServiceImpl recomponseService;

    @GetMapping
    @ApiOperation(value="Trouver tous les recomponse", notes="find recomponse")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="recomponse trouvé")
    })
    public List<Recomponse> findAll() {
        return recomponseService.findAll();
    }

    @PostMapping
    @ApiOperation(value="Enregister un recomponse", notes="save recomponse",authorizations = {@Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="recomponse enregistré")
    })
    public MessageResponse save(@RequestBody Recomponse recomponse) {
        return recomponseService.save(recomponse);
    }

    @PutMapping
    @ApiOperation(value="mettre à jour un recomponse", notes="update recomponse")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="recomponse mis à jour")
    })
    public MessageResponse update(@RequestBody Recomponse recomponse) {
        return recomponseService.update(recomponse);
    }

    @GetMapping("/{code}")
    @ApiOperation(value="Trouver un recomponse par id", notes="find recomponse par id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="recomponse trouvé")
    })
    public Recomponse findById(@PathVariable("code") Long id) {
        return recomponseService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="supprimer un recomponse", notes="delete recomponse")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="recomponse supprimé")
    })
    public MessageResponse delete(@PathVariable Long id) {
        return recomponseService.delete(id);
    }
}


