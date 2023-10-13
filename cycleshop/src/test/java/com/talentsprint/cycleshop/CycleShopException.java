package com.talentsprint.cycleshop;
import com.talentsprint.cycleshop.exception.CycleShopBusinessException;
import com.talentsprint.cycleshop.exception.NotLoggedInException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals; import com.talentsprint.cycleshop.business.GlobalExceptionHandler;
import com.talentsprint.cycleshop.exception.NotLoggedInException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class CycleShopException {

    @Test
    void testCycleShopBusinessException() {
        String errorMessage = "Business exception message";
        CycleShopBusinessException exception = new CycleShopBusinessException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testNotLoggedInException() {
        String errorMessage = "Not logged in exception message";
        NotLoggedInException exception = new NotLoggedInException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    //globalexception
    @WebMvcTest(GlobalExceptionHandler.class)
    class GlobalExceptionHandlerTest {

    @MockBean
    private GlobalExceptionHandler globalExceptionHandler;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(globalExceptionHandler).build();
    }

    @Test
    void testHandleNotLoggedInException() throws Exception {
        // Simulate a NotLoggedInException being thrown
        doThrow(new NotLoggedInException("Not logged in")).when(globalExceptionHandler).handleNotLoggedInException();

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/error"))
            .andExpect(status().is3xxRedirection()) // Ensure a redirection response
            .andReturn().getResponse();

        // Check that the response redirects to the login page
        String redirectedUrl = response.getHeader("Location");
        assert redirectedUrl != null;
        assert redirectedUrl.endsWith("/loginpage");
    }
    
}

}


