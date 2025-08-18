package models;

public class Bike extends Vehicle {
    public BikeSize bikeSize;

    public Bike(String vehicleNumber, int pricePerDay, BikeSize bikeSize) {
        super(vehicleNumber, ProductType.BIKE, pricePerDay);
        this.bikeSize = bikeSize;
    }
}
