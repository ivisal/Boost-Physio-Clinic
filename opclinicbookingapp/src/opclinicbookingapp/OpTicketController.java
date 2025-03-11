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
    String throwMessage = "Ooho!! Something error occured. Please try again.";
    int initial = 0;

    // Fetches patient details and books an appointment
    private Patient bookAnAppointment() {
        System.out.println();
        System.out.print("Enter the respective ID you may wish an appointment (enter 0 to exit): ");

        ArrayList<Integer> randomNumbersArray = new ArrayList<>();
        String patientName = "";
        int physioId = input.nextInt();
        int opCount = appointments.size() + 1;


        if (physioId > 0 && helpers.isValidPhsio(physioId)  ) {
            HashMap<String, Integer> appointment = new HashMap<>();
            Patient patient = new Patient();
            Random random = new Random();
            int uniquePID = random.nextInt(90) + 10; // Generates a random number between 10 and 99
            while (!randomNumbersArray.contains(uniquePID)) // Ensure unique ID
                randomNumbersArray.add(uniquePID);

            patient.setId(uniquePID);
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
            appointment.put("appointmentID", opCount);
            appointment.put("physioID", physioId);
            appointment.put("patientID", uniquePID);
            String appointmentKey = "appointment" + opCount;
            appointments.put(appointmentKey, appointment);

            // Show stored appointments
            System.out.println("Current appointments: ");
            System.out.println(appointments);
            printReceipt(patient, physioId);
            return patient;
        } else {
            if(physioId == 0){
                throwMessage = "Thank you for visiting Boost Physio Clinic, have a nice day.";
                helpers.throwError(throwMessage);
                System.exit(1);
                return null;
            }else{
                helpers.throwError(throwMessage);
                System.exit(1);
                return null;
            }

        }
    }

    // List actions that a user can perform initially
    private void listUserActions(int initial) {
        System.out.println();
        if(initial==0) {
            System.out.print("1. Book an appointment\t\t");
            System.out.println("2. Exit");
        }else{
            System.out.print("1. Book another appointment\t\t");
            System.out.print("2. View appointment\t\t");
            System.out.print("3. Cancel appointment\t\t");
            System.out.println("4. Exit");
        }

        System.out.print("Enter your choice: ");
    }

    // List available physiotherapists
    public void listAvailablePhysio() {
        System.out.println();
        Physio physio = new Physio();
        physio.showAvailablePhysios();
    }

    public void printReceipt(Patient patient, int physioId) {
        try {
            System.out.println("Your receipt is printing...");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        HashMap<String, String> bookedPhysio = helpers.getPhysioByKey(physioId);
        System.out.println();
        System.out.println("Boost Physio Clinic, United Kingdom");
        System.out.println("------------------------------------");
        System.out.println();
        System.out.println("Booking Receipt");
        System.out.println("-----------------");
        System.out.println();
        System.out.println("Appointment ID: " + patient.getId());
        System.out.println("Patient Name: " + patient.getPatientName());
        System.out.println("Physician: " + bookedPhysio.get("Name").toUpperCase());
        System.out.println("Expertise: " + bookedPhysio.get("Expertise"));
        System.out.println("Booking Slot: " + bookedPhysio.get("Available Time"));
        System.out.println();
        System.out.println("Thankyou for booking with Boost Physio Clinic, have a nice day.");

    }
    public void typeOfAppointment() {
        System.out.println();
        System.out.println("1. Would you like to search by physio name ?");
        System.out.println("2. Would you like to show all physios ?");
        System.out.print("Enter your choice: ");
        switch (input.nextInt()){
            case 1:
                System.out.print("Enter Physio name to search: ");
                String prefferedPhysio = inputString.nextLine().toLowerCase();
                if(!prefferedPhysio.isEmpty()){
                    HashMap<String, String> searchedPhysio  = helpers.getPhysioByName(prefferedPhysio);
                    if (searchedPhysio != null && !searchedPhysio.isEmpty()) {
                        System.out.println();
                        System.out.println("Search Results:");
                        System.out.println("-----------------");
                        String physioName = searchedPhysio.get("Name");  // Get the name (String) from the HashMap
                        if (physioName != null && !physioName.isEmpty()) {
                            physioName = physioName.substring(0, 1).toUpperCase() + physioName.substring(1).toLowerCase();
                        }
                        System.out.println("ID: " + searchedPhysio.get("Key"));
                        System.out.println("Physio Name: Dr. " + physioName);
                        System.out.println("Expertise: " + searchedPhysio.get("Expertise"));
                        System.out.println("Avaliable From: " + searchedPhysio.get("Available Time"));
                        bookAnAppointment();
                        break;
                    }else{
                        throwMessage = "No Physio found!";
                        helpers.throwError(throwMessage);
                        System.exit(1);
                    }
                }else{
                    helpers.throwError(throwMessage);
                }
            case 2:
                listAvailablePhysio(); // List available physiotherapists
                break;
        }
    }

    public void viewAppointments( HashMap<String, HashMap<String, Integer>> appointments  ){
        System.out.println();
        System.out.println("1. View Your appointment by appointment ID ");
        System.out.println("2. View all your appointments ");
        System.out.print("Choose a option: ");
        switch (input.nextInt()){
            case 1:
                System.out.print("Enter your appointment ID: ");
                int appointmentID = input.nextInt();
                if(appointmentID > 0 ){
                    System.out.print("-----------");
                    break;
                }
            case 2:
                System.out.println("Your appointments: ");
                break;
            default:
                helpers.throwError(throwMessage);
                break;
        }
    }



    // Perform actions based on user input
    public void performUserActions() {
        boolean running = true;
        while (running) {
            listUserActions(initial);
            if(initial==0){
                switch (input.nextInt()) {
                    case 1:
                        typeOfAppointment();
                        bookAnAppointment(); // Book an appointment
                        break;
                    case 2:
                        System.out.println("Exit");
                        running = false;
                        break;
                    default:
                        helpers.throwError(throwMessage); // Handle invalid input
                        break;
                }
            }else{
                switch (input.nextInt()) {
                    case 1:
                        typeOfAppointment();
                        bookAnAppointment();
                        break;
                    case 2:
                        viewAppointments(appointments);
                        break;
                    case 3:
                        System.out.println("Cancel appointment");
                        break;
                    case 4:
                        System.out.println("Exit");
                        running = false;
                        break;
                    default:
                        helpers.throwError(throwMessage); // Handle invalid input
                        break;
                }
            }

            initial++;
        }
    }

    // Constructor to start the program and perform actions
    public OpTicketController() {
        performUserActions();
    }
}
