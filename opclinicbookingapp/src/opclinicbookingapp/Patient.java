package opclinicbookingapp;

public class Patient {
    private int pId;
    private String patientName;

    public Patient(int  pId, String pName){
        this.setId(pId);
        this.setPatientName(pName);
    }

    public Patient(){
        this.pId = 1;
        this.patientName = "John Doe";
    }

    public void setId(int pId){
        this.pId = pId;
    }
    public void setPatientName(String pName){
        this.patientName = pName;
    }
    public int getId(){
        return this.pId;
    }
}
