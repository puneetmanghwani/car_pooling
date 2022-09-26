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

    private String name;

    private List<Ride> ridesTaken;

    private List<Ride> ridesOffered;

}
