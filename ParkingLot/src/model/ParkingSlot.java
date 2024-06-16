package model;

public class ParkingSlot {

    private Integer parkingSlotId;
    private ParkingSlotType slotType;

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    private Boolean isAvailable;
    private Vehicle vehicle;

    public Double getFeePerHour() {
        return feePerHour;
    }

    private Double feePerHour;

    public ParkingSlot(Integer parkingSlotId, ParkingSlotType slotType, Vehicle vehicle, Double feePerHour) {
        this.parkingSlotId = parkingSlotId;
        this.slotType = slotType;
        this.vehicle = vehicle;
        this.feePerHour = feePerHour;
        this.isAvailable = true;
    }

    public void parkVehile(Vehicle vehicle){
        this.vehicle = vehicle;
        isAvailable = false;
    }



    public ParkingSlotType getSlotType() {
        return slotType;
    }

    public void setSlotType(ParkingSlotType slotType) {
        this.slotType = slotType;
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "parkingSlotId=" + parkingSlotId +
                ", slotType=" + slotType +
                ", isAvailable=" + isAvailable +
                ", vehicle=" + vehicle +
                ", feePerHour=" + feePerHour +
                '}';
    }
}
