package models;

import java.time.LocalDateTime;
import java.util.UUID;

public class BookingDto {
    UUID userId;
    UUID inventoryId;
    UUID bookingId;
    LocalDateTime start;
    LocalDateTime end;

    public BookingDto(UUID userId, UUID inventoryId, LocalDateTime start, LocalDateTime end) {
        this.userId = userId;
        this.inventoryId = inventoryId;
        this.bookingId = UUID.randomUUID();
        this.start = start;
        this.end = end;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getInventoryId() {
        return inventoryId;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }
}

