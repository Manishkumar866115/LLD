import services.*;
import models.*;

public class Main {
    public static void main(String[] args) {
        /**
         * FR:
         * Design a fully automated parking system. Parking Lot to have 3 sizes of parking spaces( Small, Medium, Large).
         * Default count of each is 50, 100 and 30 respectively. Customer will drive onto an automated lift that will measure
         * the cars size automatically and classify it. A display will show the customer the amout of open slots for their car
         * size and will leave their car on the lift for parking. If they do, they get a unique identifier printed. The lift
         * will then move the car to most appropriate spot.
         */
        /**
         * Extension : Keep the number of slots as configurable such that spots can be added or removed( for maintainance etc).
         * Questions :
         * 1. What happens if two cars get the same spot? How conflict will be resolved?
         * 2. How to track how many spots get used per day.
         * 3. How can we add more slots easily?
         */


        ParkingSpaceManagementService spaceService = new ParkingSpaceManagementService();
        spaceService.addParkingSpace(new ParkingSpace(ParkingSpaceType.SMALL));
        spaceService.addParkingSpace(new ParkingSpace(ParkingSpaceType.MEDIUM));
        spaceService.addParkingSpace(new ParkingSpace(ParkingSpaceType.LARGE));

        StrategyManager strategyManager = new StrategyManager(new DefaultParkingStrategy());

        Lift lift = Lift.getInstance();
        lift.wire(new ClassificationService(), spaceService, strategyManager);

        Display display = new Display(spaceService);

        ParkingLot lot = ParkingLot.getInstance();
        lot.wire(lift, display, spaceService);


        Car car1 = new Car("KA01-AB-1234", "Swift", ParkingSpaceType.SMALL);
        System.out.println("Available for SMALL: " + lot.availabilityCheck(car1));
        ParkingTicket ticket1 = lot.park(car1);

        Car car2 = new Car("KA01-AB-1234", "Swift", ParkingSpaceType.SMALL);
        System.out.println("Available for SMALL: " + lot.availabilityCheck(car2));
        ParkingTicket ticket2 = lot.park(car2);
        if (ticket1 != null) {
            lot.checkout(ticket1);
        }
        if (ticket2 != null) {
            lot.checkout(ticket2);
        }
    }
}