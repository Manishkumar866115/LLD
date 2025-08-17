package services;

import models.ParkingSpace;
import models.ParkingSpaceType;

import java.util.List;

public interface ParkingStrategy {
    ParkingSpace getParkingSpace(List<ParkingSpace> spaceList, ParkingSpaceType parkingSpaceType);
}
