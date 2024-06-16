package parkinglot;

import model.Ticket;
import model.Vehicle;

public interface Gate {

    Ticket checkIn(Vehicle vehicle);

    Boolean isParkingAvailable(Vehicle vehicle);

    Double checkout(Ticket ticket);

}
