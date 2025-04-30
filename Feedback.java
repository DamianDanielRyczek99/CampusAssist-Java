/**
 * Feedback represents a student's review of a completed appointment.
 * It includes a comment and a rating from 1 to 5.
 */
public class Feedback {
    private Appointment appointment;
    private String comment;
    private int rating;

    /**
     * Constructor for feedback entry.
     */
    public Feedback(Appointment appointment, String comment, int rating) {
        this.appointment = appointment;
        this.comment = comment;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Feedback on " + appointment.getCategory() + ": \"" + comment + "\" | Rating: " + rating + "/5";
    }
}
