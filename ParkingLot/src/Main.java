import model.*;
import parkinglot.*;
import parkinglot.ParkingLot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        ParkingLot parkingLot = ParkingLot.getInstance(1);

        Map<ParkingSlotType, List<ParkingSlot>> parkingSpots = new HashMap<>();
        parkingSpots.put(ParkingSlotType.COMPACT, new ArrayList<>(List.of(new Compact(1,ParkingSlotType.COMPACT,null, 100D), new Compact(2,ParkingSlotType.COMPACT,null, 100D))));
        ParkingFloor parkingFloor = new ParkingFloor(parkingSpots, 1);

        parkingLot.addFloor(parkingFloor);
        parkingFloor.display();

        Car car = new Car("C1");
        Bike bike = new Bike("B1");
        EntryGate entryGate1 = new EntryGate(parkingLot);

        entryGate1.checkIn(bike);

        Ticket ticket = entryGate1.checkIn(car);
        System.out.println(ticket);
        ExitGate exitGate1 = new ExitGate(parkingLot);
        exitGate1.checkout(ticket);

        parkingFloor.display();

    }
}