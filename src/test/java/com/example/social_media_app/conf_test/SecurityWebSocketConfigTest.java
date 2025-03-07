package com.example.social_media_app.conf_test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@ExtendWith(MockitoExtension.class)
public class SecurityWebSocketConfigTest {

    @InjectMocks
    private SecurityConfig securityConfig;

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
        SecurityFilterChain filterChain = securityConfig.securityFilterChain(httpSecurity);
        assertNotNull(filterChain);
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
        assertDoesNotThrow(() -> webSocketConfig.registerStompEndpoints(stompEndpointRegistry));
    }
}
