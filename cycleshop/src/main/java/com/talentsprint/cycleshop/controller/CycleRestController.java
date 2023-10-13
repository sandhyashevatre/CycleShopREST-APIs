package com.talentsprint.cycleshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.talentsprint.cycleshop.entity.Cycle;
import com.talentsprint.cycleshop.entity.User;
import com.talentsprint.cycleshop.repository.UserRepository;
import com.talentsprint.cycleshop.service.CycleService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
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

    @PostMapping("/{id}/borrow")
    public List<Cycle> borrowCycles(@PathVariable int id, @RequestParam int count) {
        cycleService.borrowCycle(id, count);
        return all();
    }

    @PostMapping("/{id}/return")
    public List<Cycle> returnCycles(@PathVariable int id, @RequestParam int count) {
        cycleService.returnCycle(id, count);
        return all();
    }

    @PostMapping("/{id}/restock")
    public List<Cycle> restockCycles(@PathVariable int id, @RequestParam int count) {
        cycleService.restockBy(id, count);
        return all();
    }

    @GetMapping("/cycle/list")
    public List<Cycle> all() {
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
}