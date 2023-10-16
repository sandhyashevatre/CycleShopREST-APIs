package com.talentsprint.cycleshop;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.talentsprint.cycleshop.business.LoginBody;
import com.talentsprint.cycleshop.controller.APIAuthController;
import com.talentsprint.cycleshop.controller.CycleRestController;
import com.talentsprint.cycleshop.entity.Cycle;
import com.talentsprint.cycleshop.repository.UserRepository;
import com.talentsprint.cycleshop.service.CycleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.test.web.servlet.setup.SecurityMockMvcConfigurers;
import java.util.Collections;
import java.util.List;
import static org.mockito.Mockito.when;

class CycleRestControllerTest {

    @InjectMocks
    private CycleRestController cycleRestController;

    @Mock
    private CycleService cycleService;

    @Mock
    private UserRepository userRepository;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cycleRestController).build();
    }

    @Test
    void testCheckHealth() throws Exception {
        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("healthy"));
    }

    @Test
    void testBorrowCycles() throws Exception {
       
        Cycle cycle = new Cycle(); // Prepare a sample cycle and user
        cycle.setId(1);
        
        when(cycleService.borrowCycle(1, 3)).thenReturn(cycle);// Mock the service method
       
        mockMvc.perform(post("/api/1/borrow") // Send a request to borrow cycles
                .param("count", "3"))
                .andExpect(status().isOk());
    }

    @Test
    void testReturnCycles() throws Exception {
        
        Cycle cycle = new Cycle();
        cycle.setId(1);
        
        when(cycleService.returnCycle(1, 3)).thenReturn(cycle);
        
        mockMvc.perform(post("/api/1/return")
                .param("count", "3"))
                .andExpect(status().isOk());
    }

    @Test
    void testRestockCycles() throws Exception {
       
        Cycle cycle = new Cycle();
        cycle.setId(1);
      
        when(cycleService.restockBy(1, 3)).thenReturn(cycle);
        
        mockMvc.perform(post("/api/1/restock")
                .param("count", "3"))
                .andExpect(status().isOk());
    }

    // Auth
    @SpringBootTest
    class APIAuthControllerTest {

        private MockMvc mockMvc;

        @MockBean
        private AuthenticationManager authenticationManager;

        @MockBean
        private JwtEncoder jwtEncoder;

        // @BeforeEach
        // public void setup() {
        // this.mockMvc = MockMvcBuilders
        // .standaloneSetup(new APIAuthController())
        // .apply(SecurityMockMvcConfigurers.springSecurity())
        // .build();
        // }

        // @Test
        // public void testGenerateTokenWithValidCredentials() throws Exception {
        // // Mock successful authentication
        // when(authenticationManager.authenticate(new
        // UsernamePasswordAuthenticationToken("testuser", "testpassword")))
        // .thenReturn(new UsernamePasswordAuthenticationToken("testuser",
        // "testpassword", Collections.emptyList()));

        // // Mock JWT token generation
        // when(jwtEncoder.encode(Mockito.any())).thenReturn("VALID_JWT_TOKEN");

        // // Create a LoginBody with valid credentials
        // LoginBody loginBody = new LoginBody("testuser", "testpassword");

        // mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/token")
        // .contentType("application/json")
        // .content(new ObjectMapper().writeValueAsString(loginBody)))
        // .andExpect(MockMvcResultMatchers.status().isOk())
        // .andExpect(MockMvcResultMatchers.content().string("VALID_JWT_TOKEN"));
        // }

        // @Test
        // public void testGenerateTokenWithInvalidCredentials() throws Exception {
        // // Mock authentication failure
        // when(authenticationManager.authenticate(new
        // UsernamePasswordAuthenticationToken("testuser", "invalidpassword")))
        // .thenThrow(new BadCredentialsException("Invalid credentials"));

        // // Create a LoginBody with invalid credentials
        // LoginBody loginBody = new LoginBody("testuser", "invalidpassword");

        // mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/token")
        // .contentType("application/json")
        // .content(new ObjectMapper().writeValueAsString(loginBody)))
        // .andExpect(MockMvcResultMatchers.status().isUnauthorized())
        // .andExpect(MockMvcResultMatchers.content().string(""));
        // }
    }
}