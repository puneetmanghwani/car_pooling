package com.demo.car_pooling.service;

import com.demo.car_pooling.exception.RideException;
import com.demo.car_pooling.exception.UserException;
import com.demo.car_pooling.exception.VehicleException;
import com.demo.car_pooling.model.DTO.RideSelectionDTO;
import com.demo.car_pooling.model.DTO.RideStat;
import com.demo.car_pooling.model.DTO.UserRideDTO;
import com.demo.car_pooling.model.Ride;
import com.demo.car_pooling.model.User;
import com.demo.car_pooling.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarPoolingService {

    @Autowired
    private UserService userService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private RideService rideService;

    public void demoStart() throws UserException, VehicleException, RideException {

        User user1 = userService.addUser(new User(null, "Rohan", "rohan@gmail.com", 36, "M"));
        User user2 = userService.addUser(new User(null, "Shashank", "shashank@gmail.com", 29, "M"));
        User user3 = userService.addUser(new User(null, "Nandini", "Nandini@gmail.com", 31, "F"));
        User user4 = userService.addUser(new User(null, "Shipra", "Shipra@gmail.com", 32, "F"));
        User user5 = userService.addUser(new User(null, "Gaurav", "Gaurav@gmail.com", 36, "M"));
        User user6 = userService.addUser(new User(null, "Rahul", "Rahul@gmail.com", 36, "M"));

        Vehicle vehicle1 = vehicleService.addVehicle(new Vehicle(null, user1.getId(), "Swift", "KA-01-12345"));
        Vehicle vehicle2 = vehicleService.addVehicle(new Vehicle(null, user2.getId(), "Baleno", "TS-05-62395"));
        Vehicle vehicle3 = vehicleService.addVehicle(new Vehicle(null, user4.getId(), "Polo", "KA-05-41491"));
        Vehicle vehicle4 = vehicleService.addVehicle(new Vehicle(null, user4.getId(), "Activa", "KA-12-12332"));
        Vehicle vehicle5 = vehicleService.addVehicle(new Vehicle(null, user6.getId(), "Xuv", "KA-05-1234"));

        Ride ride1 = rideService.offerRide(new UserRideDTO(user1.getId(), "Hyderabad", "Bangalore", 1, "KA-01-12345", "Swift"));
        Ride ride2 = rideService.offerRide(new UserRideDTO(user4.getId(), "Bangalore", "Mysore", 1, "KA-12-12332", "Activa"));
        Ride ride3 = rideService.offerRide(new UserRideDTO(user4.getId(), "Bangalore", "Mysore", 2, "KA-05-41491", "Polo"));
        Ride ride4 = rideService.offerRide(new UserRideDTO(user2.getId(), "Hyderabad", "Bangalore", 2, "TS-05-62395", "Baleno"));
        Ride ride5 = rideService.offerRide(new UserRideDTO(user6.getId(), "Hyderabad", "Bangalore", 5, "KA-05-1234", "Xuv"));
        Ride ride6 = rideService.offerRide(new UserRideDTO(user1.getId(), "Bangalore", "Pune", 1, "KA-01-12345", "Swift"));

        RideSelectionDTO rideSelectionDTO1 = rideService.selectRide(new RideSelectionDTO(user3.getId(), "Bangalore", "Mysore", 1, null, true));
        RideSelectionDTO rideSelectionDTO2 = rideService.selectRide(new RideSelectionDTO(user5.getId(), "Bangalore", "Mysore", 1, "Activa", false));
        RideSelectionDTO rideSelectionDTO3 = rideService.selectRide(new RideSelectionDTO(user2.getId(), "Mumbai", "Bangalore", 1, null, true));
        RideSelectionDTO rideSelectionDTO4 = rideService.selectRide(new RideSelectionDTO(user1.getId(), "Hyderabad", "Bangalore", 1, "Baleno", false));
        RideSelectionDTO rideSelectionDTO5 = rideService.selectRide(new RideSelectionDTO(user2.getId(), "Hyderabad", "Bangalore", 1, "Polo", false));

        boolean firstRideEnded = rideService.endRide(ride1.getId());
        boolean secondRideEnded = rideService.endRide(ride2.getId());
        boolean thirdRideEnded = rideService.endRide(ride3.getId());
        boolean fourthRideEnded = rideService.endRide(ride4.getId());

        List<RideStat> rideStats = rideService.getRidesStatForAllUsers();
    }

}
