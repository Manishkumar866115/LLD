package models;

import java.util.HashMap;
import java.util.Map;

public class AvailableVehicleSummary {
    HashMap<String, Map<String, Integer>> summary = new HashMap<>();

    public void addVehicle(String type, String subType) {
        summary.putIfAbsent(type, new HashMap<>());
        Map<String, Integer> subMap = summary.get(type);
        subMap.put(subType, subMap.getOrDefault(subType, 0) + 1);
    }

    public void printSummary() {
        System.out.println("Available Vehicles:");
        for (String type : summary.keySet()) {
            System.out.println("- " + type);
            for (Map.Entry<String, Integer> entry : summary.get(type).entrySet()) {
                System.out.println("   * " + entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}
