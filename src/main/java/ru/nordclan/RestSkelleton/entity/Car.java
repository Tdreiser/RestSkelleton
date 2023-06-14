package ru.nordclan.RestSkelleton.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Shlokov Andrey
 */
@Entity
@Data
@Table(name = "cars")
public class Car {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "manufactureyear")
    private int yearOfManufacture;
    @Column(name = "mark")
    private String mark;
    @Column(name = "model")
    private String model;

}
