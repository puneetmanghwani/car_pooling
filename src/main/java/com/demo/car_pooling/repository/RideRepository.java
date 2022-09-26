package com.demo.car_pooling.repository;

import com.demo.car_pooling.model.Ride;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RideRepository {

    private List<Ride> currentRides;

    private List<Ride> completedRides;

    public List<Ride> getCurrentRides() {
        return currentRides;
    }

    public List<Ride> getCompletedRides() {
        return completedRides;
    }

    public RideRepository(){
        this.currentRides = new ArrayList<>();
        this.completedRides = new ArrayList<>();
    }

    public synchronized Ride saveRide(Ride ride){

        UUID uuid = UUID.randomUUID();
        ride.setId(uuid.toString());

        this.currentRides.add(ride);

        return ride;

    }

    public Ride findRideForRegNo(String regNo){

        for(Ride ride : this.currentRides){
            if(ride.getVehicleRegNo().equals(regNo)){
                return ride;
            }
        }

        return null;

    }

    public Ride findRideById(String rideId){
        for(Ride ride : this.currentRides){
            if(ride.getId().equals(rideId)){
                return ride;
            }
        }

        return null;
    }

    public boolean deleteRide(String rideId){

        Ride toBeDeletedRide = null;

        for(Ride ride : this.currentRides){
            if(ride.getId().equals(rideId)){
                toBeDeletedRide = ride;
            }
        }

        if(toBeDeletedRide!=null){
            this.currentRides.remove(toBeDeletedRide);
            return true;
        }

        return false;

    }

    public List<Ride> findRideBetweenSourceAndDestination(String origin, String destination, Integer requiredSeats){

        List<Ride> finalRides = new ArrayList<>();

        for(Ride ride : currentRides){
            if(ride.getRideTaken()==false && ride.getOrigin().equals(origin) && ride.getDestination().equals(destination) && ride.getAvailableSeats()>=requiredSeats){
                finalRides.add(ride);
            }
        }

        return finalRides;


    }

    public List<Ride> findRideBySource(String origin, Integer requiredSeats){

        List<Ride> finalRides = new ArrayList<>();

        for(Ride ride : currentRides){
            if(ride.getOrigin().equals(origin) && ride.getAvailableSeats()>=requiredSeats){
                finalRides.add(ride);
            }
        }

        return finalRides;

    }

    public List<Ride> findRideByDestination(String destination, Integer requiredSeats){

        List<Ride> finalRides = new ArrayList<>();

        for(Ride ride : currentRides){
            if(ride.getDestination().equals(destination) && ride.getAvailableSeats()>=requiredSeats){
                finalRides.add(ride);
            }
        }

        return finalRides;

    }

    public Ride updateRide(Ride updatedRide){

        for(Ride ride : currentRides){
            if(ride.getId().equals(updatedRide.getId())){
                //TODO: will add more fields and also check if they changed. no need for now for this feature
                ride.setForUserId(updatedRide.getForUserId());
                ride.setRideTaken(updatedRide.getRideTaken());
                ride.setIsCompleted(updatedRide.getIsCompleted());
            }
        }

        return updatedRide;

    }

    public Ride addCompletedRide(Ride ride){

        this.completedRides.add(ride);

        return ride;

    }

    public List<Ride> getCompletedRidesTakenByUser(String userId){

        List<Ride> finalRides = new ArrayList<>();

        for(Ride ride : this.completedRides){
            if(ride.getForUserId()!=null && ride.getForUserId().equals(userId)){
                finalRides.add(ride);
            }
        }

        return finalRides;

    }

    public List<Ride> getCompletedRidesOfferedByUser(String userId){

        List<Ride> finalRides = new ArrayList<>();

        for(Ride ride : this.completedRides){
            if(ride.getByUserId().equals(userId)){
                finalRides.add(ride);
            }
        }

        return finalRides;

    }


}
