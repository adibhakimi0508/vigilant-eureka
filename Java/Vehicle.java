public class Vehicle {
    private int vehicleId;
    private String vehicleType;
    private boolean available;
    private double fuelTank;
    private double maxDistance;
    private Applicant applicant;

    public Vehicle(int vid, String vt, boolean avl, double ft, double maxdt, Applicant adt) {
        vehicleId = vid;
        vehicleType = vt;
        available = avl;
        fuelTank = ft;
        maxDistance = maxdt;
        applicant = adt;
    }

    public void setVehicleId(int vid) {
        vehicleId = vid;
    }

    public void setVehicleType(String vt) {
        vehicleType = vt;
    }

    public void setAvailable(boolean avl) {
        available = avl;
    }

    public void setFuelTank(double ft) {
        fuelTank = ft;
    }

    public void setMaxDistance(double maxdt) {
        maxDistance = maxdt;
    }

    public void setApplicantDistance(Applicant adt) {
        applicant = adt;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public boolean getAvailable() {
        return available;
    }

    public double getFuelTank() {
        return fuelTank;
    }

    public double getMaxDistance() {
        return maxDistance;
    }

    public Applicant getApplicantDistance() {
        return applicant;
    }

    public double calcFuelNeeded() {
            double fuelEfficiency = maxDistance/fuelTank;
            return applicant.getDistance() / fuelEfficiency;
    }

    public String toString() {
        return "" + "\n\nVehicle ID: " + vehicleId + 
        "\nVehicle Type: " + vehicleType +
        "\nAvailablity: " + available +
        "\nMax Fuel Tank: " + fuelTank +
        "\nMax Distance in Full Tank: " + maxDistance + "";
    }
}

