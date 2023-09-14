package com.talentsprint.cycleshop.controller;

import java.security.Principal;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talentsprint.cycleshop.entity.Cycle;
import com.talentsprint.cycleshop.entity.User;
import com.talentsprint.cycleshop.repository.UserRepository;
import com.talentsprint.cycleshop.service.CycleService;

@RestController
@RequestMapping("/api")
public class CycleRestController {
    
    @Autowired
    private CycleService cycleService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/health")
    public String checkhealth() {
        return "healthy";
    }

    @GetMapping("/cycle/list")
    public List<Cycle> all(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        System.out.println(jwt.getClaimAsString("scope"));
        return cycleService.listAvailableCycles();
    }
    @PostMapping("/register")

    public ResponseEntity<String> registerUser(@RequestBody User user) {

        try {

            if (userRepository.existsByName(user.getName())) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");

            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));

            userRepository.save(user);

            return ResponseEntity.ok("User registered successfully");

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());

        }

    }

 

    @GetMapping("/login")

    public String LoginForm(Model model) {

        return "userLogin";

    }
}