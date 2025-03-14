package opclinicbookingapp;

import opclinicbookingapp.Helpers.helpersFunction;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class BookingService {
    private Scanner input = new Scanner(System.in);
    private Scanner inputString = new Scanner(System.in);
    helpersFunction helpers = new helpersFunction();
    Physio physio = new Physio();
    private AppointmentManager appointmentManager;
    private HashMap<Integer, Patient> patients;
    public String throwMessage = "Ooho!! Something error occured. Please try again.";


    public BookingService(AppointmentManager appointmentManager) {
        this.appointmentManager = appointmentManager;
        this.patients = new HashMap<>();
    }

    public void typeOfAppointment() {

        System.out.println("1. Would you like to search by physio name ?");
        System.out.println("2. Would you like to show all physios ?");
        System.out.print("Enter your choice: ");
        switch (input.nextInt()) {
            case 1:
                System.out.print("Enter Physio name to search: ");
                String prefferedPhysio = inputString.nextLine().toLowerCase();
                if (!prefferedPhysio.isEmpty()) {
                    HashMap<String, String> searchedPhysio = helpers.getPhysioByName(prefferedPhysio);
                    if (searchedPhysio != null && !searchedPhysio.isEmpty()) {
                        System.out.println();
                        System.out.println("Search Results:");
                        System.out.println("-----------------");
                        String physioName = searchedPhysio.get("Name"); // Get the name (String) from the HashMap
                        if (physioName != null && !physioName.isEmpty()) {
                            physioName = physioName.substring(0, 1).toUpperCase() + physioName.substring(1).toLowerCase();
                        }
                        System.out.println("ID: " + searchedPhysio.get("Key"));
                        System.out.println("Physio Name: Dr. " + physioName);
                        System.out.println("Expertise: " + searchedPhysio.get("Expertise"));
                        System.out.println("Avaliable From: " + searchedPhysio.get("Available Time"));

                    } else {
                        throwMessage = "No Physio found!";
                        helpers.throwError(throwMessage);
                        System.exit(1);
                    }
                } else {
                    helpers.throwError(throwMessage);
                }
                break;
            case 2:
                physio.showAvailablePhysios();
                break;
        }
    }

    public void bookAnAppointment() {
        System.out.println();
        typeOfAppointment();

        System.out.print("\nEnter the respective ID you may wish an appointment (enter 0 to exit): ");
        int physioId = input.nextInt();

        if (!(helpers.isValidPhsio(physioId))){
            System.out.println("Invalid Doctor ID.");
            System.out.println("Thank you for visiting Boost Physio Clinic, have a nice day.");
            return;
        }

        Random random = new Random();
        int uniquePID = random.nextInt(90) + 10;

        Patient patient = new Patient();
        patient.setId(uniquePID);

        System.out.print("Please enter patient name: ");
        String patientName = inputString.nextLine();
        patient.setPatientName(patientName);

        System.out.print("Please enter patient age: ");
        int age = input.nextInt();
        patient.setAge(age);

        System.out.print("Please enter patient gender: (M / F)");
        String gender = inputString.nextLine().toUpperCase();
        while (!gender.equals("M") && !gender.equals("F")) {
            System.out.println("Invalid input! Please enter 'M' or 'F'.");
            gender = inputString.nextLine().toUpperCase();
        }
        patient.setGender(gender.equals("M") ? "Male" : "Female");

        System.out.print("Please enter patient address: ");
        String address = inputString.nextLine();
        patient.setAddress(address);

        System.out.print("Please enter patient phone number: ");
        String phoneNumber = inputString.nextLine();
        patient.setPhone(phoneNumber);

        patients.put(uniquePID, patient);
        int appointmentId = appointmentManager.bookAppointment(physioId, uniquePID);

        System.out.println("Appointment booked successfully!\n");
        printReceipt(patient, physioId, appointmentId);
    }

    public void printReceipt(Patient patient, int physioId, int appointmentId) {
        System.out.println("\nBoost Physio Clinic, United Kingdom");
        System.out.println("------------------------------------");
        System.out.println("Booking Receipt");
        System.out.println("-----------------");
        System.out.println("Appointment ID: " + appointmentId);
        System.out.println("Patient Name: " + patient.getPatientName());
        System.out.println("Physio ID: " + physioId);
        System.out.println("Thank you for booking with Boost Physio Clinic!");
    }
}
