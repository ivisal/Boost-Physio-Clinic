package opclinicbookingapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import opclinicbookingapp.Helpers.helpersFunction;

public class OpTicketController {
    Scanner input = new Scanner(System.in);
    Scanner inputString = new Scanner(System.in);
    helpersFunction helpers = new helpersFunction();
    HashMap<String, HashMap<String, Integer>> appointments = new HashMap<>();

    // Fetches patient details and books an appointment
    private Patient bookAnAppointment() {
        ArrayList<Integer> randomNumbersArray = new ArrayList<>();
        String patientName = "";
        int physioId = input.nextInt();
        int opCount = appointments.size() + 1;

        if (helpers.isValidPhsio(physioId)) {
            HashMap<String, Integer> appointment = new HashMap<>();
            Patient patient = new Patient();
            Random random = new Random();
            int uniqueID = random.nextInt(90) + 10; // Generates a random number between 10 and 99
            while (!randomNumbersArray.contains(uniqueID)) // Ensure unique ID
                randomNumbersArray.add(uniqueID);

            patient.setId(uniqueID);
            System.out.println("Please enter patient name: ");
            patientName = inputString.nextLine();
            patient.setPatientName(patientName);
            System.out.println("Please enter patient age: ");
            int age = input.nextInt();
            patient.setAge(age);
            System.out.println("Please enter patient gender: (M / F)");
            String gender = inputString.nextLine().toUpperCase();
            while (gender.length() != 1 || !(gender.equals("M") || gender.equals("F"))) {
                System.out.println("Invalid input! Please enter 'M' for Male or 'F' for Female.");
                gender = inputString.nextLine().toUpperCase();
            }

            if ((gender.equals("F")))
                patient.setGender("Female");

            System.out.println("Please enter patient address: ");
            String address = inputString.nextLine();
            patient.setAddress(address);
            System.out.println("Please enter patient phone number: ");
            String phoneNumber = inputString.nextLine();
            patient.setPhone(phoneNumber);

            // Store the appointment with the unique key
            appointment.put("physioid", physioId);
            appointment.put("patientid", uniqueID);
            String appointmentKey = "appointment" + opCount;
            appointments.put(appointmentKey, appointment);

            // Show stored appointments
            System.out.println("Current appointments: ");
            System.out.println(appointments);
            printReceipt(patient, 30007, 200);
            return patient;
        } else {
            helpers.throwError();
            return null;
        }
    }

    // List actions that a user can perform initially
    private void listUserActions() {
        System.out.println();
        System.out.print("1. Book an appointment\t\t");
        System.out.print("2. View appointment\t\t");
        System.out.print("3. Cancel appointment\t\t");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    // List available physiotherapists
    public void listAvailablePhysio() {
        System.out.println();
        Physio physio = new Physio();
        physio.showAvailablePhysios();
    }

    public void printReceipt(Patient patient, int physioId, int appId) {
        HashMap<String, String> bookedPhysio = helpers.getPhysioByKey(physioId);
        System.out.println();
        System.out.println("Boost Physio Clinic, United Kingdom");
        System.out.println("------------------------------------");
        System.out.println();
        System.out.println("Booking Receipt");
        System.out.println("-----------------");
        System.out.println();
        System.out.println("Appointment ID: " + appId);
        System.out.println("Patient Name: " + patient.getPatientName());
        System.out.println("Physician: " + bookedPhysio.get("Name"));
        System.out.println("Expertise: " + bookedPhysio.get("Expertise"));
        System.out.println("Booking Slot: " + bookedPhysio.get("Available Time"));
        System.out.println();
        System.out.println("Thakyou for booking with Boost Physio Clinic, have a nice day.");

    }

    // Perform actions based on user input
    public void performUserActions() {
        boolean running = true;
        while (running) {
            listUserActions();
            switch (input.nextInt()) {
                case 1:
                    listAvailablePhysio(); // List available physiotherapists
                    bookAnAppointment(); // Book an appointment
                    break;
                case 2:
                    System.out.println("View appointment");
                    break;
                case 3:
                    System.out.println("Cancel appointment");
                    break;
                case 4:
                    System.out.println("Exit");
                    running = false;
                    break;
                default:
                    helpers.throwError(); // Handle invalid input
                    break;
            }
        }
    }

    // Constructor to start the program and perform actions
    public OpTicketController() {
        performUserActions();
    }
}
