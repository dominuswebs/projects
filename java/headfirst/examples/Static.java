class Duck {
    private int size;
    private static int nDucks; // this variable is initialized only when the class is first loaded, NOT every time an instance is created

    static { // static initializer. Runs before any static variables can be used or any static methods are called.
        
        System.out.println("Initializing ducks, please wait...");
        
        nDucks = 0;
    }

    public Duck() { // constructor
        nDucks++; // this will increment nDucks every time a new instance is created (the contructor runs)
    }

    public static int getDucks() { // static method, does not need an instance
        return nDucks;
    }

    public int getInstanceDucks() { // public method, an instance can call it
        return nDucks;
    }

    public void setSize(int s) {
        size = s;
    }

    public int getSize() {
        return size;
    }
}

class Static {

    public static void main(String[] args) {
           
        System.out.println(Duck.getDucks()); // value is now 0
        
        Duck d1 = new Duck(); 
        
        System.out.println(Duck.getDucks()); // duck created, value is now 1
        
        Duck d2 = new Duck();

        System.out.println(Duck.getDucks()); // another duck, value is now 2
        System.out.println(d1.getInstanceDucks()); // confirming instance 1 value is 2
        System.out.println(d2.getInstanceDucks()); // confirming instance 1 value is 2
    }
}