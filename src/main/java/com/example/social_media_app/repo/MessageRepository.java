package com.example.social_media_app.repo;

import com.example.social_media_app.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {}