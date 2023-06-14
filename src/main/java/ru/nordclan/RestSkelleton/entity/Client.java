package ru.nordclan.RestSkelleton.entity;

import javax.persistence.*;

import lombok.Data;

import java.util.List;

/**
 * @author Shlokov Andrey
 */
@Entity
@Table(name = "clients")
@Data
public class Client {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages;

}