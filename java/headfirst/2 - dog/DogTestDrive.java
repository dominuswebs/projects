// package dog;

public class DogTestDrive {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.size = 12;
        d.bark(3);
        // left side: compiler, create a variable that will accept an Array of Dogs
        // right side: compiler, create an array of dogs of length 5
        // middle: compiler, assign the created array of dogs to the variable that accepts an array of dogs 
        Dog[] dogs = new Dog[5];

        dogs[0] = new Dog();

        System.out.println(dogs[0]); // dog reference
        System.out.println(dogs[1]); // null
    }
}
