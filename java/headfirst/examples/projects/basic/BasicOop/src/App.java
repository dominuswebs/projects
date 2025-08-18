import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
    
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
