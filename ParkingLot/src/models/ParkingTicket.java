package models;

import java.time.Instant;
import java.util.UUID;

public class ParkingTicket {
    private final UUID ticketId = UUID.randomUUID();
    private final String vehicleId;
    private final ParkingSpaceType parkingSpaceType;
    private final Instant inTime;
    private Instant outTime;
    private double fee;

    public ParkingTicket(String vehicleId, ParkingSpaceType parkingSpaceType, Instant inTime) {
        this.vehicleId = vehicleId;
        this.parkingSpaceType = parkingSpaceType;
        this.inTime = inTime;
    }

    public UUID getTicketId() { return ticketId; }
    public String getVehicleId() { return vehicleId; }
    public ParkingSpaceType getParkingSpaceType() { return parkingSpaceType; }
    public Instant getInTime() { return inTime; }
    public Instant getOutTime() { return outTime; }
    public double getFee() { return fee; }

    public void close(Instant outTime, double fee) {
        this.outTime = outTime;
        this.fee = fee;
    }
}
