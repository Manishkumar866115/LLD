package model;

import java.util.List;

public class Car extends Vehicle{

    public Car(String vehicleNumber) {
        super(vehicleNumber);
    }

    public List<ParkingSlotType> eligible(){
        return List.of(ParkingSlotType.COMPACT, ParkingSlotType.LARGE);
    }
}
