package opclinicbookingapp;

import java.util.Scanner;

public class OpTicketController {
    private Scanner input = new Scanner(System.in);
    private BookingService bookingService;
    private AppointmentManager appointmentManager;

    public OpTicketController() {
        appointmentManager = new AppointmentManager();
        bookingService = new BookingService(appointmentManager);
        performUserActions();
    }

    private void listUserActions(int step) {
        System.out.println();
        if (step == 0) {
            System.out.println("1. Book an appointment");
            System.out.println("2. Exit");
        } else {
            System.out.println("1. Book another appointment");
            System.out.println("2. View appointment");
            System.out.println("3. Cancel appointment");
            System.out.println("4. Exit");
        }
        System.out.print("Enter your choice: ");
    }

    private void performUserActions() {
        boolean running = true;
        int step = 0;

        while (running) {
            listUserActions(step);
            int choice = input.nextInt();
            if(step == 0){
                switch (choice) {
                    case 1 -> bookingService.bookAnAppointment();
                    case 2 ->{
                        System.out.println("Exit");
                        running = false;
                    }
                    default -> System.out.println("Invalid choice! Try again.");
                }
            }else{
                switch (choice) {
                    case 1 -> bookingService.bookAnAppointment();
                    case 2 -> viewAppointments();
                    case 3 -> cancelAppointment();
                    case 4 -> {
                        System.out.println("Exit");
                        running = false;
                    }
                    default -> System.out.println("Invalid choice! Try again.");
                }
            }


            step++;
        }
    }

    private void viewAppointments() {
        System.out.println("Enter Appointment ID to view: ");
        int appointmentId = input.nextInt();

        if (appointmentManager.isValidAppointment(appointmentId)) {
            Appointment appointment = appointmentManager.getAppointment(appointmentId);
            System.out.println("Appointment Found: " + appointment);
        } else {
            System.out.println("Invalid Appointment ID!");
        }
    }

    private void cancelAppointment() {
        System.out.println("Enter Appointment ID to cancel: ");
        int appointmentId = input.nextInt();

        if (appointmentManager.isValidAppointment(appointmentId)) {
            appointmentManager.cancelAppointment(appointmentId);
            System.out.println("Appointment canceled successfully.");
        } else {
            System.out.println("Invalid Appointment ID!");
        }
    }
}
