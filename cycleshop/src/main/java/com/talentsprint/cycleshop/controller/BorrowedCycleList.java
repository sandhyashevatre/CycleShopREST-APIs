// package com.talentsprint.cycleshop.controller;

// import java.security.Provider.Service;

// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/api")
// public class BorrowedCycleList {

//     // Autowire your service or repository to fetch borrowed cycles
//     private final Service cycleService;

//     @Autowired
//     public BorrowedCycleList(Service cycleService) {
//         this.cycleService = cycleService;
//     }
//     @GetMapping("/cycle/borrowed")
//     public ResponseEntity<List<Cycle>> getBorrowedCycles() {
//         List<Cycle> borrowedCycles = cycleService.getBorrowedCycles();
//         return ResponseEntity.ok(borrowedCycles);
//     }
// }
