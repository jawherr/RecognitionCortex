package com.neocortex.recognitioncortex.controller.admin;

import com.neocortex.recognitioncortex.entities.Badge;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.service.BadgeServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@CrossOrigin("*")
@RequestMapping("/badge")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class BadgeController {

    @Autowired
    private BadgeServiceImpl badgeService;

    @GetMapping
    @ApiOperation(value="Trouver tous les badges", notes="find badges")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="badges trouvés")
    })
    public List<Badge> findAll() {
        return badgeService.findAll();
    }

    @PostMapping
    @ApiOperation(value="Enregister un badge", notes="save badge",authorizations = {@Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="badge enregistré")
    })
    public MessageResponse save(@RequestBody Badge badge) {
        return badgeService.save(badge);
    }

    @PutMapping
    @ApiOperation(value="mettre à jour un badge", notes="update badge")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="badge mis à jour")
    })
    public MessageResponse update(@RequestBody Badge badge) {
        return badgeService.update(badge);
    }

    @GetMapping("/{code}")
    @ApiOperation(value="Trouver un badge par id", notes="find badge par id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="badge trouvé")
    })
    public Badge findById(@PathVariable("code") Long id) {
        return badgeService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="supprimer un badge", notes="delete badge")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="badge supprimé")
    })
    public MessageResponse delete(@PathVariable Long id) {
        return badgeService.delete(id);
    }
}


