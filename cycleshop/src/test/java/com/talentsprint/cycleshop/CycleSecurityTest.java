// package com.talentsprint.cycleshop;

// import com.nimbusds.jwt.JWT;
// import com.talentsprint.cycleshop.service.CustomUserDetailsService;
// import com.talentsprint.cycleshop.service.UserService;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import java.util.Optional;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.mockito.Mockito.when;

// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
// import org.springframework.test.context.TestPropertySource;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
// import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// import java.security.PrivateKey;
// import java.security.interfaces.RSAPrivateKey;
// import java.util.Date;

// @ExtendWith(MockitoExtension.class)
// class CycleSecurityTest {// CustomUserDetailsServiceTest

//     @Mock
//     private UserService userService;

//     @Mock
//     private PasswordEncoder passwordEncoder;

//     @Test
//     void testLoadUserByUsername_UserFound() {
//         CustomUserDetailsService userDetailsService = new CustomUserDetailsService(userService);
//         when(userService.getByName("username"))
//                 .thenReturn(Optional.of(new MyUserEntity("username", "password", "ROLE_USER")));

//         UserDetails userDetails = userDetailsService.loadUserByUsername("username");

//         assertEquals("username", userDetails.getUsername());
//         // Verify other details as needed
//     }

//     @Test
//     void testLoadUserByUsername_UserNotFound() {
//         CustomUserDetailsService userDetailsService = new CustomUserDetailsService(userService);
//         when(userService.getByName("nonexistent")).thenReturn(Optional.empty());

//         assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("nonexistent"));
//     }

//     @Test
//     void testPasswordEncoder() {
//         CustomUserDetailsService userDetailsService = new CustomUserDetailsService(userService);
//         PasswordEncoder passwordEncoder = userDetailsService.passwordEncoder();

//         // Test password encoding and decoding
//         String rawPassword = "password";
//         String encodedPassword = passwordEncoder.encode(rawPassword);
//         assertTrue(passwordEncoder.matches(rawPassword, encodedPassword));
//     }

//     @SpringBootTest // basic-confg
//     @TestPropertySource(properties = {
//             "jwt.public.key=YOUR_PUBLIC_KEY",
//             "jwt.private.key=YOUR_PRIVATE_KEY",
//             "spring.datasource.url=jdbc:h2:mem:testdb",
//             "spring.datasource.driverClassName=org.h2.Driver",
//             "spring.datasource.username=sa",
//             "spring.datasource.password=password",
//             "spring.datasource.initialize=true",
//             "spring.datasource.platform=h2",
//             "spring.jpa.hibernate.ddl-auto=create"
//     })
//     class BasicConfigurationTest {

//         private PrivateKey privateKey; // Load the private key used for signing JWT tokens

//         @Test
//         void testAccessToSecuredEndpointsWithExpiredToken() throws Exception {
//             String expiredToken = generateExpiredJwtToken();

//             mockMvc.perform(MockMvcRequestBuilders.get("/api/cycle/list")
//                     .with(SecurityMockMvcRequestPostProcessors.jwt().jwt(expiredToken)))
//                     .andExpect(MockMvcResultMatchers.status().isUnauthorized());

//             mockMvc.perform(MockMvcRequestBuilders.get("/api/1/borrow")
//                     .with(SecurityMockMvcRequestPostProcessors.jwt().jwt(expiredToken)))
//                     .andExpect(MockMvcResultMatchers.status().isUnauthorized());

//             mockMvc.perform(MockMvcRequestBuilders.get("/api/1/return")
//                     .with(SecurityMockMvcRequestPostProcessors.jwt().jwt(expiredToken)))
//                     .andExpect(MockMvcResultMatchers.status().isUnauthorized());

//             mockMvc.perform(MockMvcRequestBuilders.get("/api/1/restock")
//                     .with(SecurityMockMvcRequestPostProcessors.jwt().jwt(expiredToken)))
//                     .andExpect(MockMvcResultMatchers.status().isUnauthorized());
//         }

//         @Test
//         void testAccessToSecuredEndpointsWithTamperedToken() throws Exception {
//             String tamperedToken = generateTamperedJwtToken();

//             mockMvc.perform(MockMvcRequestBuilders.get("/api/cycle/list")
//                     .with(SecurityMockMvcRequestPostProcessors.jwt().jwt(tamperedToken)))
//                     .andExpect(MockMvcResultMatchers.status().isUnauthorized());

//             mockMvc.perform(MockMvcRequestBuilders.get("/api/1/borrow")
//                     .with(SecurityMockMvcRequestPostProcessors.jwt().jwt(tamperedToken)))
//                     .andExpect(MockMvcResultMatchers.status().isUnauthorized());

//             mockMvc.perform(MockMvcRequestBuilders.get("/api/1/return")
//                     .with(SecurityMockMvcRequestPostProcessors.jwt().jwt(tamperedToken)))
//                     .andExpect(MockMvcResultMatchers.status().isUnauthorized());

//             mockMvc.perform(MockMvcRequestBuilders.get("/api/1/restock")
//                     .with(SecurityMockMvcRequestPostProcessors.jwt().jwt(tamperedToken)))
//                     .andExpect(MockMvcResultMatchers.status().isUnauthorized());
//         }

//         private String generateExpiredJwtToken() {
//             long currentTimeMillis = System.currentTimeMillis();
//             long expirationTimeMillis = currentTimeMillis - 1000; // Token expired 1 second ago
//             return Jwts.builder()
//                     .setSubject("username")
//                     .setIssuedAt(new Date(currentTimeMillis))
//                     .setExpiration(new Date(expirationTimeMillis))
//                     .signWith(privateKey, SignatureAlgorithm.RS256)
//                     .compact();
//         }

//         private String generateTamperedJwtToken() {
//             String validToken = generateValidJwtToken();
//             // Tamper with the token by changing a claim or signature
//             // For example, modify the subject claim
//             return validToken.replace("\"sub\":\"username\"", "\"sub\":\"tampered-username\"");
//         }
//     }

// }
