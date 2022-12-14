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

    private String id;

    private String byUserId;

    private String vehicleRegNo;

    private String vehicleName;

    private String origin;

    private String destination;

    private Integer availableSeats;

    private Boolean rideTaken;

    private String forUserId;

    private Boolean isCompleted;

}
