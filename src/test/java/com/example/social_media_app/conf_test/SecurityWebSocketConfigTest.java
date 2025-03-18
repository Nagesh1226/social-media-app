package com.example.social_media_app.conf_test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import com.example.social_media_app.conf.SecurityConfig;
import com.example.social_media_app.conf.WebSocketConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.StompWebSocketEndpointRegistration;

@ExtendWith(MockitoExtension.class)
public class SecurityWebSocketConfigTest {

    @InjectMocks
    private SecurityConfig securityConfig;
    @Autowired
    private ApplicationContext context;
    @InjectMocks
    private WebSocketConfig webSocketConfig;

    @Mock
    private HttpSecurity httpSecurity;

    @Mock
    private MessageBrokerRegistry messageBrokerRegistry;

    @Mock
    private StompEndpointRegistry stompEndpointRegistry;

    @Test
    void testSecurityFilterChain() throws Exception {
        // Create a mock HttpSecurity object
        HttpSecurity httpSecurity = Mockito.mock(HttpSecurity.class);

        // Mock the behavior of the HttpSecurity methods
        when(httpSecurity.csrf(any())).thenReturn(httpSecurity);
        when(httpSecurity.authorizeHttpRequests(any())).thenReturn(httpSecurity);

        // Create a valid DefaultSecurityFilterChain object
        SecurityFilterChain filterChain = new DefaultSecurityFilterChain(AnyRequestMatcher.INSTANCE);

        // Mock the build() method to return the valid filter chain
        when(httpSecurity.build()).thenReturn((DefaultSecurityFilterChain) filterChain);

        // Create an instance of your SecurityConfig class
        SecurityConfig securityConfig = new SecurityConfig();

        // Call the method under test
        SecurityFilterChain result = securityConfig.securityFilterChain(httpSecurity);

        // Verify that the filter chain is not null
        assertNotNull(result);

        // Optionally, verify that the expected methods were called
        verify(httpSecurity).csrf(any());
        verify(httpSecurity).authorizeHttpRequests(any());
        verify(httpSecurity).build();
    }

    @Test
    void testUserDetailsService() {
        UserDetailsService userDetailsService = securityConfig.userDetailsService();
        assertNotNull(userDetailsService);
        assertTrue(userDetailsService instanceof InMemoryUserDetailsManager);
    }

    @Test
    void testConfigureMessageBroker() {
        assertDoesNotThrow(() -> webSocketConfig.configureMessageBroker(messageBrokerRegistry));
    }

    @Test
    void testRegisterStompEndpoints() {
        // Retrieve the WebSocketConfig bean from the application context
      // WebSocketConfig webSocketConfig = context.getBean(WebSocketConfig.class);

        // Create a mock StompEndpointRegistry
        StompEndpointRegistry stompEndpointRegistry = Mockito.mock(StompEndpointRegistry.class);

        // Test the method
      // assertDoesNotThrow(() -> webSocketConfig.registerStompEndpoints(stompEndpointRegistry));
    }
}
