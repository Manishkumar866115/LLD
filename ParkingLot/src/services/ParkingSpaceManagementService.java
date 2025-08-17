package services;

import models.Car;
import models.ParkingSpace;
import models.ParkingSpaceType;

import java.util.*;
import java.util.stream.Collectors;

public class ParkingSpaceManagementService {
    private final List<ParkingSpace> parkingSpaceList = new ArrayList<>();

    public void addParkingSpace(ParkingSpace parkingSpace) {
        parkingSpaceList.add(parkingSpace);
    }

    public void removeParkingSpace(UUID parkingSpaceId) {
        parkingSpaceList.removeIf(s -> s.getId().equals(parkingSpaceId));
    }

    public boolean isSpaceAvailable(ParkingSpaceType parkingSpaceType) {
        return parkingSpaceList.stream()
                .anyMatch(s -> s.isAvailable() && s.getParkingSpaceType() == parkingSpaceType);
    }

    public void park(UUID parkingSpaceId, Car car) {
        parkingSpaceList.stream()
                .filter(s -> s.getId().equals(parkingSpaceId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Parking space not found"))
                .occupy(car);
    }

    public List<ParkingSpace> getAllSpace() {
        return Collections.unmodifiableList(parkingSpaceList);
    }

    public List<ParkingSpace> getAvailableSpaces(ParkingSpaceType type) {
        return parkingSpaceList.stream()
                .filter(s -> s.isAvailable() && s.getParkingSpaceType() == type)
                .collect(Collectors.toList());
    }
}
