package models;

import java.util.UUID;

public abstract class Vehicle {
    UUID id;
    String vehicleNumber;
    ProductType productType;
    int pricePerDay;

    public Vehicle(String vehicleNumber, ProductType productType, int pricePerDay) {
        this.id = UUID.randomUUID();
        this.vehicleNumber = vehicleNumber;
        this.productType = productType;
        this.pricePerDay = pricePerDay;
    }

    public UUID getId() {
        return id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public ProductType getProductType() {
        return productType;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }
}