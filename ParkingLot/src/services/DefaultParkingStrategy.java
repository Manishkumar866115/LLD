package services;

import models.ParkingSpace;
import models.ParkingSpaceType;

import java.util.List;

public class DefaultParkingStrategy implements ParkingStrategy {
    @Override
    public ParkingSpace getParkingSpace(List<ParkingSpace> spaceList, ParkingSpaceType parkingSpaceType) {
        for (ParkingSpace s : spaceList) {
            if (s.isAvailable() && s.getParkingSpaceType() == parkingSpaceType) {
                return s;
            }
        }
        return null;
    }
}
