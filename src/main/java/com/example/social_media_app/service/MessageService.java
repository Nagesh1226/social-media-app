package com.example.social_media_app.service;

import com.example.social_media_app.entity.Message;
import com.example.social_media_app.repo.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message sendMessage(Message message) {
        return messageRepository.save(message);
    }
}
