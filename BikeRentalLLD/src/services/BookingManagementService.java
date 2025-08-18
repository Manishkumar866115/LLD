package services;

import models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingManagementService {
    private static BookingManagementService instance;
    private List<Booking> bookings;
    private UserManagementService userManagementService;
    private VehicleManagementService inventoryManagementService;

    private BookingManagementService() {
        bookings = new ArrayList<>();
        userManagementService = UserManagementService.getInstance();
        inventoryManagementService = VehicleManagementService.getInstance();
    }

    public static BookingManagementService getInstance() {
        if (instance == null) instance = new BookingManagementService();
        return instance;
    }

    public boolean book(BookingDto bookingDto) {
        if (!availability(bookingDto)) return false;

        Booking booking = new Booking(bookingDto.getUserId(), bookingDto.getInventoryId(),
                bookingDto.getStart(), bookingDto.getEnd());
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        bookings.add(booking);

        Vehicle v = inventoryManagementService.getVehicleById(bookingDto.getInventoryId());
        User u = userManagementService.getUserById(bookingDto.getUserId());
        long days = (bookingDto.getEnd().toLocalDate().toEpochDay() - bookingDto.getStart().toLocalDate().toEpochDay());
        double cost = days * v.getPricePerDay();
        if (!userManagementService.deductBalance(u.getUserId(), cost)) {
            booking.setBookingStatus(BookingStatus.CANCELLED);
            return false;
        }

        return true;
    }

    public boolean availability(BookingDto bookingDto) {

        for (Booking b : bookings) {
            if (b.getInventoryId().equals(bookingDto.getInventoryId()) && b.getBookingStatus() == BookingStatus.CONFIRMED) {
                if (b.overlaps(bookingDto.getStart(), bookingDto.getEnd())) {
                    return false;
                }
            }
        }
        return true;
    }

    public AvailableVehicleSummary getAllAvailableVehicles(LocalDateTime start, LocalDateTime end) {
        AvailableVehicleSummary summary = new AvailableVehicleSummary();

        for (Vehicle v : inventoryManagementService.getAllVehicle()) {
            boolean isAvailable = true;
            for (Booking b : bookings) {
                if (b.getInventoryId().equals(v.getId()) && b.getBookingStatus() == BookingStatus.CONFIRMED && b.overlaps(start, end)) {
                    isAvailable = false;
                    break;
                }
            }
            if (isAvailable) {
                if (v instanceof Bike) {
                    summary.addVehicle("BIKE", ((Bike) v).bikeSize.toString());
                } else if (v instanceof Scooter) {
                    summary.addVehicle("SCOOTER", ((Scooter) v).scooterType.toString());
                }
            }
        }
        return summary;
    }
}