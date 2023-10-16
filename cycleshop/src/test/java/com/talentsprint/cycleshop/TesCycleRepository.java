package com.talentsprint.cycleshop;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talentsprint.cycleshop.entity.Cycle;

public interface TesCycleRepository extends JpaRepository< Cycle, Integer> {
    
}
