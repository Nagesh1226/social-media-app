package com.example.social_media_app.controller;

import com.example.social_media_app.entity.Message;
import com.example.social_media_app.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public List<Message> getMessages() {
        return messageService.getAllMessages();
    }

    @PostMapping
    public Message sendMessage(@RequestBody Message message) {
        return messageService.sendMessage(message);
    }
}
