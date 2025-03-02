package opclinicbookingapp.Helpers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class helpersFunction {
    public String getTime() {
        Instant timeStamp = Instant.now();
        LocalDateTime ldt = LocalDateTime.ofInstant(timeStamp, ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h a");
        String formattedTime = ldt.format(formatter);
        int hour = ldt.getHour();
        return formattedTime + " - " + (hour + 4) + " " + (hour + 4 >= 12 ? "PM" : "AM");
    }
}
