public class Car extends Vehicle {
    private String carBrand;
    private String carPlate;
    private String carColor;

    public Car(int vid, String vt, boolean avl, double ft, double maxdt, Applicant adt, String cb, String cp, String cc) {
        super(vid, vt, avl, ft, maxdt, adt);
        carBrand = cb;
        carPlate = cp;
        carColor = cc;
    }

    public void setCarBrand(String cb) {
        carBrand = cb;
    }

    public void setCarPlate(String cp) {
        carPlate = cp;
    }

    public void setCarColor(String cc) {
        carColor = cc;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public String getCarColor() {
        return carColor;
    }

    public String printCar() {
        return "\n\nCar Brand: " + carBrand +
               "\nCar Plate Number: " + carPlate +
               "\nCar Color: " + carColor + "";
    }
}
