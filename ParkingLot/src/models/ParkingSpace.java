package models;

import java.util.UUID;

public class ParkingSpace {
    private UUID id;
    private ParkingSpaceType parkingSpaceType;
    private boolean isAvailable;
    private Car car;

    public ParkingSpace() {
        this.id = UUID.randomUUID();
        this.isAvailable = true;
    }

    public ParkingSpace(ParkingSpaceType type) {
        this();
        this.parkingSpaceType = type;
    }

    public UUID getId() { return id; }
    public ParkingSpaceType getParkingSpaceType() { return parkingSpaceType; }
    public boolean isAvailable() { return isAvailable; }
    public Car getCar() { return car; }

    public void occupy(Car car) {
        this.car = car;
        this.isAvailable = false;
    }

    public void vacate() {
        this.car = null;
        this.isAvailable = true;
    }
}
