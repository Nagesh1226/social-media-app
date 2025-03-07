package com.example.social_media_app.service_test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.social_media_app.entity.Message;
import com.example.social_media_app.entity.Post;
import com.example.social_media_app.entity.UserEntity;
import com.example.social_media_app.repo.MessageRepository;
import com.example.social_media_app.repo.PostRepository;
import com.example.social_media_app.repo.UserRepository;
import com.example.social_media_app.service.MessageService;
import com.example.social_media_app.service.PostService;
import com.example.social_media_app.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ServiceLayerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageService messageService;

    private UserEntity user;
    private Post post;
    private Message message;

    @BeforeEach
    void setUp() {
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
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        List<UserEntity> users = userService.getAllUsers();
        assertEquals(1, users.size());
        assertEquals("testUser", users.get(0).getUsername());
    }

    @Test
    void testCreateUser() {
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);
        UserEntity savedUser = userService.createUser(user);
        assertNotNull(savedUser);
        assertEquals("testUser", savedUser.getUsername());
    }

    @Test
    void testGetAllPosts() {
        when(postRepository.findAll()).thenReturn(Arrays.asList(post));
        List<Post> posts = postService.getAllPosts();
        assertEquals(1, posts.size());
        assertEquals("Sample Post", posts.get(0).getContent());
    }

    @Test
    void testCreatePost() {
        when(postRepository.save(any(Post.class))).thenReturn(post);
        Post savedPost = postService.createPost(post);
        assertNotNull(savedPost);
        assertEquals("Sample Post", savedPost.getContent());
    }

    @Test
    void testGetAllMessages() {
        when(messageRepository.findAll()).thenReturn(Arrays.asList(message));
        List<Message> messages = messageService.getAllMessages();
        assertEquals(1, messages.size());
        assertEquals("Hello", messages.get(0).getContent());
    }

    @Test
    void testSendMessage() {
        when(messageRepository.save(any(Message.class))).thenReturn(message);
        Message sentMessage = messageService.sendMessage(message);
        assertNotNull(sentMessage);
        assertEquals("Hello", sentMessage.getContent());
    }
}
