package com.demo.car_pooling.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Vehicle {

    private String id;

    private String userId;

    private String name;

    private String regNo;


}
