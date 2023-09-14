package com.talentsprint.cycleshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.talentsprint.cycleshop.service.CycleService;
import com.talentsprint.cycleshop.entity.Cycle; // Assuming you have a Cycle model class.

@RestController
@RequestMapping("/api/cycles")
public class CycleController {

    @Autowired
    private CycleService cycleService;

    @PostMapping("/{id}/borrow")
    public String borrowCycle(@PathVariable long id, @RequestParam(required = false, defaultValue = "1") int count) {
        cycleService.borrowCycle(id, count);
        return "Cycle borrowed successfully";
    }
    @PostMapping("/{id}/return")
    public String returnCycle(@PathVariable long id, @RequestParam(required = false, defaultValue = "1") int count) {
        cycleService.returnCycle(id, count);
        return "Cycle returned successfully";
    }

    @PostMapping("/{id}/restock")
    public String restockCycle(@PathVariable long id, @RequestParam(required = false, defaultValue = "1") int count) {
        cycleService.restockBy(id, count);
        return "Cycle restocked successfully";
    }

    @GetMapping("/list")
    public List<Cycle> listAvailableCycles() {
        return cycleService.listAvailableCycles();
    }

    @GetMapping("/{id}")
    public Cycle cycleDetail(@PathVariable long id) {
        return cycleService.findByIdOrThrow404(id);
    }
}
