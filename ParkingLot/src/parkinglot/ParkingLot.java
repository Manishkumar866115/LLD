package parkinglot;

import model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParkingLot {

    public Integer getParkingLotId() {
        return parkingLotId;
    }

    public List<ParkingFloor> getParkingFloors() {
        return parkingFloors;
    }

    private Integer parkingLotId;

    private List<ParkingFloor> parkingFloors;
    private static ParkingLot instance = null;

    private ParkingLot(Integer parkingLotId, List<ParkingFloor> parkingFloors) {
        parkingFloors = new ArrayList<>();
        this.parkingLotId = parkingLotId;
        this.parkingFloors = parkingFloors;
    }

    public static ParkingLot getInstance(int id) {
        if( instance == null) {
            instance = new ParkingLot(id, null);
        }
        return instance;

    }

    public void addFloor(ParkingFloor parkingFloor){
        // validation that it is being added by admin
        parkingFloors.add(parkingFloor);
    }

    public Boolean parkingAvailable(Vehicle vehicle) {
        List<ParkingSlotType> eligibleSlots = vehicle.eligible();
        System.out.println("Eligible slots for vehile: " + vehicle.getVehicleNumber() + " are "  + eligibleSlots);

        int n = parkingFloors.size();
        for( ParkingSlotType parkingSlotType : eligibleSlots) {
            for (int i = 0; i < n; i++) {
                if ( parkingFloors.get(i).getParkingSpots().containsKey(parkingSlotType) && parkingFloors.get(i).getParkingSpots().get(parkingSlotType).size() > 0){
                    return true;
                }
            }
        }
        return false;
    }

    public Ticket park(Vehicle vehicle) {
        List<ParkingSlotType> eligibleSlots = vehicle.eligible();
        //System.out.println("Eligible slots for vehile: " + vehicle.getVehicleNumber() + " are "  + eligibleSlots);

        int n = parkingFloors.size();
        for( ParkingSlotType parkingSlotType : eligibleSlots) {
            for (int i = 0; i < n; i++) {
                if ( parkingFloors.get(i).getParkingSpots().containsKey(parkingSlotType) && parkingFloors.get(i).getParkingSpots().get(parkingSlotType).size() > 0){
                    // park the vehicle
                    ParkingSlot parkingSlot = parkingFloors.get(i).getParkingSpots().get(parkingSlotType).get(0);
                    parkingSlot.parkVehile(vehicle);
                    parkingFloors.get(i).remove(parkingSlotType);
                    //parkingFloors.get(i).getParkingSpots().get(parkingSlotType).remove(parkingSlot);
                    return new Ticket(new Date(), parkingSlot, vehicle, i);
                }
            }
        }
        return null;
    }
}
