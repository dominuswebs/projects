// package dog;

class Dog {
    int size;
    String breed;
    String name;

    public void bark(int numOfBarks) {
        while (numOfBarks > 0) {
            System.out.println("Ruff!");
            numOfBarks -= 1;
        }
    }
}