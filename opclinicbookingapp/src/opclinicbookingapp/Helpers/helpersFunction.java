package opclinicbookingapp.Helpers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import opclinicbookingapp.Patient;
import opclinicbookingapp.Physio;

public class helpersFunction {
    public String getTime() {
        Instant timeStamp = Instant.now();
        LocalDateTime ldt = LocalDateTime.ofInstant(timeStamp, ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h a");
        String formattedTime = ldt.format(formatter);
        LocalDateTime newTime = ldt.plusHours(4);
        String formattedNewTime = newTime.format(formatter);
        return formattedTime + " - " + formattedNewTime;
    }

    public boolean isValidPhsio(int physioID) {
        System.out.println("ID is: " + physioID);
        Physio physio = new Physio();
        ArrayList<HashMap<String, String>> availablePhysios = physio.getAvailablePhysios();

        for (HashMap<String, String> physioMap : availablePhysios) {
            if (physioMap.containsKey("Key") && Integer.parseInt(physioMap.get("Key")) == physioID) {
                return true;
            }
        }
        return false;
    }

    public HashMap<String, String> getPhysioByKey(int physioId) {
        Physio physio = new Physio();
        ArrayList<HashMap<String, String>> availablePhysios = physio.getAvailablePhysios();

        for (HashMap<String, String> physioMap : availablePhysios) {
            if (physioMap.containsKey("Key") && Integer.parseInt(physioMap.get("Key")) == physioId) {
                return physioMap;
            }
        }
        return null;
    }

    public HashMap<String, String> getPhysioByName(String physioName) {
        Physio physio = new Physio();
        ArrayList<HashMap<String, String>> availablePhysios = physio.getAvailablePhysios();

        for (HashMap<String, String> physioMap : availablePhysios) {
            if (physioMap.containsKey("Name") && Objects.equals(physioMap.get("Name"), physioName)) {
                return physioMap;
            }
        }
        return null;
    }

    public  HashMap<String, String> getAppointmentByID(
            HashMap<String, HashMap<String, Integer>> appointments,
            HashMap<Integer, Patient> patients, int appointmentID) {

        // Iterate through the appointments map using Map.Entry
        for (Map.Entry<String, HashMap<String, Integer>> entry : appointments.entrySet()) {
            HashMap<String, Integer> appointment = entry.getValue();


            // Check if the appointmentID exists
            if (appointment.containsKey("appointmentID") && appointment.get("appointmentID") == appointmentID) {
                int patientID = appointment.get("patientID");
                for (Map.Entry<Integer, Patient> item : patients.entrySet()) {
                    Patient patient = item.getValue();  // Get the Patient object

                    // Check if the patientID matches the one inside the Patient object
                    if (patient.getId() == patientID) {
                        HashMap<String, String> appointmentDetails = new HashMap<>();
                        appointmentDetails.put("appointmentID", String.valueOf(appointmentID));
                        appointmentDetails.put("patientID", String.valueOf(patient.getId()));
                        appointmentDetails.put("patientName", patient.getPatientName());
                        appointmentDetails.put("patientAge", String.valueOf(patient.getAge()));
                        appointmentDetails.put("patientGender",patient.getGender());
                        appointmentDetails.put("patientAddress",patient.getAddress());
                        appointmentDetails.put("patientPhone",patient.getPhone());
                        return appointmentDetails;
                    }
                }
            }
        }
        return  null;
    }

    public boolean isValidAppointmentID(HashMap<String, HashMap<String, Integer>> appointments,int appID){
        for (Map.Entry<String, HashMap<String, Integer>> entry : appointments.entrySet()) {
            HashMap<String, Integer> appointment = entry.getValue();
            // Check if the appointmentID exists
            if (appointment.containsKey("appointmentID") && appointment.get("appointmentID") == appID) {
                return  true;
            }
        }
        return false;
    }


    public String throwError(String throwMessage) {
        System.out.println(throwMessage);
        return null;
    }
}
