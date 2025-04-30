import java.time.LocalDateTime;
/**
 * Appointment represents a support session booking.
 * Each appointment has a category (type), date/time, and completion status.
 */
public class Appointment {
    private String category;
    private LocalDateTime dateTime;
    private boolean isCompleted;

    public Appointment(String category, LocalDateTime dateTime) {
        this.category = category;
        this.dateTime = dateTime;
        this.isCompleted = false;
    }

    public String getCategory() {
        return category;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public boolean isUpcoming() {
        return dateTime.isAfter(LocalDateTime.now());
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void complete() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        return "Category: " + category + " | Date: " + dateTime + " | Completed: " + isCompleted;
    }
}
