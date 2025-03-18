package com.example.social_media_app.conf_test;



import com.example.social_media_app.conf.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SecurityConfigTest {

    @Test
    void testSecurityFilterChain() throws Exception {
        // Arrange
        SecurityConfig securityConfig = new SecurityConfig();
        HttpSecurity httpSecurity = Mockito.mock(HttpSecurity.class);

        // Mock the behavior of HttpSecurity methods
        when(httpSecurity.csrf(any())).thenReturn(httpSecurity);
        when(httpSecurity.authorizeHttpRequests(any())).thenReturn(httpSecurity);
        when(httpSecurity.sessionManagement(any())).thenReturn(httpSecurity);
       // when(httpSecurity.build()).thenReturn((DefaultSecurityFilterChain) mock(SecurityFilterChain.class));

        // Act
        SecurityFilterChain filterChain = securityConfig.securityFilterChain(httpSecurity);

        // Assert
       // assertNotNull(filterChain);

        // Verify that the expected methods were called
        verify(httpSecurity).csrf(any());
        verify(httpSecurity).authorizeHttpRequests(any());
        verify(httpSecurity).sessionManagement(any());
        verify(httpSecurity).build();
    }

    @Test
    void testUserDetailsService() {
        // Arrange
        SecurityConfig securityConfig = new SecurityConfig();

        // Act
        UserDetailsService userDetailsService = securityConfig.userDetailsService();
        UserDetails user = userDetailsService.loadUserByUsername("user");

        // Assert
        assertNotNull(userDetailsService);
        assertNotNull(user);
        assertEquals("user", user.getUsername());
        assertNotEquals("password", user.getPassword());
        assertTrue(user.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")));
    }
}