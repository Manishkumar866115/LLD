package model;

public class Compact extends ParkingSlot{

    public Compact(Integer parkingSlotId,ParkingSlotType slotType, Vehicle vehicle, Double feePerHour) {
        super(parkingSlotId, slotType, vehicle, feePerHour);
    }

}
