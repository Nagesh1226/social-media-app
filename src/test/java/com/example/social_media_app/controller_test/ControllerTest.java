package com.example.social_media_app.controller_test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.social_media_app.controller.MessageController;
import com.example.social_media_app.controller.PostController;
import com.example.social_media_app.controller.UserController;
import com.example.social_media_app.entity.Message;
import com.example.social_media_app.entity.Post;
import com.example.social_media_app.entity.UserEntity;
import com.example.social_media_app.service.MessageService;
import com.example.social_media_app.service.PostService;
import com.example.social_media_app.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;

    @Mock
    private PostService postService;
    @InjectMocks
    private PostController postController;

    @Mock
    private MessageService messageService;
    @InjectMocks
    private MessageController messageController;

    private UserEntity user;
    private Post post;
    private Message message;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController, postController, messageController).build();

        user = new UserEntity();
        user.setId(1L);
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setPassword("password123");

        post = new Post();
        post.setId(1L);
        post.setContent("Sample Post");
        post.setImageUrl("http://example.com/image.jpg");
        post.setUser(user);

        message = new Message();
        message.setId(1L);
        message.setContent("Hello");
        message.setSender(user);
        message.setReceiver(user);
    }

    @Test
    void testGetUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(Arrays.asList(user));
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void testCreateUser() throws Exception {
        when(userService.createUser(any(UserEntity.class))).thenReturn(user);
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testUser"));
    }

    @Test
    void testGetPosts() throws Exception {
        when(postService.getAllPosts()).thenReturn(Arrays.asList(post));
        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void testCreatePost() throws Exception {
        when(postService.createPost(any(Post.class))).thenReturn(post);
        mockMvc.perform(post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Sample Post"));
    }

    @Test
    void testGetMessages() throws Exception {
        when(messageService.getAllMessages()).thenReturn(Arrays.asList(message));
        mockMvc.perform(get("/api/messages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void testSendMessage() throws Exception {
        when(messageService.sendMessage(any(Message.class))).thenReturn(message);
        mockMvc.perform(post("/api/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(message)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Hello"));
    }
}
