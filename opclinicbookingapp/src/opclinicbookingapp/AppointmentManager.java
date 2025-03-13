package opclinicbookingapp;

import java.util.HashMap;

public class AppointmentManager {
    private HashMap<Integer, Appointment> appointments = new HashMap<>();
    private int appointmentCounter = 1; // Starts appointment IDs from 1

    public int bookAppointment(int physioId, int patientId) {
        int appointmentId = appointmentCounter++;
        Appointment appointment = new Appointment(appointmentId, physioId, patientId);
        appointments.put(appointmentId, appointment);
        return appointmentId;
    }

    public Appointment getAppointment(int appointmentId) {
        return appointments.get(appointmentId);
    }

    public void cancelAppointment(int appointmentId) {
        appointments.remove(appointmentId);
    }

    public boolean isValidAppointment(int appointmentId) {
        return appointments.containsKey(appointmentId);
    }

    public HashMap<Integer, Appointment> getAllAppointments() {
        return appointments;
    }
}
