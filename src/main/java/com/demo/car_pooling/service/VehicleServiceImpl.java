package com.demo.car_pooling.service;

import com.demo.car_pooling.exception.VehicleException;
import com.demo.car_pooling.model.Vehicle;
import com.demo.car_pooling.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle addVehicle(Vehicle vehicle) throws VehicleException {

        Vehicle existingVehicle = vehicleRepository.findByRegNo(vehicle.getRegNo());

        if(existingVehicle!=null){
            throw new VehicleException("Vehicle Already Exist with given registration number " + vehicle.getRegNo());
        }

        return vehicleRepository.saveVehicle(vehicle);
    }

}
