package model;

import java.util.List;
import java.util.Map;

public class ParkingFloor {
    public Map<ParkingSlotType, List<ParkingSlot>> getParkingSpots() {
        return parkingSpots;
    }

    public Integer getParkingFloorId() {
        return parkingFloorId;
    }

    Map<ParkingSlotType, List<ParkingSlot>> parkingSpots;
    private Integer parkingFloorId;

    public ParkingFloor(Map<ParkingSlotType, List<ParkingSlot>> parkingSpots, Integer parkingFloorId) {
        this.parkingSpots = parkingSpots;
        this.parkingFloorId = parkingFloorId;
    }

    public void display(){
        System.out.println("Parking Spots below: ");
        System.out.println(parkingSpots);
    }

    public void remove(ParkingSlotType parkingSlotType){
        parkingSpots.get(parkingSlotType).remove(0);
    }
}
