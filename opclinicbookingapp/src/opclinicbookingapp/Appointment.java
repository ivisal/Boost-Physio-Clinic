package opclinicbookingapp;

public class Appointment {
    private int appointmentId;
    private int physioId;
    private int patientId;

    public Appointment(int appointmentId, int physioId, int patientId) {
        this.appointmentId = appointmentId;
        this.physioId = physioId;
        this.patientId = patientId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public int getPhysioId() {
        return physioId;
    }

    public int getPatientId() {
        return patientId;
    }

    @Override
    public String toString() {
        return "Appointment{ID=" + appointmentId + ", PhysioID=" + physioId + ", PatientID=" + patientId + "}";
    }
}
