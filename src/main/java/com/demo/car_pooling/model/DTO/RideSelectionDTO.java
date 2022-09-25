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

    public RideSelectionDTO(String userId, String origin, String destination, Integer requiredSeats, String preferredVehicle, Boolean mostVacant) {
        this.userId = userId;
        this.origin = origin;
        this.destination = destination;
        this.requiredSeats = requiredSeats;
        this.preferredVehicle = preferredVehicle;
        this.mostVacant = mostVacant;
    }

    private List<Ride> availableRides;

}
