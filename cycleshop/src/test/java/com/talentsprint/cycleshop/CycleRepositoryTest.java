package com.talentsprint.cycleshop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.talentsprint.cycleshop.entity.Cycle;
import com.talentsprint.cycleshop.repository.CycleRepository;


@DataJpaTest
public class CycleRepositoryTest {

    @Mock
    private CycleRepository cycleRepository;

    @Test
    public void testFindBySomeCriteria() {
        Cycle cycle = new Cycle();
        cycle.setId(100);
        cycle.setBrand("bappa");
        cycle.setNumBorrowed(0);
        cycle.setStock(100);
        cycleRepository.save(cycle);

        // Retrieve the cycle by ID and use Optional
        Optional<Cycle> optionalCycle = cycleRepository.findById((long) 100);

        // Check if the cycle is present
        assertFalse(optionalCycle.isPresent());

        // Access the cycle and perform assertions
        Cycle retrievedCycle = optionalCycle.get();
        assertEquals("bappa", retrievedCycle.getBrand());
        assertEquals(0, retrievedCycle.getNumBorrowed());
        assertEquals(100, retrievedCycle.getStock());
    }
}
