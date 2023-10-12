package com.talentsprint.cycleshop;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.talentsprint.cycleshop.controller.CycleRestController;
import com.talentsprint.cycleshop.repository.UserRepository;
import com.talentsprint.cycleshop.service.CycleService;

@SpringBootTest
@AutoConfigureMockMvc
public class CycleRestControllerTest {

    @InjectMocks
    private CycleRestController cycleRestController;

    @Mock
    private CycleService cycleService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MockMvc mockMvc;

    // @Test
    // public void testCheckHealth() throws Exception {
    //     mockMvc.perform(MockMvcRequestBuilders.get("/api/health"))
    //             .andExpect(MockMvcResultMatchers.status().isOk())
    //             .andExpect(MockMvcResultMatchers.content().string("healthy"));
    // }
}