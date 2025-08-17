package services;

import models.Car;
import models.ParkingSpace;
import models.ParkingSpaceType;

public class Lift {
    private static Lift lift;
    private ClassificationService classificationService;
    private ParkingSpaceManagementService parkingSpaceManagementService;
    private StrategyManager strategyManager;
    private Lift() {}

    public static synchronized Lift getInstance() {
        if (lift == null) {
            lift = new Lift();
        }
        return lift;
    }

    public void wire(ClassificationService classificationService,
                     ParkingSpaceManagementService spaceService,
                     StrategyManager strategyManager) {
        this.classificationService = classificationService;
        this.parkingSpaceManagementService = spaceService;
        this.strategyManager = strategyManager;
    }

    public void moveToSpot(ParkingSpace parkingSpace, Car car) {
        parkingSpace.occupy(car);
    }

    public ParkingSpaceType classify(Car car) {
        return classificationService.classify(car);
    }

    public ClassificationService getClassificationService() { return classificationService; }
    public ParkingSpaceManagementService getParkingSpaceManagementService() { return parkingSpaceManagementService; }
    public StrategyManager getStrategyManager() { return strategyManager; }
}
