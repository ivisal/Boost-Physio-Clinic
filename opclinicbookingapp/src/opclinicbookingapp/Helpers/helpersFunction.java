package opclinicbookingapp.Helpers;

import java.time.LocalTime;

public class helpersFunction {
    public String getTime() {
        LocalTime currentTime = LocalTime.now();
        return currentTime.plusHours(1) + " " + currentTime.plusHours(5);
    }
}
