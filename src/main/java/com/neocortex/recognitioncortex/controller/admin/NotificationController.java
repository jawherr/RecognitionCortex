package com.neocortex.recognitioncortex.controller.admin;

import com.neocortex.recognitioncortex.entities.Notification;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.service.impl.NotificationServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@CrossOrigin("*")
@RequestMapping("/notification")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class NotificationController {
    @Autowired
    private NotificationServiceImpl notificationService;

    @GetMapping
    @ApiOperation(value="Trouver tous les notification", notes="find notification")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="notification trouvé")
    })
    public List<Notification> findAll() {
        return notificationService.findAll();
    }

    @PostMapping
    @ApiOperation(value="Enregister un notification", notes="save notification",authorizations = {@Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="notification enregistré")
    })
    public MessageResponse save(@RequestBody Notification notification) {
        return notificationService.save(notification);
    }

    @PutMapping
    @ApiOperation(value="mettre à jour un notification", notes="update notification")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="notification mis à jour")
    })
    public MessageResponse update(@RequestBody Notification notification) {
        return notificationService.update(notification);
    }

    @GetMapping("/{code}")
    @ApiOperation(value="Trouver un notification par id", notes="find notification par id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="notification trouvé")
    })
    public Notification findById(@PathVariable("code") Long id) {
        return notificationService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="supprimer un notification", notes="delete notification")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="notification supprimé")
    })
    public MessageResponse delete(@PathVariable Long id) {
        return notificationService.delete(id);
    }
}


