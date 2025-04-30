import java.util.ArrayList;
import java.util.List;

/**
 * Student represents a user of the system.
 * Each student has a username, password, appointments and feedback entries.
 */
public class Student {
    private String username;
    private String password;
    private List<Appointment> appointments;
    private List<Feedback> feedbackList;

    public Student(String username, String password) {
        this.username = username;
        this.password = password;
        this.appointments = new ArrayList<>();
        this.feedbackList = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public boolean login(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public void addAppointment(Appointment appt) {
        appointments.add(appt);
    }

    public void addFeedback(Feedback feedback) {
        feedbackList.add(feedback);
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    /**
     * Prints reminders for any upcoming appointments.
     */
    public void showUpcomingReminders() {
        System.out.println("\nUpcoming Appointments:");
        for (Appointment appt : appointments) {
            if (appt.isUpcoming()) {
                System.out.println(" - " + appt);
            }
        }
    }
}
