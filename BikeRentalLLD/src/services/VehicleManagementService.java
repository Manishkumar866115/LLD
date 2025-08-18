package services;

import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VehicleManagementService {
    private static VehicleManagementService instance;
    private List<Vehicle> inventoryList;

    private VehicleManagementService() {
        this.inventoryList = new ArrayList<>();
    }

    public static VehicleManagementService getInstance() {
        if (instance == null) instance = new VehicleManagementService();
        return instance;
    }

    public boolean addInventory(Vehicle vehicle) {
        return inventoryList.add(vehicle);
    }

    public boolean removeInventory(UUID id) {
        return inventoryList.removeIf(v -> v.getId().equals(id));
    }

    public List<Vehicle> getAllVehicle() {
        return inventoryList;
    }

    public Vehicle getVehicleById(UUID id) {
        return inventoryList.stream().filter(v -> v.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
