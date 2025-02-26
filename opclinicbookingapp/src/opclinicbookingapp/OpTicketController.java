package opclinicbookingapp;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class OpTicketController{
    Scanner input = new Scanner(System.in);
    // Fetches patient details
    private Patient patientDetailsInput(){
        ArrayList<Integer> randomNumbersArray = new ArrayList<>();
        String patientName = "";
        Patient patient = new Patient();
        Random random = new Random();
        int uniqueID = random.nextInt(90) + 10; // Generates a random number between 10 and 99
        while(!randomNumbersArray.contains(uniqueID))
            randomNumbersArray.add(uniqueID);
        patient.setId(uniqueID);  
        return patient; 
    }

    // list actions that a user can perform initially
    private void listUserActions(){
        System.out.println();
        System.out.print("1. Book an appointment\t\t");
        System.out.print("2. View appointment\t\t");
        System.out.print("3. Cancel appointment\t\t");
        System.out.println("4. Exit");
    }

    public OpTicketController(){
        listUserActions();
        // switch statement to handle user actions
        System.out.println("Enter your choice: ");
        switch(input.nextInt()){
            case 1:
                System.out.println("Book an appointment");
                Patient patient = patientDetailsInput();
                break;
            case 2:
                System.out.println("View appointment");
                break;
            case 3:
                System.out.println("Cancel appointment");
                break;
            case 4:
                System.out.println("Exit");
                return ;
            default:
                System.out.println("Invalid choice");
                System.out.println("Invalid choice");
                break;
        }
       
    }
}

