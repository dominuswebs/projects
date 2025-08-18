public class RoboDog implements Pet {

    private Number charge = 100;

    private String owner = null;

    public Number chargeStatus() {
        return this.charge;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String o) {
        this.owner = o;
    }

    public String favouriteToy() {
        return "Batteries";
    }
}