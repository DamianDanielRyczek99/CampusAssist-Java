import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

/**
 * SessionManager manages student login and the overall console interaction.
 * Handles support session booking, appointment viewing, and feedback submission.
 */
public class SessionManager {
    private List<Student> students;         // List of registered students
    private Scanner scanner;                // Input scanner for user interaction
    private Student currentStudent;         // Tracks the currently logged-in user

    /**
     * Constructs the session manager with a list of students.
     *
     * @param students List of registered users
     */
    public SessionManager(List<Student> students) {
        this.students = students;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the login flow and launches the student menu if login is successful.
     */
    public void start() {
        System.out.println("========== Welcome to CampusAssist ==========");
        login();

        if (currentStudent != null) {
            currentStudent.showUpcomingReminders(); // Automated reminder on login
            showMenu(); // Launch student menu
        }
    }

    /**
     * Handles student login using username and password.
     */
    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Loop through hardcoded student list and authenticate
        for (Student s : students) {
            if (s.getUsername().equalsIgnoreCase(username) && s.login(password)) {
                currentStudent = s;
                System.out.println("✅ Login successful! Welcome, " + username + "!");
                return;
            }
        }

        System.out.println("❌ Login failed. Invalid credentials.");
    }

    /**
     * Displays the main menu for logged-in students and routes options.
     */
    private void showMenu() {
        int choice;
        do {
            System.out.println("\n------ Student Menu ------");
            System.out.println("1. Request Support Session");
            System.out.println("2. View Appointments");
            System.out.println("3. Provide Feedback");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                choice = -1;
            }

            switch (choice) {
                case 1 -> requestSession();
                case 2 -> viewAppointments();
                case 3 -> provideFeedback();
                case 4 -> System.out.println("👋 Logging out. Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 4);
    }

    /**
     * Allows the student to book a new support session.
     */
    private void requestSession() {
        System.out.println("\nSelect Session Category:");
        System.out.println("1. Mental Health");
        System.out.println("2. Academic Support");
        System.out.println("3. Financial Aid");

        String categoryInput = scanner.nextLine();
        String category = switch (categoryInput) {
            case "1" -> "Mental Health";
            case "2" -> "Academic Support";
            case "3" -> "Financial Aid";
            default -> "General Support";
        };

        System.out.print("Enter date and time (format: yyyy-MM-ddTHH:mm): ");
        String input = scanner.nextLine();

        try {
            LocalDateTime dateTime = LocalDateTime.parse(input);
            Appointment appointment = new Appointment(category, dateTime);
            currentStudent.addAppointment(appointment);
            System.out.println("Appointment booked: " + appointment);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use: yyyy-MM-ddTHH:mm");
        }
    }

    /**
     * Displays the student's appointment history and upcoming bookings.
     */
    private void viewAppointments() {
        System.out.println("\nYour Appointments:");
        List<Appointment> appointments = currentStudent.getAppointments();

        if (appointments.isEmpty()) {
            System.out.println("No appointments booked.");
        } else {
            for (Appointment appt : appointments) {
                System.out.println(" - " + appt);
            }
        }
    }

    /**
     * Allows the student to submit feedback on completed appointments.
     */
    private void provideFeedback() {
        System.out.println("\nCompleted Appointments (for feedback):");
        List<Appointment> completed = currentStudent.getAppointments().stream()
                .filter(Appointment::isCompleted)
                .toList();

        if (completed.isEmpty()) {
            System.out.println("You have no completed sessions available for feedback.");
            return;
        }

        for (int i = 0; i < completed.size(); i++) {
            System.out.println((i + 1) + ". " + completed.get(i));
        }

        System.out.print("Select appointment number to review: ");
        try {
            int selection = Integer.parseInt(scanner.nextLine()) - 1;
            Appointment appt = completed.get(selection);

            System.out.print("Enter your feedback: ");
            String comment = scanner.nextLine();

            System.out.print("Rate the session (1 to 5): ");
            int rating = Integer.parseInt(scanner.nextLine());

            Feedback feedback = new Feedback(appt, comment, rating);
            currentStudent.addFeedback(feedback);

            System.out.println("Thank you! Your feedback has been recorded.");
        } catch (Exception e) {
            System.out.println("Invalid selection. Please enter a valid number.");
        }
    }

    // ============================== Admin Integration Notes ==============================
    /*
        This project currently supports only student features.
        To integrate Group Member B (Admin) features:

        1. Create an Admin class with username/password and admin privileges.
        2. Enhance login() to identify whether the user is an admin or student.
        3. Add an adminMenu() method with options to:
           - View all students and appointments
           - Approve, reschedule, or cancel sessions
           - View and manage all feedback
        4. Use a centralized data controller to manage all student/appointment records.
    */
}
