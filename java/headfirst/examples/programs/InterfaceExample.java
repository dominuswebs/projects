import java.util.ArrayList;

abstract class Animal {
    public abstract String makeNoise();
}

interface Pet {
    public abstract String getOwner();
    public abstract void setOwner(String o);
    public abstract String favouriteToy();
}

class Dog extends Animal implements Pet {

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

class RoboDog implements Pet {

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

class InterfaceExample {
    public static void main (String[] args) {

        RoboDog t1000 = new RoboDog();
        Dog rex = new Dog();
        ArrayList<Pet> pets = new ArrayList<Pet>();

        pets.add(t1000);
        pets.add(rex);

        rex.setOwner("John");
        t1000.setOwner("Connor");

        for (Pet pet : pets) {
            System.out.println(pet.favouriteToy());
            System.out.println(pet.getOwner());
        }
    }
}
