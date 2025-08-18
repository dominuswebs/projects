public class Dog extends Animal implements Pet {

    private String owner = null;

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String o) {
        this.owner = o;
    }

    public String makeNoise() {
        return "Woof Woof";
    }
    public String favouriteToy() {
        return "Ball";
    }
}