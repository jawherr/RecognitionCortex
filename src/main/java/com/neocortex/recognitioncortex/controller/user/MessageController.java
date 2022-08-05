package com.neocortex.recognitioncortex.controller.user;

import com.neocortex.recognitioncortex.entities.Message;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.service.impl.MessageServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/message")
@PreAuthorize("hasRole('ROLE_USER')")
public class MessageController {

    @Autowired
    private MessageServiceImpl messageService;

    @GetMapping
    @ApiOperation(value="Trouver tous les message", notes="find message")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="message trouvé")
    })
    public List<Message> findAll() {
        return messageService.findAll();
    }

    @PostMapping
    @ApiOperation(value="Enregister un message", notes="save message",authorizations = {@Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="message enregistré")
    })
    public MessageResponse save(@RequestBody Message message) {
        return messageService.save(message);
    }

    @PutMapping
    @ApiOperation(value="mettre à jour un message", notes="update message")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="message mis à jour")
    })
    public MessageResponse update(@RequestBody Message message) {
        return messageService.update(message);
    }

    @GetMapping("/{code}")
    @ApiOperation(value="Trouver un message par id", notes="find message par id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="message trouvé")
    })
    public Message findById(@PathVariable("code") Long id) {
        return messageService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="supprimer un message", notes="delete message")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="message supprimé")
    })
    public MessageResponse delete(@PathVariable Long id) {
        return messageService.delete(id);
    }
}

