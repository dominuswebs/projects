class Duck {

    private int size;

    public Duck(int x) {
        size = x;
    }

    public int getSize() {
        return this.size;
    }
}

class CustomConstructor {

    public static void main (String[] args) {
        Duck d = new Duck(34);

        System.out.println(d.getSize());
    }
}