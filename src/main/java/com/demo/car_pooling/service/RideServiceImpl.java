package com.demo.car_pooling.service;

import com.demo.car_pooling.exception.RideException;
import com.demo.car_pooling.model.DTO.RideSelectionDTO;
import com.demo.car_pooling.model.DTO.RideStat;
import com.demo.car_pooling.model.DTO.UserRideDTO;
import com.demo.car_pooling.model.Ride;
import com.demo.car_pooling.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RideServiceImpl implements RideService{

    @Autowired
    private UserService userService;

    @Autowired
    private RideRepository rideRepository;

    @Override
    public Ride offerRide(UserRideDTO userRideDTO) throws RideException {

        String regNo = userRideDTO.getVehicleRegNo();

        if(rideExistForVehicle(regNo)){
            throw new RideException("One Offered Ride Already Exist for this Vehicle " + regNo);
        }

        Ride ride = new Ride(null, userRideDTO.getByUserId(), regNo, userRideDTO.getVehicleName(), userRideDTO.getOrigin(), userRideDTO.getDestination(), userRideDTO.getAvailableSeats(),
                false, null, false);

        return rideRepository.saveRide(ride);


    }

    @Override
    public RideSelectionDTO selectRide(RideSelectionDTO rideSelectionDTO) throws RideException {

        Integer requiredSeats = rideSelectionDTO.getRequiredSeats();

        if(requiredSeats<1 || requiredSeats>2){
            throw new RideException("Ride should be request for at least 1 and maximum 2 people");
        }

        List<Ride> rides = getRidesBetweenSourceAndDestination(rideSelectionDTO.getOrigin(), rideSelectionDTO.getDestination(), rideSelectionDTO.getRequiredSeats());

        if(rides.size()==0){
            //TODO: think
            rides = getOverLappingRidesBetweenSourceAndDestination(rideSelectionDTO.getOrigin(), rideSelectionDTO.getDestination(), rideSelectionDTO.getRequiredSeats());
        }

        Ride desiredRide = getRideBasedOnSelectionStrategy(rides, rideSelectionDTO);
        desiredRide = saveUserForRide(desiredRide, rideSelectionDTO.getUserId());


        rideSelectionDTO.setDesiredRide(desiredRide);
        rideSelectionDTO.setAvailableRides(rides);

        return rideSelectionDTO;
    }

    @Override
    public boolean endRide(String rideId){

        Ride ride = rideRepository.findRideById(rideId);
        ride.setIsCompleted(true);

        ride = rideRepository.updateRide(ride);

        //TODO: we should remove this ride from current rides

        Ride completedRide = rideRepository.addCompletedRide(ride);

        return true;
    }

    @Override
    public List<Ride> getRidesTakenByUser(String userId) {

        List<Ride> rides = rideRepository.getCompletedRidesTakenByUser(userId);

        return rides;

    }

    @Override
    public List<Ride> getRidesOfferedByUser(String userId) {
        List<Ride> rides = rideRepository.getCompletedRidesOfferedByUser(userId);

        return rides;
    }

    @Override
    public RideStat getRidesStatForUser(String userId) {

        List<Ride> ridesTakenByUser = getRidesTakenByUser(userId);
        List<Ride> ridesOfferedByUser = getRidesOfferedByUser(userId);

        RideStat rideStat = new RideStat(ridesTakenByUser, ridesOfferedByUser);

        return rideStat;

    }

    private Ride saveUserForRide(Ride ride, String userId){

        ride.setForUserId(userId);
        ride = rideRepository.updateRide(ride);

        return ride;
    }

    private boolean rideExistForVehicle(String regNo){

        Ride ride = rideRepository.findRideForRegNo(regNo);

        if(ride!=null && ! ride.getIsCompleted()){
            return true;
        }

        return false;

    }

    private List<Ride> getRidesBetweenSourceAndDestination(String origin, String destination, Integer requiredSeats){

        List<Ride> rides = rideRepository.findRideBetweenSourceAndDestination(origin, destination, requiredSeats);

        return rides;

    }

    private List<Ride> getOverLappingRidesBetweenSourceAndDestination(String origin, String destination, Integer requiredSeats){

        List<Ride> sourceRides = rideRepository.findRideBySource(origin, requiredSeats);
        List<Ride> destinationRides = rideRepository.findRideByDestination(destination, requiredSeats);

        //TODO: think of better way
        return null;
    }

    private Ride getRideBasedOnSelectionStrategy(List<Ride> rides, RideSelectionDTO rideSelectionDTO){

        Ride finalRide = null;
        String preferredVehicle = rideSelectionDTO.getPreferredVehicle();
        Boolean needMostVacant = rideSelectionDTO.getMostVacant();
        Integer currentRideVacancy = 0;

        //given priority to preferred vehicle
        for(Ride ride : rides){
            if(checkIfPreferredVehicle(ride.getVehicleName(), preferredVehicle)){
                finalRide = ride;
            } else if (checkIfMostVacantVehicle(needMostVacant, currentRideVacancy, ride.getAvailableSeats())) {
                finalRide = ride;
                currentRideVacancy = ride.getAvailableSeats();
            }
        }

        return finalRide;

    }

    private boolean checkIfPreferredVehicle(String currentRideVehicle, String preferredVehicle){
        if(preferredVehicle!=null && currentRideVehicle.equals(preferredVehicle)){
            return true;
        }

        return false;
    }

    private boolean checkIfMostVacantVehicle(Boolean needMostVacant, Integer currentRideVacancy, Integer availableSeats){

        if(needMostVacant!=null && availableSeats>currentRideVacancy){
            return true;
        }

        return false;

    }
}
