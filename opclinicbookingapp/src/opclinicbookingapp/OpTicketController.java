package opclinicbookingapp;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import opclinicbookingapp.Helpers.helpersFunction;

public class OpTicketController {
    Scanner input = new Scanner(System.in);
    Scanner inputString = new Scanner(System.in);

    // Fetches patient details
    private Patient patientDetailsInput() {
        ArrayList<Integer> randomNumbersArray = new ArrayList<>();
        String patientName = "";
        Patient patient = new Patient();
        Random random = new Random();
        int uniqueID = random.nextInt(90) + 10; // Generates a random number between 10 and 99
        while (!randomNumbersArray.contains(uniqueID))
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
        return patient;
    }

    // list actions that a user can perform initially
    private void listUserActions() {
        System.out.println();
        System.out.print("1. Book an appointment\t\t");
        System.out.print("2. View appointment\t\t");
        System.out.print("3. Cancel appointment\t\t");
        System.out.println("4. Exit");
    }

    private void listAvailablePhysio() {
        System.out.println();
        Physio listPhysios = new Physio();
        listPhysios.availablePhysios();
        helpersFunction helper = new helpersFunction();

        System.err.println(helper.getTime());

    }

    public OpTicketController() {
        listUserActions();

        // switch statement to handle user actions
        System.out.print("Enter your choice: ");
        switch (input.nextInt()) {
            case 1:
                System.out.println("Book an appointment");
                listAvailablePhysio();
                // Patient patient = patientDetailsInput();
                // System.out.print(patient.getPatientCredentials());
                break;
            case 2:
                System.out.println("View appointment");
                break;
            case 3:
                System.out.println("Cancel appointment");
                break;
            case 4:
                System.out.println("Exit");
                return;
            default:
                System.out.println("Invalid choice");
                System.out.println("Invalid choice");
                break;
        }

    }
}