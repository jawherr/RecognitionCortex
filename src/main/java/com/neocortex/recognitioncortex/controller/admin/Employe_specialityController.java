package com.neocortex.recognitioncortex.controller.admin;

import com.neocortex.recognitioncortex.entities.Employe_speciality;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.service.Employe_specialityServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@CrossOrigin("*")
@RequestMapping("/employe_speciality")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class Employe_specialityController {
        @Autowired
        private Employe_specialityServiceImpl employe_specialityService;

        @GetMapping
        @ApiOperation(value="Trouver tous les employe speciality", notes="find employe speciality")
        @ApiResponses(value = {
                @ApiResponse(code = 200, message="employe speciality trouvé")
        })
        public List<Employe_speciality> findAll() {
                return employe_specialityService.findAll();
        }

        @PostMapping
        @ApiOperation(value="Enregister un employe speciality", notes="save employe speciality",authorizations = {@Authorization(value="jwtToken") })
        @ApiResponses(value = {
                @ApiResponse(code = 200, message="employe speciality enregistré")
        })
        public MessageResponse save(@RequestBody Employe_speciality employe_speciality) {
                return employe_specialityService.save(employe_speciality);
        }

        @PutMapping
        @ApiOperation(value="mettre à jour un employe speciality", notes="update employe speciality")
        @ApiResponses(value = {
                @ApiResponse(code = 200, message="employe speciality mis à jour")
        })
        public MessageResponse update(@RequestBody Employe_speciality employe_speciality) {
                return employe_specialityService.update(employe_speciality);
        }

        @GetMapping("/{code}")
        @ApiOperation(value="Trouver un employe speciality par id", notes="find employe speciality par id")
        @ApiResponses(value = {
                @ApiResponse(code = 200, message="employe speciality trouvé")
        })
        public Employe_speciality findById(@PathVariable("code") Long id) {
                return employe_specialityService.findById(id);
        }

        @DeleteMapping("/{id}")
        @ApiOperation(value="supprimer un employe speciality", notes="delete employe speciality")
        @ApiResponses(value = {
                @ApiResponse(code = 200, message="employe speciality supprimé")
        })
        public MessageResponse delete(@PathVariable Long id) {
                return employe_specialityService.delete(id);
        }
}


