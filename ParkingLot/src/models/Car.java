package models;

import java.util.Objects;

public class Car {
    private String vehicleId;
    private String model;
    private ParkingSpaceType carSize;

    public Car() { }

    public Car(String vehicleId, String model, ParkingSpaceType carSize) {
        this.vehicleId = vehicleId;
        this.model = model;
        this.carSize = carSize;
    }

    public String getVehicleId() { return vehicleId; }
    public String getModel() { return model; }
    public ParkingSpaceType getCarSize() { return carSize; }

    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }
    public void setModel(String model) { this.model = model; }
    public void setCarSize(ParkingSpaceType carSize) { this.carSize = carSize; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return Objects.equals(vehicleId, car.vehicleId);
    }
    @Override public int hashCode() { return Objects.hash(vehicleId); }
}
