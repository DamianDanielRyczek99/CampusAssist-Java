import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class to launch the CampusAssist system.
 * Initializes hardcoded test data and starts the console session.
 */
public class CampusAssist {
    public static void main(String[] args) {

        // -----------------------------
        // Step Create student accounts
        // -----------------------------

        Student damian = new Student("damian", "pass123");
        Student daniel = new Student("daniel", "abc123");

        // -----------------------------
        // Step Create appointments
        // -----------------------------

        // Damian has one completed appointment (to test feedback)
        Appointment pastAppt1 = new Appointment("Mental Health", LocalDateTime.now().minusDays(5));
        pastAppt1.complete();
        damian.addAppointment(pastAppt1);

        // Damian has one upcoming appointment (to test reminders)
        Appointment futureAppt1 = new Appointment("Academic Support", LocalDateTime.now().plusDays(1));
        damian.addAppointment(futureAppt1);

        // Daniel has two future appointments
        Appointment futureAppt2 = new Appointment("Financial Aid", LocalDateTime.now().plusDays(2));
        Appointment futureAppt3 = new Appointment("Academic Support", LocalDateTime.now().plusHours(20));
        daniel.addAppointment(futureAppt2);
        daniel.addAppointment(futureAppt3);

        // -----------------------------
        // Step Add students to system
        // -----------------------------

        List<Student> students = new ArrayList<>();
        students.add(damian);
        students.add(daniel);

        // -----------------------------
        // Step Launch the session manager
        // -----------------------------

        SessionManager manager = new SessionManager(students);
        manager.start();
    }
}
