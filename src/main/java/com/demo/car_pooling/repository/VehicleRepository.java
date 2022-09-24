package com.demo.car_pooling.repository;

import com.demo.car_pooling.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VehicleRepository {

    private List<Vehicle> vehicles;

    public VehicleRepository() {
        this.vehicles = new ArrayList<>();
    }

    public synchronized Vehicle saveVehicle(Vehicle vehicle){

        UUID uuid = UUID.randomUUID();
        vehicle.setId(uuid.toString());

        this.vehicles.add(vehicle);

        return vehicle;

    }

    public synchronized Vehicle findByRegNo(String regNo){

        for(Vehicle vehicle : vehicles){
            if(vehicle.getRegNo().equals(regNo)){
                return vehicle;
            }
        }

        return null;

    }

}
