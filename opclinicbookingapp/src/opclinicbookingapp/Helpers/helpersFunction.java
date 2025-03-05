package opclinicbookingapp.Helpers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import opclinicbookingapp.Physio;

public class helpersFunction {
    public String getTime() {
        Instant timeStamp = Instant.now();
        LocalDateTime ldt = LocalDateTime.ofInstant(timeStamp, ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h a");
        String formattedTime = ldt.format(formatter);
        int hour = ldt.getHour();
        return formattedTime + " - " + (hour + 4) + " " + (hour + 4 >= 12 ? "PM" : "AM");
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

    public String throwError() {
        System.out.println("Ooho!! Something error occured. Please try again.");
        return null;
    }

}
