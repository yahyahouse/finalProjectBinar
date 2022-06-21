package com.binar.dummyproject.controller;

import com.binar.dummyproject.config.JwtUtils;
import com.binar.dummyproject.config.SignupRequest;
import com.binar.dummyproject.enumeration.ERole;
import com.binar.dummyproject.model.*;
import com.binar.dummyproject.repository.RoleRepository;
import com.binar.dummyproject.repository.UsersRepository;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(
            @Valid
            @Schema(example = "{" +
                    "\"username\":\"seller\"," +
                    "\"password\":\"seller\"" +
                    "}")
            @RequestBody Map<String, Object> login) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.get("username"), login.get("password")));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUserId(), userDetails.getUsername(), userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(
            @Valid
            @Schema(example = "{" +
                    "\"username\":\"seller\"," +
                    "\"email\":\"seller@gmail.com\"," +
                    "\"password\":\"seller\"," +
                    "\"address\":\"Jl. Mermaidman\"," +
                    "\"usersImage\":\"1\"," +
                    "\"city\":\"Ambon\"," +
                    "\"phone\":\"0877777773\"," +
                    "\"role\":[\"SELLER\"]" +
                    "}")
            @RequestBody SignupRequest signupRequest) {
        Boolean usernameExist = usersRepository.existsByUsername(signupRequest.getUsername());
        if(Boolean.TRUE.equals(usernameExist)) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        Boolean emailExist = usersRepository.existsByEmail(signupRequest.getEmail());
        if(Boolean.TRUE.equals(emailExist)) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        Boolean noHPExist = usersRepository.existsByPhone(signupRequest.getPhone());
        if(Boolean.TRUE.equals(noHPExist)) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Phone Number is already taken!"));
        }

        Users users = new Users(signupRequest.getUsername(), signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()), signupRequest.getUsersImage(), signupRequest.getAddress(),
                signupRequest.getPhone(), signupRequest.getCity());

        Set<String> strRoles = signupRequest.getRole();
        Set<Roles> roles = new HashSet<>();

        if(strRoles == null) {
            Roles role = roleRepository.findByName(ERole.SELLER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(role);
        } else {
            strRoles.forEach(role -> {
                Roles roles1 = roleRepository.findByName(ERole.valueOf(role))
                        .orElseThrow(() -> new RuntimeException("Error: Role " + role + " is not found"));
                roles.add(roles1);
            });
        }
        users.setRoles(roles);
        usersRepository.save(users);
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }
}