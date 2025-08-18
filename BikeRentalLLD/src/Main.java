import models.*;
import services.*;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        /**
         * You've been hired by a bike shop to create software to help them with their rental operation. This includes automating
         * activities such as keeping track of inventory, customers, stock items that are rented, customer fee accural etc
         * The store rents two type of products: Bikes and scooters, Each bike is made in one of the three different sizes: Small,
         * Medium, Large and will fit small, medium and large humans respectively. We'd like to be sue our customers get a bike that fits
         * them and will need to track the size of our bikes. The scooter are available in electric motor or gas motor styles.  The styles
         * of scooters are very different so we want to track them separately so we can match our customers need to the appropriate scooter.
         * FR:
         * 1. Add/ remove product from inventory.
         * 2. Add customer
         * 3. Record that a product is rented to a customer
         * 4. Create a charge for the customer.
         * Question:
         * 1. How many small bikes do you have?
         * 2. What products are there for rent?
         * 3. Does this customer have a balance( owe money) with us?
         * 4. What products are rented?
         * 5. Are there products that are overdue for return? Who has them?
         * 6. What products has a customer rented?
         */

        VehicleManagementService vehicleService = VehicleManagementService.getInstance();
        UserManagementService userService = UserManagementService.getInstance();
        BookingManagementService bookingService = BookingManagementService.getInstance();


        Bike bike1 = new Bike("KA01AB1234", 500, BikeSize.MEDIUM);
        Scooter scooter1 = new Scooter("KA02XY5678", 300, ScooterType.ELECTRIC);
        vehicleService.addInventory(bike1);
        vehicleService.addInventory(scooter1);

        User user = new User("Manish", 5000.0);
        userService.addUser(user);


        System.out.println("Before Booking:");
        bookingService.getAllAvailableVehicles(LocalDateTime.now(), LocalDateTime.now().plusDays(2)).printSummary();


        BookingDto bookingDto = new BookingDto(user.getUserId(), bike1.getId(), LocalDateTime.now(), LocalDateTime.now().plusDays(2));
        boolean success = bookingService.book(bookingDto);
        System.out.println("Booking Successful: " + success);

        System.out.println("After Booking:");
        bookingService.getAllAvailableVehicles(LocalDateTime.now(), LocalDateTime.now().plusDays(2)).printSummary();

        System.out.println("User Balance: " + user.getBalance());
    }
}