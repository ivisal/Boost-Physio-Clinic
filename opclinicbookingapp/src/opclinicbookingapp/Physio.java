package opclinicbookingapp;

import java.util.HashMap;

public class Physio {
    public HashMap<String, String>[] availablePhysios;

    public Physio() {
        availablePhysios = new HashMap[1];
        availablePhysios[0] = new HashMap<>();
    }

    public void availablePhysios() {
        availablePhysios[0].put("Name", "Dr. John");
        availablePhysios[0].put("Expertise", "Physiotherapist");
        // availablePhysios[0].put("Available Time", );

        System.out.println(availablePhysios[0]);
    }
}
