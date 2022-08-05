package com.neocortex.recognitioncortex.controller.user;

import com.neocortex.recognitioncortex.entities.Calendrier;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.service.impl.CalendrierServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/calendrier")
//@PreAuthorize("hasRole('ROLE_USER')")
public class CalendrierController {

    @Autowired
    private CalendrierServiceImpl calendrierService;

    @GetMapping
    @ApiOperation(value="Trouver tous les calendrier", notes="find calendrier")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="calendrier trouvé")
    })
    public List<Calendrier> findAll() {
        return calendrierService.findAll();
    }

    @PostMapping
    @ApiOperation(value="Enregister un calendrier", notes="save calendrier",authorizations = {@Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="calendrier enregistré")
    })
    public MessageResponse save(@RequestBody Calendrier calendrier) {
        return calendrierService.save(calendrier);
    }

    @PutMapping
    @ApiOperation(value="mettre à jour un calendrier", notes="update calendrier")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="calendrier mis à jour")
    })
    public MessageResponse update(@RequestBody Calendrier calendrier) {
        return calendrierService.update(calendrier);
    }

    @GetMapping("/{code}")
    @ApiOperation(value="Trouver un calendrier par id", notes="find calendrier par id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="calendrier trouvé")
    })
    public Calendrier findById(@PathVariable("code") Long id) {
        return calendrierService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="supprimer un calendrier", notes="delete calendrier")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="calendrier supprimé")
    })
    public MessageResponse delete(@PathVariable Long id) {
        return calendrierService.delete(id);
    }
}

