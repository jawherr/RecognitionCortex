package com.neocortex.recognitioncortex.controller.user;

import com.neocortex.recognitioncortex.dtos.UtilisateurDTO;
import com.neocortex.recognitioncortex.exception.UserNotFoundException;
import com.neocortex.recognitioncortex.service.CortexAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class UserController {
    //New Part
    private CortexAccountService cortexAccountService;
    @GetMapping("/users")
    public List<UtilisateurDTO> users(){
        return cortexAccountService.listUsers();
    }
    @GetMapping("/users/search")
    public List<UtilisateurDTO> searchUsers(@RequestParam(name = "keyword",defaultValue = "") String keyword){
        return cortexAccountService.searchUsers("%"+keyword+"%");
    }
    @GetMapping("/users/{id}")
    public UtilisateurDTO getUser(@PathVariable(name = "id") Long userId) throws UserNotFoundException {
        return cortexAccountService.getUser(userId);
    }
    @PostMapping("/users")
    public UtilisateurDTO saveUser(@RequestBody UtilisateurDTO utilisateurDTO){
        return cortexAccountService.saveUser(utilisateurDTO);
    }
    @PutMapping("/users/{userId}")
    public UtilisateurDTO updateUser(@PathVariable Long userId, @RequestBody UtilisateurDTO utilisateurDTO){
        utilisateurDTO.setId(userId);
        return cortexAccountService.updateUser(utilisateurDTO);
    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id){
        cortexAccountService.deleteUser(id);
    }
}
