public class Applicant {
    private int applicantId;
    private String name;
    private String contactNumber;
    private String applicationDate;
    private String vehicleNeeded;
    private String reasonOfUsage;
    private String returnDate;
    private double distance;

    public Applicant(int id, String nm, String ctc, String date, String vn, String row, double dt) {
        applicantId = id;
        name = nm;
        contactNumber = ctc;
        applicationDate = date;
        vehicleNeeded = vn;
        reasonOfUsage = row;
        distance = dt;
    }

    public void setApplicantId(int id) {
        applicantId = id;
    }

    public void setName(String nm) {
        name = nm;
    }

    public void setContactNumber(String ctc) {
        contactNumber = ctc;
    }

    public void setApplicationDate(String date) {
        applicationDate = date;
    }

    public void setVehicleNeeded(String vn) {
        vehicleNeeded = vn;
    }

    public void setReasonOfUsage(String row) {
        reasonOfUsage = row;
    }

    public void setDistance(double dt) {
        distance = dt;
    }

    public int getApplicantId() {
        return applicantId;
    }
   
    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getVehicleNeeded() {
        return vehicleNeeded;
    }

    public String getReasonOfUsage() {
        return reasonOfUsage;
    }

    public double getDistance() {
        return distance;
    }

    public String toString() {
        return "\nApplicant ID: " + applicantId +
               "\nApplicant Name: " + name +
               "\nApplicant Contact Number: " + contactNumber +
               "\nApplication Date: " + applicationDate +
               "\nVehicle Needed: " + vehicleNeeded +
               "\nReason of Usage: " + reasonOfUsage +
               "\nDistance in KM: " + distance + "\n";
    }
}
