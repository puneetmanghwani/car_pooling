package com.demo.car_pooling.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRideDTO {

    private String byUserId;

    private String origin;

    private String destination;

    private Integer availableSeats;

    private String vehicleRegNo;

    private String vehicleName;

}
