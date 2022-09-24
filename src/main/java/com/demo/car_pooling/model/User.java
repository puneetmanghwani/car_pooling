package com.demo.car_pooling.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private String id;

    private String name;

    private String email;

    private Integer age;

    private String gender;

    private List<Vehicle> vehicleData;

    public User(String id, String name, String email, Integer age, String gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
    }
}
