package parkinglot;

import model.ParkingSlot;
import model.Ticket;
import model.Vehicle;

import java.util.Date;

public class EntryGate implements Gate{

    ParkingLot parkingLot;
    public EntryGate(ParkingLot parkingLot){
        this.parkingLot = parkingLot;
    }

    @Override
    public Ticket checkIn(Vehicle vehicle) {
        Boolean parkingAvailable = parkingLot.parkingAvailable(vehicle);
        if( !parkingAvailable){
            System.out.println("No parking available for vehicle : " + vehicle.getVehicleNumber());
            //throw new RuntimeException("No parking available");
        }else{
            Ticket ticket = parkingLot.park(vehicle);
            return ticket;
        }
        return null;
    }

    @Override
    public Boolean isParkingAvailable(Vehicle vehicle) {
        return null;
    }

    @Override
    public Double checkout(Ticket ticket) {
        throw new RuntimeException("Exit not allowed through entry gate");
    }
}
