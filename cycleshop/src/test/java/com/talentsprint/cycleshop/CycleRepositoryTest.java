// package com.talentsprint.cycleshop;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

// import com.talentsprint.cycleshop.entity.Cycle;
// import com.talentsprint.cycleshop.repository.CycleRepository;

// @DataJpaTest
// public class CycleRepositoryTest {

//     @Autowired
//     private CycleRepository cycleRepository;

//     @Test
//     public void testFindBySomeCriteria() {
//         Cycle cycle = new Cycle();
//         cycle.setId(100);
//         cycle.setBrand("mental");
//         cycle.setNumBorrowed(0);
//         cycle.setStock(100);
//         cycleRepository.save(cycle);
//         assertEquals("mental",cycleRepository.findById((long)100).get().getBrand());
//         assertEquals(0,cycleRepository.findById((long)100).get().getNumBorrowed());
//         assertEquals(100,cycleRepository.findById((long)100).get().getStock());
//     }
// }
