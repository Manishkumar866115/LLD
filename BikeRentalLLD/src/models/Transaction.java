package models;

import java.util.UUID;

public class Transaction {
    UUID transactionId;
    UUID bookingId;
    UUID userId;
    UUID vehicleId;

    public Transaction(UUID bookingId, UUID userId, UUID vehicleId) {
        this.transactionId = UUID.randomUUID();
        this.bookingId = bookingId;
        this.userId = userId;
        this.vehicleId = vehicleId;
    }
}
