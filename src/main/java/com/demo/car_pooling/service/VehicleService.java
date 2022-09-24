package com.demo.car_pooling.service;

import com.demo.car_pooling.exception.VehicleException;
import com.demo.car_pooling.model.Vehicle;

public interface VehicleService {

    public Vehicle addVehicle(Vehicle vehicle) throws VehicleException;

}
