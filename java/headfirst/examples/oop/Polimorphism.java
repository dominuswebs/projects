import java.util.ArrayList;

abstract class Animal {
    public abstract void makeNoise();
}

abstract class Fish extends Animal {
    public void swim(){
        System.out.println("Just keep swimming");
    }
}

class Dog extends Animal {

    public void fetch() {
        System.out.println("double oof!");
    }

    public void makeNoise() {
        System.out.println("oof");
    }
}

class Sardine extends Fish {
    public void makeNoise(){
        System.out.println("Glub Glub");
    };
}

class Vet {
    public void giveInjection(Animal a) {
        a.makeNoise();
    }
}

class Polimorphism {
    public static void main(String[] args) {

        Vet v = new Vet();
        Sardine s = new Sardine();
        Dog d = new Dog();


        // polimorphism, an Array objects of any subclass of Animal
        Animal[] animals = new Animal[2];
        animals[0] = s;
        animals[1] = d;
        for(Animal animal: animals) {
            v.giveInjection(animal); // this Vet method accepts any Animal type
        }

        s.swim();

        ArrayList<Animal> animalsList = new ArrayList<Animal>();

        animalsList.add(s);
        animalsList.add(d);
        
        
        // this will not work, even though the Object referenced at position 1 is a Dog, the "get" method always returns type Animal ( the ArrayList type )
        // Dog newDog = animalsList.get(1);
        // we can however do this but only the methods defined in the Animal class will be available
        Animal newAnimal = animalsList.get(1);
        newAnimal.makeNoise();
        // Compiler throws an error because fetch is not implemented by Animal
        // newAnimal.fetch();

        System.out.println(newAnimal.getClass()); // class Dog
        System.out.println(newAnimal.toString()); // Dog@12345
        // this will not work either
        // Dog newDog = newAnimal;

        Object o = new Dog();
        //o.fetch(); // this fails. but we can Cast o to a Dog reference
        Dog newDog = (Dog) o; // cast o as Dog
        newDog.fetch(); // this now works
        // both newDog and o point (reference) the same object. But o only has access to Object methods while newDog has access to all Dog methods.
        // we can use instanceof to make sure that the cast works wo avoid runtime errors
        // so we rewrite the cast above like this

        if( o instanceof Dog) {
            Dog newNewDog = (Dog) o;
        }
    }
}