package ru.nordclan.RestSkelleton.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Shlokov Andrey
 */
@Entity
@Data
@Table(name = "messages")

public class Message {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "message")
    private String message;
}
