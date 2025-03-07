package com.example.social_media_app.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserEntity sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private UserEntity receiver;
}
