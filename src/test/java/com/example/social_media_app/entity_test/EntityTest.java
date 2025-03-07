package com.example.social_media_app.entity_test;

import static org.junit.jupiter.api.Assertions.*;

import com.example.social_media_app.entity.Message;
import com.example.social_media_app.entity.Post;
import com.example.social_media_app.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EntityTest {
    @Test
    void testUserEntity() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setPassword("password123");

        assertEquals(1L, user.getId());
        assertEquals("testUser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
    }

    @Test
    void testPostEntity() {
        Post post = new Post();
        UserEntity user = new UserEntity();
        user.setId(2L);
        post.setId(1L);
        post.setContent("Sample Post");
        post.setImageUrl("http://example.com/image.jpg");
        post.setUser(user);

        assertEquals(1L, post.getId());
        assertEquals("Sample Post", post.getContent());
        assertEquals("http://example.com/image.jpg", post.getImageUrl());
        assertEquals(2L, post.getUser().getId());
    }

    @Test
    void testMessageEntity() {
        Message message = new Message();
        UserEntity sender = new UserEntity();
        UserEntity receiver = new UserEntity();

        sender.setId(1L);
        receiver.setId(2L);

        message.setId(1L);
        message.setContent("Hello");
        message.setSender(sender);
        message.setReceiver(receiver);

        assertEquals(1L, message.getId());
        assertEquals("Hello", message.getContent());
        assertEquals(1L, message.getSender().getId());
        assertEquals(2L, message.getReceiver().getId());
    }
}
