package models;

import services.*;
import models.*;

import java.time.Duration;
import java.time.Instant;
import java.util.List;


public class ParkingLot {
    private static ParkingLot parkingLot;

    private Lift lift;
    private Display display;
    private ParkingSpaceManagementService parkingSpaceManagementService;

    private ParkingLot() { }

    public static synchronized ParkingLot getInstance() {
        if (parkingLot == null) {
            parkingLot = new ParkingLot();
        }
        return parkingLot;
    }

    public void wire(Lift lift,
                     Display display,
                     ParkingSpaceManagementService service) {
        this.lift = lift;
        this.display = display;
        this.parkingSpaceManagementService = service;
    }

    public Integer availabilityCheck(Car car) {
        var type = lift.classify(car);
        return parkingSpaceManagementService.getAvailableSpaces(type).size();
    }

    public ParkingTicket park(Car car) {
        var type = lift.classify(car);
        List<ParkingSpace> candidates = parkingSpaceManagementService.getAvailableSpaces(type);
        ParkingStrategy strategy = lift.getStrategyManager().getParkingStrategy();
        ParkingSpace chosen = strategy.getParkingSpace(candidates, type);

        if (chosen == null) {
            display.display("No space available for type: " + type);
            return null;
        }

        lift.moveToSpot(chosen, car);
        display.display("Parked " + car.getVehicleId() + " at spot " + chosen.getId());
        return new ParkingTicket(car.getVehicleId(), type, Instant.now());
    }

    public double checkout(ParkingTicket ticket) {
        // very simple fee logic: ₹50 per hour, min 1 hour
        long hours = Math.max(1, Duration.between(ticket.getInTime(), Instant.now()).toHours());
        double amount = hours * 50.0;
        ticket.close(Instant.now(), amount);
        display.display("Checkout " + ticket.getVehicleId() + " → ₹" + amount);
        return amount;
    }

}