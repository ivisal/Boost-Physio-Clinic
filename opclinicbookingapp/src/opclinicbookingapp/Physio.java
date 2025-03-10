package opclinicbookingapp;

import java.util.ArrayList;
import java.util.HashMap;
import opclinicbookingapp.Helpers.helpersFunction;

public class Physio {
    private ArrayList<HashMap<String, String>> availablePhysios;

    // Constructor to initialize availablePhysios
    public Physio() {
        availablePhysios = new ArrayList<>();
        helpersFunction helpers = new helpersFunction();

        // Add physiotherapists with available time fetched from helpers
        addPhysioByKey("30001", "john", "Physiotherapist", helpers.getTime());
        addPhysioByKey("30007", "edward", "ENT", "1 PM - 4 PM");
    }

    // Method to add a new physiotherapist by key
    public void addPhysioByKey(String key, String name, String expertise, String availableTime) {
        HashMap<String, String> physioDetails = new HashMap<>();
        physioDetails.put("Key", key);
        physioDetails.put("Name", name);
        physioDetails.put("Expertise", expertise);
        physioDetails.put("Available Time", availableTime);

        availablePhysios.add(physioDetails);
    }

    // Method to show available physiotherapists
    public void showAvailablePhysios() {
        try {
            System.out.println("Loading the physios...");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Choose the Physio you may want a appointment");
        System.out.println();
        for (HashMap<String, String> physio : availablePhysios) {
            String physioName = physio.get("Name");  // Get the name (String) from the HashMap
            if (physioName != null && !physioName.isEmpty()) {
                physioName = physioName.substring(0, 1).toUpperCase() + physioName.substring(1).toLowerCase();
            }
            System.out.println("ID: " + physio.get("Key"));
            System.out.println("Name: Dr. " + physioName);
            System.out.println("Expertise: " + physio.get("Expertise"));
            System.out.println("Avaliable From: " + physio.get("Available Time"));
            System.out.println();

        }
    }

    public ArrayList<HashMap<String, String>> getAvailablePhysios() {
        return availablePhysios;
    }
}