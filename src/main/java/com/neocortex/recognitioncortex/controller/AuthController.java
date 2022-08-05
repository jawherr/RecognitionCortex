package com.neocortex.recognitioncortex.controller;

import com.neocortex.recognitioncortex.enums.ERole;
import com.neocortex.recognitioncortex.entities.Role;
import com.neocortex.recognitioncortex.entities.Utilisateur;
import com.neocortex.recognitioncortex.reponses.JwtResponse;
import com.neocortex.recognitioncortex.reponses.MessageResponse;
import com.neocortex.recognitioncortex.repository.RoleRepository;
import com.neocortex.recognitioncortex.repository.UtilisateurRepository;
import com.neocortex.recognitioncortex.request.LoginRequest;
import com.neocortex.recognitioncortex.request.SignupRequest;
import com.neocortex.recognitioncortex.security.jwt.JwtUtils;
import com.neocortex.recognitioncortex.service.UtilisateurService;
import com.neocortex.recognitioncortex.service.impl.UtilisateurDetailsImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Api
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UtilisateurService utilisateurService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    @ApiOperation(value="Authentifier un utilisateur", notes="login user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="utilisateur authentifié")
    })
    public ResponseEntity<JwtResponse> signin(@RequestBody LoginRequest loginRequest) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            Utilisateur utilisateur = utilisateurService.findOne(userDetails.getUsername());
            return ResponseEntity.ok(new JwtResponse(jwt,
                    utilisateur.getId(),
                    utilisateur.getUsername(),
                    roles));

    }


    @PostMapping("/signup")
    @ApiOperation(value="enregistrer un utilisateur", notes="register user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message="utilisateur enregistré")
    })
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (utilisateurRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(false,"Failed","Error: Username is already taken!"));
        }
        if (utilisateurRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(false,"Failed","Error: Username is already taken!"));
        }

        // Create new user's account
        Utilisateur user = new Utilisateur(signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getNom(), signUpRequest.getUsername(),
                signUpRequest.getDate_naissance(), signUpRequest.getAddress(),
                signUpRequest.getCreated_at(), signUpRequest.getUpdated_at());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByNom(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);
                    break;
                case "rh":
                    Role rhRole = roleRepository.findByNom(ERole.ROLE_RH)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(rhRole);
                    break;
                case "ce":
                    Role ceRole = roleRepository.findByNom(ERole.ROLE_CE)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(ceRole);
                    break;
                case "fa":
                    Role faRole = roleRepository.findByNom(ERole.ROLE_FA)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(faRole);
                    break;
                default:
                    Role userRole = roleRepository.findByNom(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });

        user.setRoles(roles);
        utilisateurRepository.save(user);
        return ResponseEntity.ok(new MessageResponse(true,"Succés","User registered successfully!"));
    }
}