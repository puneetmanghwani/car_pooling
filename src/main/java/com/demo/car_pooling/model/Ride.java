package com.demo.car_pooling.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ride {

    private User byUser;

    private Vehicle vehicle;

    private String origin;

    private String destination;

    private Integer availableSeats;

    private Boolean rideTaken;

    private User forUser;

    private Boolean isCompleted;

}
