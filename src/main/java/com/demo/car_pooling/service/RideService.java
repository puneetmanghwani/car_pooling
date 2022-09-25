package com.demo.car_pooling.service;

import com.demo.car_pooling.exception.RideException;
import com.demo.car_pooling.model.DTO.RideSelectionDTO;
import com.demo.car_pooling.model.DTO.RideStat;
import com.demo.car_pooling.model.DTO.UserRideDTO;
import com.demo.car_pooling.model.Ride;

import java.util.List;

public interface RideService {

    public Ride offerRide(UserRideDTO userRideDTO) throws RideException;

    public RideSelectionDTO selectRide(RideSelectionDTO rideSelectionDTO) throws RideException;

    public boolean endRide(String rideId);

    public List<Ride> getRidesTakenByUser(String userId);

    public List<Ride> getRidesOfferedByUser(String userId);

    public RideStat getRidesStatForUser(String userId);

}
