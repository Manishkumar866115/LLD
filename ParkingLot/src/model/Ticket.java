package model;

import java.util.Date;

public class Ticket {
    private static Integer ticketId = 1;
    private Date entryTime;
    private ParkingSlot parkingSlot;

    public Integer getFloorNumber() {
        return floorNumber;
    }

    private Integer floorNumber;
    private Vehicle vehicle;

    public Ticket(Date entryTime, ParkingSlot parkingSlot, Vehicle vehicle, Integer floorNumber) {
        this.entryTime = entryTime;
        this.parkingSlot = parkingSlot;
        this.vehicle = vehicle;
        this.floorNumber = floorNumber;
    }

    public static void incrementTicketId() {
        ticketId++;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public ParkingSlot getParkingSlot() {
        return parkingSlot;
    }

    public void setParkingSlot(ParkingSlot parkingSlot) {
        this.parkingSlot = parkingSlot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "entryTime=" + entryTime +
                ", parkingSlot=" + parkingSlot +
                ", floorNumber=" + floorNumber +
                ", vehicle=" + vehicle +
                '}';
    }
}
