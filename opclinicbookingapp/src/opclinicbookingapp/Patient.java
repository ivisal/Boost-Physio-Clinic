package opclinicbookingapp;

public class Patient {
    private int pId;
    private String patientName;
    private int age;
    private String gender;
    private String address;
    private String phone;

    public Patient(int pId, String pName) {
        this.setId(pId);
        this.setPatientName(pName);
    }

    public Patient() {
        this.pId = 1;
        this.patientName = "John Doe";
        this.age = 0;
        this.gender = "Male";
        this.address = "";
        this.phone = "";
    }

    public void setId(int pId) {
        this.pId = pId;
    }

    public void setPatientName(String pName) {
        this.patientName = pName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return this.pId;
    }

    public String getPatientName() {
        return this.patientName;
    }

    public int getAge() {
        return this.age;
    }

    public String getGender() {
        return this.gender;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhone() {
        return this.phone;
    }
    @Override
    public String toString() {
        return "Patient{patientID=" + this.pId + ", name=" + this.patientName + ", age="+this.age+",gender="+this.gender+", address="+this.address+", phone="+this.phone+"}";
    }

    public String getPatientCredentials() {
        return "Patient ID: " + this.pId + "\n" +
                "Name: " + this.patientName + "\n" +
                "Age: " + this.age + "\n" +
                "Gender: " + this.gender + "\n" +
                "Address: " + this.address + "\n" +
                "Phone: " + this.phone;
    }
    // Override the toString() method to print meaningful information

}