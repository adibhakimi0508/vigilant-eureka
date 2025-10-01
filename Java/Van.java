public class Van extends Vehicle {
    private String vanBrand;
    private String vanPlate;
    private String vanColor;

    public Van(int vid, String vt, boolean avl, double ft, double maxdt, Applicant adt, String vb, String vp, String vc) {
        super(vid, vt, avl, ft, maxdt, adt);
        vanBrand = vb;
        vanPlate = vp;
        vanColor = vc;
    }

    public void setVanBrand(String vb) {
        vanBrand = vb;
    }

    public void setVanPlate(String vp) {
        vanPlate = vp;
    }

    public void setVanColor(String vc) {
        vanColor = vc;
    }

    public String getVanBrand() {
        return vanBrand;
    }

    public String getVanPlate() {
        return vanPlate;
    }

    public String getVanColor() {
        return vanColor;
    }

    public String printVan() {
        return String.format("\n\nVan Brand: %s\nVan Plate Number: %s\nVan Color: %s",
                             vanBrand, vanPlate, vanColor);
    }
}
