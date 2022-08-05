package com.neocortex.recognitioncortex.controller.admin;

import com.neocortex.recognitioncortex.entities.Role;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.service.impl.RoleServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@CrossOrigin("*")
@RequestMapping("/role")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;

    @GetMapping
    @ApiOperation(value="trouver tous les roles", notes="find all roles")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="roles trouvés")
    })
    public List<Role> findAll() {
        return roleService.findAll();
    }

    @PostMapping
    @ApiOperation(value="enregistrer un role", notes="save organisme")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="role enregistré")
    })
    public MessageResponse save(@RequestBody Role role) {
        return roleService.save(role);
    }

    @PutMapping
    @ApiOperation(value="Mettre à jour un role", notes="update role")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="role mis à jour")
    })
    public MessageResponse update(@RequestBody Role role) {
        return roleService.update(role);
    }

    @GetMapping("/{id}")
    @ApiOperation(value="trouver un role par id", notes="find role par id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="role trouvé")
    })
    public Role findById(@PathVariable("id") Long id) {
        return roleService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="Supprimer un role", notes="delete role")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="role supprimé")
    })
    public MessageResponse delete(@PathVariable Long id) {
        return roleService.delete(id);
    }


}
