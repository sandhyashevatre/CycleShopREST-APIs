package com.talentsprint.cycleshop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import com.talentsprint.cycleshop.entity.Cycle;
import com.talentsprint.cycleshop.repository.CycleRepository;
import com.talentsprint.cycleshop.service.CycleService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CycleServiceTest {

    @InjectMocks
    private CycleService cycleService;

    @Mock
    private CycleRepository cycleRepository;

    @Test
    void testBorrowCycle() {
        // Create a test cycle
        Cycle testCycle = new Cycle();
        testCycle.setId(1);
        testCycle.setStock(10);
        testCycle.setNumBorrowed(0);

        // Mock cycleRepository behavior
        when(cycleRepository.findById(any())).thenReturn(Optional.of(testCycle));
        when(cycleRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Test borrowing one cycle
        Cycle borrowedCycle = cycleService.borrowCycle(1, 1);

        // Assert the results
        assertEquals(1, borrowedCycle.getNumBorrowed());
    }

    @Test
    void testReturnCycle() {
        // Create a test cycle
        Cycle testCycle = new Cycle();
        testCycle.setId(1);
        testCycle.setStock(10);
        testCycle.setNumBorrowed(5);

        // Mock cycleRepository behavior
        when(cycleRepository.findById(any())).thenReturn(Optional.of(testCycle));
        when(cycleRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Test returning five cycles
        Cycle returnedCycle = cycleService.returnCycle(1, 5);

        // Assert the results
        assertEquals(0, returnedCycle.getNumBorrowed());
    }

    @Test
    void testRestockBy() {
        // Create a test cycle
        Cycle testCycle = new Cycle();
        testCycle.setId(1);
        testCycle.setStock(10);

        // Mock cycleRepository behavior
        when(cycleRepository.findById(any())).thenReturn(Optional.of(testCycle));
        when(cycleRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Test restocking five cycles
        Cycle restockedCycle = cycleService.restockBy(1, 5);

        // Assert the results
        assertEquals(15, restockedCycle.getStock());
    }
}
