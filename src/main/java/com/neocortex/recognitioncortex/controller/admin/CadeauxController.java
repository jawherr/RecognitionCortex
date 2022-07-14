package com.neocortex.recognitioncortex.controller.admin;

import com.neocortex.recognitioncortex.entities.Cadeaux;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.service.CadeauxServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@CrossOrigin("*")
@RequestMapping("/cadeaux")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class CadeauxController {

    @Autowired
    private CadeauxServiceImpl cadeauxService;

    @GetMapping
    @ApiOperation(value="Trouver tous les cadeaux", notes="find cadeaux")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="cadeaux trouvé")
    })
    public List<Cadeaux> findAll() {
        return cadeauxService.findAll();
    }

    @PostMapping
    @ApiOperation(value="Enregister un cadeaux", notes="save cadeaux",authorizations = {@Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="cadeaux enregistré")
    })
    public MessageResponse save(@RequestBody Cadeaux cadeaux) {
        return cadeauxService.save(cadeaux);
    }

    @PutMapping
    @ApiOperation(value="mettre à jour un cadeaux", notes="update cadeaux")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="cadeaux mis à jour")
    })
    public MessageResponse update(@RequestBody Cadeaux cadeaux) {
        return cadeauxService.update(cadeaux);
    }

    @GetMapping("/{code}")
    @ApiOperation(value="Trouver un cadeaux par id", notes="find cadeaux par id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="cadeaux trouvé")
    })
    public Cadeaux findById(@PathVariable("code") Long id) {
        return cadeauxService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="supprimer un cadeaux", notes="delete cadeaux")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="cadeaux supprimé")
    })
    public MessageResponse delete(@PathVariable Long id) {
        return cadeauxService.delete(id);
    }
}


