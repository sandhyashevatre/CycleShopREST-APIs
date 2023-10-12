package com.talentsprint.cycleshop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.talentsprint.cycleshop.entity.Cycle;
import com.talentsprint.cycleshop.entity.User;

@SpringBootTest
public class CycleEntityTest {

    @Test
    public void testCycleModel() {
        Cycle cycle = new Cycle();
        cycle.setId(1);
        cycle.setBrand("Mountain Bike");
        cycle.setStock(10);
        cycle.setNumBorrowed(3);

        assertEquals(1, cycle.getId());
        assertEquals("Mountain Bike", cycle.getBrand());
        assertEquals(10, cycle.getStock());
        assertEquals(3, cycle.getNumBorrowed());
        assertEquals(7, cycle.getNumAvailable());
    }

    @Test
    public void testUserModel() {
        User user = new User();
        user.setId(1);
        user.setName("John");
        user.setPassword("password");
        user.setRole("USER");

        assertEquals(1, user.getId());
        assertEquals("John", user.getName());
        assertEquals("password", user.getPassword());
        assertEquals("USER", user.getRole());
    }

}

