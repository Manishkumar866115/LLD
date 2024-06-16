package parkinglot;

import model.Ticket;
import model.Vehicle;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class ExitGate implements Gate{

    ParkingLot parkingLot;

    public ExitGate(ParkingLot parkingLot){
        this.parkingLot = parkingLot;
    }

    @Override
    public Ticket checkIn(Vehicle vehicle) {
        throw new RuntimeException("Entry Not allowed through exit gate");
    }

    @Override
    public Boolean isParkingAvailable(Vehicle vehicle) {
        return null;
    }

    @Override
    public Double checkout(Ticket ticket) {
        if( ticket == null){
            throw new RuntimeException("No exit without ticket allowed");
        }
        Instant instant1 = (new Date()).toInstant();
        Instant instant2 = ticket.getEntryTime().toInstant();
        long hoursParked = ChronoUnit.HOURS.between(instant1, instant2);
        hoursParked++;

        double charge = ticket.getParkingSlot().getFeePerHour() * hoursParked;

        System.out.println("Parking time for vehile : " + ticket.getVehicle().getVehicleNumber() + " is : " + hoursParked + " and total charge is: " + charge);
        ticket.getParkingSlot().setAvailable(true);

        parkingLot.getParkingFloors().get(ticket.getFloorNumber()).getParkingSpots().get(ticket.getParkingSlot().getSlotType()).add(ticket.getParkingSlot());

        return charge;
    }
}
