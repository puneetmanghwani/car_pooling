package com.demo.car_pooling.model.DTO;

import com.demo.car_pooling.model.Ride;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideSelectionDTO {

    private String userId;

    private String origin;

    private String destination;

    private Integer requiredSeats;

    private String preferredVehicle;

    private Boolean mostVacant;

    private Ride desiredRide;

    private List<Ride> availableRides;

}
