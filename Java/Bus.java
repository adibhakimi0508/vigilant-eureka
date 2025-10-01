public class Bus extends Vehicle {
    private String busBrand;
    private String plateNumber;
    private String busColor;

    public Bus(int vid, String vt, boolean avl, double ft, double maxdt, Applicant adt, String bb, String pn, String bc) {
        super(vid, vt, avl, ft, maxdt, adt);
        busBrand = bb;
        plateNumber = pn;
        busColor = bc;
    }

    public void setBus(String bb, String pn, String bc) {
        busBrand = bb;
        plateNumber = pn;
        busColor = bc;
    }
    
    public String getBusBrand() {
      return busBrand;
    }

    public String getBusPlate() {
        return plateNumber;
    }

    public String getBusColor() {
        return busColor;
    }

    public String printBus() {
        return "\nBus Brand: " + busBrand + "\nPlate Number: " + plateNumber + "\nBus Color: " + busColor;
    }
}