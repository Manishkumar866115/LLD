package models;

public class Scooter extends Vehicle {
    public ScooterType scooterType;

    public Scooter(String vehicleNumber, int pricePerDay, ScooterType scooterType) {
        super(vehicleNumber, ProductType.SCOOTER, pricePerDay);
        this.scooterType = scooterType;
    }
}
