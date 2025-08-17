package services;

public class Display {
    private ParkingSpaceManagementService parkingSpaceManagementService;

    public Display(ParkingSpaceManagementService service) {
        this.parkingSpaceManagementService = service;
    }

    public void display(String message) {
        System.out.println(message);
    }
}
