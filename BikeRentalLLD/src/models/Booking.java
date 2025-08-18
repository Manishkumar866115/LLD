package models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class Booking {
    UUID bookingId;
    UUID inventoryId;
    UUID userId;
    Date bookedAt;
    LocalDateTime bookedFrom;
    LocalDateTime bookedTo;
    BookingStatus bookingStatus;
    Date updatedAt;

    public Booking(UUID userId, UUID inventoryId, LocalDateTime bookedFrom, LocalDateTime bookedTo) {
        this.bookingId = UUID.randomUUID();
        this.userId = userId;
        this.inventoryId = inventoryId;
        this.bookedAt = new Date();
        this.bookedFrom = bookedFrom;
        this.bookedTo = bookedTo;
        this.bookingStatus = BookingStatus.PENDING;
        this.updatedAt = new Date();
    }

    public boolean overlaps(LocalDateTime start, LocalDateTime end) {
        return !(end.isBefore(this.bookedFrom) || start.isAfter(this.bookedTo));
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
        this.updatedAt = new Date();
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public UUID getInventoryId() {
        return inventoryId;
    }

    public UUID getUserId() {
        return userId;
    }

    public Date getBookedAt() {
        return bookedAt;
    }

    public LocalDateTime getBookedFrom() {
        return bookedFrom;
    }

    public LocalDateTime getBookedTo() {
        return bookedTo;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
