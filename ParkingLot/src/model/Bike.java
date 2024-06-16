package model;

import java.util.List;

public class Bike extends Vehicle{
    public Bike(String vehicleNumber) {
        super(vehicleNumber);
    }

    public List<ParkingSlotType> eligible(){
        return List.of(ParkingSlotType.SMALL);
    }
}
