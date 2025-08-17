package services;

import models.Car;
import models.ParkingSpaceType;

public class ClassificationService {
    public ParkingSpaceType classify(Car car) {
        // This logic will be complicated as arriving at the size of the car requires intelligence or multuple
        // parameters like length, width, height, etc or calculated using sensors.
        // For simplicity, we assume the car has a method to get its size.
        return car.getCarSize();
    }
}
