package com.demo.car_pooling.model.DTO;

import com.demo.car_pooling.model.Ride;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RideStat {

    List<Ride> ridesTaken;

    List<Ride> ridesOffered;

}
