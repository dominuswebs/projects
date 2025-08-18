# VSCode 

    To run/debug files without compiling it first, the package declaration needs to be set.

    If we compile, with javac that is not necessary. We need to include all .java files though. So run javac with *.java

    In case of "cannot resolve to a type error", clean the workspace by bringing up the command palette (ctrl/cmd + shift + p) and selecting "Clean the Java language server workspace". A pop up will appear to confirm.

    For more in depth configuration https://code.visualstudio.com/docs/java/java-project

    To create a new project click + under "Java Projects" in the bottom left of Vs Code, select a folder and the path will be set properly.

    When using this Vs Code will create a simple folder structure (src, bin). Add youf files under source and run javac -d ../bin *.java; to dump all the class files into bin. Then navigate to that folder in terminal and run the usual java ###.class

    In settings.json add the path to common libraries

        Open settings.json with cmd + shift + p
        Type "open settings"
        Add/edit the following entry"

            {
                "java.project.referencedLibraries": [
                    "lib/**/*.jar",
                    "path/to/library-name.jar"
                ]
            }

        ATTENTION:

            VS Code Configuration (settings.json): Helps with IDE features (e.g., code completion, refactoring) but does not affect command-line compilation or execution.
            
            Command Line Compilation (javac) and Execution (java): Requires specifying the classpath with -cp or -classpath to ensure the compiler and JVM can locate the necessary libraries.
            
            Including the JAR file in your settings.json helps the IDE but doesn’t substitute for the command-line classpath configuration.

#Intellij

    If you just want to run a specifi file go to run -> edit configurations -> modify options -> do not build before run.

    It is best to use unit tests for this. https://junit.org/junit5/docs/current/user-guide/

    Compile a single file or class
        
        Open the needed file in the editor and go to Build | Recompile 'class name' ( Ctrl Shift F9 ) in the main menu. 
        Alternatively, in the Project tool window, right-click the class you need and from the context menu, select Recompile 'class name'.

    If we build the project/file with intelliJ build tools, class files are in out -> production -> path -> to -> package

    If we instead use terminal (javac and java commands) it is the same as in VsCode.

# Language

    Only one `main` method is required per application

    Understanding variables (it still does my head in)
        ex:
            Dog[] pets = new Dog[7]; 
            
                Read each side from right to left.

                Left side of the declaration (name)

                    set the variable name to "pets" which is an "array" of "Dog"s - pets -> [] -> Dog

                Right side of the declaration (value)

                    set the variable value to a "new" "array of size 7" which contains "Dog"s

    If we want to put all classes and interfaces in one file, we can't add public to them. If we add public, these need to be on their own files.

    Common directory structure

        src - contains all your source files, and possibly the following as well (might be far down the folder tree):
        resources - contains resources such as properties files
        config - everything config related
        lib - a folder containing a your libraries, possibly placed in separate subfolderrs
        bin - contains compiled classes

        See https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html

    Javac - The Java compiler

        https://web.mit.edu/java_v1.1.6/www/tools/javac.html#:~:text=The%20class%20path%20is%20a,specified%20directly%20as%20command%20arguments.

        Look at -classpath

        If we want to add specific files from different folder paths we use the command below, with as many separators as needed

            javac -cp .:/path/to/libs/library-name.jar YourMainClass.java command, the . refers to the current directory, and the : is a classpath separator used to separate multiple paths.

                1. The current directory (.): So that the compiler can find your own classes (e.g., YourMainClass.java or any other class files in the same directory).
            
                2. The JAR file (/path/to/libs/library-name.jar): So that the compiler can find the classes provided by the external library.

            On Windows the separator is a semi-colon (;)

        If we just want to include them all we can just do this:

            // The -cp option is used to specify the classpath, i.e., where the compiler should look for already compiled .class files or libraries.
            javac -d bin -cp ../lib/* src/*.java

        Summary
        
            Use the wildcard (*) when you want to include all JAR files in a directory.
            Use colons (:) or semicolons (;) when you need to specify individual files manually.

        Dependencies

            Some libraries require dependencies, make sure to include them when compiling and running.

                exemple using opencsv:

                javac -d bin -cp ../../../lib/opencsv-5.7.1.jar:../../../lib/commons-text-1.12.0.jar:../../../lib/commons-beanutils-1.9.4.jar:../../../lib/commons-lang3-3.16.0.jar:../../../lib/commons-collections4-4.5.0-M2.jar src/*.java

                java -cp bin:../../../lib/opencsv-5.7.1.jar:../../../lib/commons-text-1.12.0.jar:../../../lib/commons-beanutils-1.9.4.jar:../../../lib/commons-lang3-3.16.0.jar:../../../lib/commons-collections4-4.5.0-M2.jar readWriteFiles.src.ReadWriteFiles 

    Natural ordering

        Java uses Unicode for its alphabetical ordering.

    Streams vs Collections

        Streams differ from collections in several ways:

            No storage. A stream is not a data structure that stores elements; instead, it conveys elements from a source such as a data structure, an array, a generator function, or an I/O channel, through a pipeline of computational operations.

            Functional in nature. An operation on a stream produces a result, but does not modify its source. For example, filtering a Stream obtained from a collection produces a new Stream without the filtered elements, rather than removing elements from the source collection.

            Laziness-seeking. Many stream operations, such as filtering, mapping, or duplicate removal, can be implemented lazily, exposing opportunities for optimization. For example, "find the first String with three consecutive vowels" need not examine all the input strings. Stream operations are divided into intermediate (Stream-producing) operations and terminal (value- or side-effect-producing) operations. Intermediate operations are always lazy.

            Possibly unbounded. While collections have a finite size, streams need not. Short-circuiting operations such as limit(n) or findFirst() can allow computations on infinite streams to complete in finite time.

            Consumable. The elements of a stream are only visited once during the life of a stream. Like an Iterator, a new stream must be generated to revisit the same elements of the source.

    Serialization and Deserialization

        Serialization is a mechanism of converting the state of an object into a byte stream. Deserialization is the reverse process where the byte stream is used to recreate the actual Java object in memory. This mechanism is used to persist the object.

        The byte stream created is platform independent. So, the object serialized on one platform can be deserialized on a different platform. To make a Java object serializable we implement the java.io.Serializable interface. The ObjectOutputStream class contains writeObject() method for serializing an Object. 

        Only the objects of those classes can be serialized which are implementing java.io.Serializable interface. Serializable is a marker interface (has no data member and method). It is used to “mark” java classes so that objects of these classes may get certain capability. Other examples of marker interfaces are:- Cloneable and Remote. 

        Points to remember 

        1. If a parent class has implemented Serializable interface then child class doesn’t need to implement it but vice-versa is not true.
        2. Only non-static data members are saved via Serialization process. 
        3. Static data members and transient (see transient keyword) data members are not saved via Serialization process. So, if you don’t want to save value of a non-static data member then make it transient. 
        4. Constructor of object is never called when an object is deserialized. 
        5. Associated objects must be implementing Serializable interface.

        Each time an object is serialized, the object (including every object in its graph) is “stamped” with a version ID number for the object’s class. The ID is called the serialVersionUID, and it’s computed based on information about the class structure. As an object is being deserialized, if the class has changed since the object was serialized, the class could have a different serialVersionUID, and deserialization will fail! 

        So, the solution is to put a serialVersionUID in your class, and then as the class evolves, the serialVersionUID will remain the same and the JVM will say, “OK, cool, the class is compatible with this serialized object,” even though the class has actually changed. However we need to be sure that when yweou make changes to the class, we take responsibility in our code for the consequences of the changes we made to the class! For example, be sure that our new Dog class can deal with an old Dog being deserialized with default values for instance variables added to the class after the Dog was serialized.

        To get a serialVersionUID for a class, we use the serialver tool that ships with our Java development kit.

        In the command line: serialver Dog

        And then add it in the class 

        public class Dog {
            static final long serialVersionUID = -5849794470654667210L;
            private String name;
            private int size;
            // method code here
        }

        Depending on your folder structure you might need to navigate to the bin folder and then run it like this serialver -classpath bin package.path.className

    Packages

        Package declarations in Java are closely related to the folder structure. They indicate the directory structure in which the Java source files and compiled class files are organized. This is why we cannot run commands directly on the package path folders.

        if we want .class files to be in a bin folder, the fully qualified class name ( package + className) defines the folder structure.

            bin/

                packageA/

                    utilA/
                                                     (package structure) (class name)
                        classA.class // full qualified name packageA.utilA.classA

                    utilB/

                        classB.class // full qualified name packageA.utilB.classB

        If we have a package (a folder foo) with a single file (Bar) we need to:

            navigate to the ROOT of foo,
            compile withthe command: javac foo/Bar.java
            run with the command: java foo.Bar

    Try-with-resources (TWR)

        Only usable with classes that implement Autocloseable

        Compiler makes a finally block for us, so we don't need to explicitly make a catch/finally block

        There are a few more things worth knowing about TWR statements:
            
            You can declare and use more than one I/O resource in a single TWR block:

                try (BufferedWriter writer =
                        new BufferedWriter(new FileWriter(file));
                    BufferedReader reader =
                        new BufferedReader(new FileReader(file))) {}
            
            If you declare more than one resource, they will be closed in the order OPPOSITE to which they were declared; i.e., first declared is last closed.
            
            If you add catch or finally blocks, the system will handle multiple close() invocations gracefully.

## Memory - Stack / Heap

    HEAP

        Also known as "The Garbage-Collective Heap"

        Where all objects live.

        Instance variables are declared inside a class but NOT inside a method. They live inside the object they belong to.

        If the instance variables are primitives, Java assigns space to that object based on the instance types. An Int requires 32 bits and will always use 32 bits of memory even if the number inside is 0. On the other hand if the instance variable is an object, Java only assigns enough memory to contain the reference to the object and not the whole memory needed for that object (that might not even exist yet). That object will be added to the HEAP once it is created.

            public class CellPhone {
                private int pNumber; // 32 bits are added to the object when it is created

                private Antenna ant = new Antenna(); // here only the reference to the Antenna object is stored in the CellPhone object. The Antenna object lives in the HEAP on it's own with it's own memory allocation.
            } 

    STACK

        Where method invocations and local variables live.

        Local variables are declared inside a method, including method PARAMETERS. They are temporary and only live as long as the method is on the stack.

            public void foo (int x) { // x is local variable
                int i = x + 3; // i is a local variable
                boolean b = true; // b is a local variable
            }

        If the local variable is a reference to an object, only the variable (the reference) goes on the stack. So even if an object is created inside a method, that object will be in the HEAP while the reference to it goes in the stack.

            public void foo() {

                Bar b = new Bar(); // b is in the stack (reference), while the Bar object is on the HEAP. 
            } 



## Comparing Variables

    == compares the bits in 2 variables

    To compare 2 primitives, use ==

    To see if 2 references are the same (refer to the same object), use ==

    equals() - To compare 2 different String objects

    To compare if 2 different objects are equal, use the method equals(). ex: ob1.equals(ob2)

## Type casting

    Widening Casting (automatically) - converting a smaller type to a larger type size

        int myInt = 9;
        double myDouble = myInt; // Automatic casting: int to double

        System.out.println(myInt);      // Outputs 9
        System.out.println(myDouble);   // Outputs 9.0

    Narrowing casting must be done manually by placing the type in parentheses in front of the value

        double myDouble = 9.78d;
        int myInt = (int) myDouble; // Manual casting: double to int

        System.out.println(myDouble);   // Outputs 9.78
        System.out.println(myInt);      // Outputs 9

## Autoboxing

    Autoboxing is the automatic conversion that the Java compiler makes between the primitive types and their corresponding object wrapper classes. For example, converting an int to an Integer, a double to a Double, and so on. If the conversion goes the other way, this is called unboxing.

    Autoboxing lets us use either a primitive or its wrapper type almost everywhere.

        Method arguments 

            void takeNumber(Integer i) {} // accepts both integer wrapper and int primitive. This will also work if the argument is (int i)

        Return values

            int returnNumber() {
                return x; // this can be an int or an Integer. The vice versa Integer returnNumber... also works.
            }

        Boolean expressions

            Any place where a boolean value is expected, we can use am expression that evaluates to a boolean (4 > 2), a primitive boolean or a Boolean wrapper.

        Operations on numbers

            i++ works either if "i" is an int primitive or an Integer wrapper.

## Wrappers

    Wrappers have useful static methods:

        .parseInt // takes a string and returns the primitive value based on the wrapper you call this method

        String s = "2";
        int x = Integer.parseInt(s); // this is fine s can be parsed to a number
        double d = Double.parseDouble("420.24");

        boolean b = Boolean.parseBoolean("True"); // this method ignores case 

        HOWEVER

        String t = "two";
        int y = Integer.parseInt(t); // no compile errors but at runtime it throws a NumberFormatException. "two" cannot be parsed to a number.


        Converting primitive number to string,

            Concatenation

                double d = 42.5;
                String doubleString = "" + d;

            .toString
                    
                double d = 42.5;
                String doubleString = Double.toString(d);

            .valueOf // this method returns the String value of pretty much everything. You can convert int to string, long to string, boolean to string, character to string, float to string, double to string, object to string and char array to string.

                double d = 42.5;
                String doubleString = String.valueOf(d);

    Optional

        The way for a method to declare that sometimes it might not return a result is to return an Optional. This is an object that wraps the result. We then use the method isPresent to check if it empty and then use the get method to get the actual value. We always have to use isPresent first, we can't go straight to get.

            Optional<IceCream> optional = getIceCream("Strawberry");

            if(optional.isPresent()) {
                IceCream ice = optional.get();
            } else {
                System.out.println("No ice cream");
            }


## Formatting

    Format specifier 

        %[argument number] [flags] [width] [.precision] type

            [argument number] which arguments if more than one.
            
            [flags] special formatting options: commas, negative sign, left justified
            
            [width] minimum characters that will be used. If the number is less than the width, it will be padded with zeros
            
            [.precision] defines precision
            
            type - d for decimal, f for float, x for hexadecimal, c for character 


    Number formatting

        long myBillion = 1_000_000_000; // we can use underscores to make the number more legible. This is also allowed but not that legible 10_0000_0000.

        String s = String.format("%,d", myBillion); // s is now 1,000,000,000

        The first argument are the instructions to be used on the second argument. "%,d" means take the second argument to this method (myBillion) and format it as a decimal integer and insert commas

        String.format("I have %,.2f bugs to fix.", 476578.09845);

            returns 476,578.10 // it formats the integer part, adding a comma and formats the fractional part by rounding it to 2 decimal points.




## Keywords

    this

        The this keyword refers to the current object in a method or constructor.

        The most common use of the this keyword is to eliminate the confusion between class attributes and parameters with the same name (because a class attribute is shadowed by a method or constructor parameter). If you omit the keyword in the example above, the output would be "0" instead of "5".

        this can also be used to:

            Invoke current class constructor
            Invoke current class method
            Return the current class object
            Pass an argument in the method call
            Pass an argument in the constructor call

    static

        The static keyword is a non-access modifier used for methods and attributes. Static methods/attributes can be accessed without creating an object of a class.
        Math class is a good example. You just call the method with the class name: Math.round(23.3), while non static methods are called by using a reference variable name.
        Static methods can't use non-static (instance) variables or non-static methods. Statics CAN'T see instance variables state.

        Static variables have the same value in all instances of that class. One value per class, instead of one value per instance.

        static initializer is static block that only gets called once, when the class itself is initialized, no matter how many objects of that type are created. 

            static {
                VAL = Math.random()
            }

        Static Context: The main method is static, which means it belongs to the class itself rather than an instance of the class. This is important because you can only directly call static methods and access static variables from a static context. Since the sleepThenPrint method is being called from within the main method (a static context), it also needs to be static.

        In java you can't call a non-static method from a static method.

            public class PredictableSleep {
                public static void main(String[] args) {
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    executor.execute(() -> sleepThenPrint());
                    System.out.println("back in main");
                    executor.shutdown();
                }
    
                private static void sleepThenPrint() {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                        System.out.println("top of the stack");
                }
            }

    throws

        The throws keyword is used tell that a method might throw an exception. One method will catch what another method throws. An exception is always thrown back to the caller. A method can throw more than one exception. Just list them separated by commas. Then in the try/catch block, stack the catches. The order can sometimes matter.

            public void takeRisk() throws BadException, AnotherBadException { 
                if (abandonAllHope) {
                    throw new BadException();
                }
            }

        and when we call the method we need to wrap it in a try/catch block

            public void crossFingers() {
                try {
                    anObject.takeRisk();
                } catch (BadException e) {
                    System.out.println("Aaargh!");
                    e.printStackTrace(); // inherited by all exceptions
                } catch (AnotherBadException ae) {
                    System.out.println("More Aaargh!");
                    ae.printStackTrace(); // inherited by all exceptions
                }
            }

        We can also use "throws" to avoid handling the exception, by declaring it. Using the above example we can get rid off the entire try/catch block by declaring that crossFingers() throws BadException, AnotherBadException. This means that the crossFingers() caller will have to handle the exception. We throw it down the call stack.

    final

        A variable marked final means that once initialized, it can never change, it is a constant. Constants are usually all in uppercase with underscore separating words ex: X_SIZE

        public static final double PI = 3.141592...

            public so any code can access it.
            static so no instance is required, just the class name.
            final so it doesn't change
        
        A final variable means you cant change it's value. These can be instance variables, method local variables and method parameters.

        A final method cannot be overriden

        A final class cannot be extended - no subclass.

    finally

        Can be used at the end of a try catch block ( catch is not necessary, you can have a try/finally block)

            try {

            } catch (Exception e) {

            } finally {

            }

            If the try block fails (an exception), flow control immediately moves to the catch block. When the catch block completes, the finally block runs. When the finally block completes, the rest of the method continues on.

            If the try block succeeds (no exception), flow control skips over the catch block and moves to the finally block. When the finally block completes, the rest of the method continues on.

            If the try or catch block has a return statement, finally will still run! Flow jumps to the finally, then back to the return.

    transient

        Transient is a variable modifier used in serialization. At the time of serialization, if we don't want to save value of a particular variable in a file, then we use transient keyword. When JVM comes across transient keyword, it ignores original value of the variable and instead saves the default value of that variable data type.

        Ex:

            // Making age transient as age is auto- 
            // computable from DOB and current date. 
            transient int age; 

## Classes

    Every Class in Java that doesn't EXPLICITLY extend another class, IMPLICITLY extends Object.

    Object class implements multiple methods which are available in every class:

        equals(Object o)

            tells if 2 objects are considered equal.

            obj1.equals(obj2)

        getClass()

            returns the class the object was instanced from

        hashcode()

            returns a hashcode for the object ( like an unique ID)

        toString()

            returns a string with the class name and the hashcode

        Some of the methods can be overriden (like the toString method) but some are marked final.


    Access levels

        In order from most restrictive to least:

            private -> default -> protected -> public

        public 
        
            members (instance variables and methods) are inherited

        private 
        
            (instance variables and methods) members are NOT inherited
            Only the code inside the class can call private members. This is true of constructors as well. See Math class for an example. 

    Constructor

        If no constructor is explicitly created, the compiler creates on for us. It is the code that runs when we say new (...), meaning it runs BEFORE the object is assigned to a reference.

        To add our own constructor we need to make sure that there is NO return type and that its name is the SAME as the class name.

        public class Duck {
            public Duck() { // custom constructor
                // do duck things like initializing instance variables
                size = 50;
            }
        }

        Constructors ARE NOT inherited.

        You can overload contructors, meaning having multiple constructors in the same class BUT they all need to have different argument lists:

            public class Mushroom {
                public Mushroom() {} // when you don't know nothing about the mushroom

                public Mushroom (int size) {} // when you know the size

                public Mushroom (boolean isMagic) {} // when you know if it is magic

                public Mushroom (int size, boolean isMagic) {} // when you know if it is magic AND the size

                public Mushroom (boolean isMagic, int size) {} // exactly the same as before BUT because the order is different it is also valid.

                 public Mushroom (boolean magic, int mSize) {} // not allowed, already defined with the same order above even if the parameter names are different
            }

        The names of the parameters do not matter, only the TYPE and the ORDER. A constructor that takes an int and a string is different from one that takes a string and an int.

        ALL the constructors in an inheritance tree MUST run when you create an object.

        You can invoke a superclass constructor by calling super(); Like before if we don't define super(), the compiler does it for us.

        The call to super needs to be the first statement in each contructor, even before method variables. The exception is this();

            public Boop() { // Boop contructor
                super(); // call superclass constructor
                // other stuff after
                int i = 10;
            }

        If the superclass constructor takes arguments you can pass them when calling super() like any other method: super(name, size);

        You use this() to call a constructor from another overloaded constructor in the same class. this() can only be used in a constructor and it need to be the first statement in a constructor. a constructor can have a call to super OR this(), but never both.

            import java.awt.Color

            class Mini extends Car {
                private Color color;

                public Mini() { // the no argument constructor supplies a default color and call the overloaded Real constructor ( the one that call super)
                    this(Color.RED);
                }

                public Mini(Color c) {
                    super("Mini"); // the super class constructor expects a brand
                    color = c;
                    // more code
                }
                // this will throw an error, can't have both
                public Mini(int size) {
                    this(Color.RED);
                    super(size);
                }
            }

    Methods

        General syntax for a method

            [access_modifier] [return_type] methodName([parameters]) {
                // method body
            }


    Inheritance

        In Java you can call a superclass method in a subclass even if you override it by using the keyword "super"

        For example both the superclass and a subclass have the method "speak". The subclass can call the superclass "speak" method and then append more code.

        // subclass method

        public void speak() { // override the superclass method
            super.speak(); // call the superclass "speak" method
            // subclass speak code here 
        }

        In Java you CANNOT override instance variables, because overriding variables would fundamentally break code in the superclass. For example, if an override changes the variable's type, that is likely to change the behavior of methods declared in the parent class that used the original variable. This is another reason to use getters and setters.

        Abstract vs Concrete classes

            When designing a class inheritance structure, a decision must be made about which classes are abstract and which are concrete. Concrete classes are those that are specific enough to be instantiated. A concrete class just means that it’s OK to make objects of that type.

            use the keyword "abstract" at the start,

                ex: abstract public class MyNewClass extends MyClass() {}


            An abstract class means the class must be extended; an abstract method means the method must be overridden. Abstract methods can only be declared in abstract classes. The point of an abstract method is that even though they don't have any actual method code, they still define part of the protocol for a group of subtypes (subclasses).

            This is important for Polymorphism. So an abstract method says, “All subtypes of this type have THIS method” for the benefit of polymorphism. Check the "Vet" class example under Polymorphism. The Vet class knows that all Animal subtypes have the "makeNoise" method. This method is abstract in Animal because all classes that extend animal and are concrete will implement their own version but Vet doesn't care, it knows all animals make noise.

            When we say “you must implement the abstract method,” that means a body must be provided. That means creating a non-abstract method with the same method signature (name and arguments) and a return type that is compatible with the declared return type of the abstract method.

    Polymorphism

        Java supports two kinds of polymorphism. You can overload a method with different sets of parameters. The compiler statically binds the method call to a specific method, which we know as static polymorphism.

        Within an inheritance hierarchy, a subclass can override a method of its superclass. The JVM will always call the overridden method if you instantiate the subclass. Even if you cast the subclass to its superclass, the result will be the same. That is dynamic polymorphism.

        ex:

            Dog is a subclass of Animal. To create a Dog object you do Dog myDog = new Dog(); but you can also do Animal myDog = new Dog();
            The reference variable is declared as Animal but the object is created as Dog, with it's own implementation.


        Anything that extends the declared reference variable type can be assigned to the reference variable.This lets you do things like make polymorphic arrays.
        Like this:

            Animal[] animals = new Animal[5];
            animals[0] = new Dog();
            animals[1] = new Cat();

            and so on. we are able to put any subclass of Animal in the Animal array.

            for (Animal animal : animals) {
                animal.eat(); // first iteration run the Dog eat method, second iteration the Cat eat method, third iteration...
                animal.roam(); // first iteration run the Dog roam method, second iteration the Cat roam method, third iteration...
            }

        Arguments and return types can also be polymorphic.

        class Vet {
            public void giveShot(Animal a) {
                // do horrible things to the Animal at
                // the other end of the ‘a’ parameter
                a.makeNoise();
            }
        }
            
        class PetOwner {
            public void start() {
            
                Vet vet = new Vet();
            
                Dog dog = new Dog();
            
                Hippo hippo = new Hippo();
            
                vet.giveShot(dog);
                vet.giveShot(hippo);
            }
        }

        ATTENTION: The compiler checks the class of the reference type — not the object type — to see if you can call a method using that reference.

            example:

                Object o = new Dog();
                o.fetch(); // error. the compiler sees the reference type (object), not the object type (dog). So while the object has the fetch method, the compiler only checks if that method exists in object.

            To turn o into a Dog reference we use CASTING

                Dog d = (Dog) o;
                d.fetch(); // now this works

            If we get the Cast wrong this will throw an error at runtime. To avoid that we use instanceof in a condition like this:

                if( o instance of Dog) {
                    Dog d = (Dog) o;
                }

    Overriding methods

        Arguments must be the same, and return types must be compatible.

        The method can’t be less accessible.

    Overloading a method

        Overloading lets you make multiple versions of a method, with different argument lists, for convenience to the callers.

        The return types can be different.

        You can’t change ONLY the return type. To overload a method, you MUST change the argument list, although you can change the return type to anything.

        You can vary the access levels in any direction.

    Interfaces / Abstract classes

        With interfaces,any method that is not defined as default or static is an abstract method that must be overridden.

        To define an interface:

            public interface Foo {}

        To implement an interface

            public class Bar extends FooBar implements Foo {

                // implement Foo methods

                // implement FooBar methods

                // implement Bar specific methods

            }

        Interfaces are the ultimate in flexibility, because if you use interfaces instead of concrete classes (or even abstract classes) as arguments and return types, you can pass anything that implements that interface. And with an interface, a class doesn’t have to come from just one inheritance tree. A class can extend one class, and implement an interface. But another class might implement the same interface, yet come from a completely different inheritance tree!

    Static methods

        The keyword static lets a method run without any instance of the class. A static method means “behavior not dependent on an instance variable, so no instance/object is required. Just the class. Do not confuse with method references, these are generally used in contexts such as streams or functional programming where you need to pass a method as a parameter.

        Ex:

            MyClass.staticMethod(myArgs) // no instance of my class is needed.

        Static Context:

            A static context is any code inside a static method or block. The main method in Java, for example, is a static method:

            In a static context, you can only directly access static methods and static variables.

    Inner Class

        An inner class can use all the methods and variables of the outer class, even the private ones.

        The inner class gets to use those variables and methods just as if the methods and variables were declared within the inner class.

        An inner class instance must be tied to an outer class instance.

        Usage:
            
            reduce boiler plate code. if 1 big boy class needs some small helper classes that are not going to be used outside the context of the main class (like eventhandlers).

            if one class is implementing more than 1 interfaces then internally it can have multiple classes implementing each interface.

        We can instantiate a inner instance from code running outside the outer class:

            MyOuter outerObj = new MyOuter();
            
            MyOuter.MyInner innerObj = outerObj.new MyInner();

    Anonymous Inner Class

        An anonymous inner class is a way to create a subclass of an abstract class or an implementation of an interface on the fly, without having to give the class a name. It is useful for situations where you need to override a method or implement an interface only once.

        In Java, an anonymous inner class can be used to create an instance of any class or interface, even if it has multiple abstract methods. The only requirement is that you must provide implementations for all the abstract methods of the interface or the class.

        Final Class: Cannot be subclassed, so you cannot create an anonymous inner class that extends a final class.
        
        Final Method: Cannot be overridden, so if you create an anonymous inner class extending a class with final methods, you cannot override those final methods.

    Immediate use objects

        In Java, the compiler allows instantiating an object without assigning it to a variable because the object can be used immediately, and there might be no need to keep a reference to it after its immediate use. This is often seen in cases where the object is used to call a method or perform an action right away.

        This is a common pattern in Java for concise code when you only need to use an object temporarily and don't need to refer to it later. The main reasons this is allowed include:

            Object Lifetime Management: The newly created Random object is used immediately and can be garbage collected after its use. There is no need to keep a reference to it if it won't be used again.

            Readability and Conciseness: In some cases, it makes the code more concise and readable. For example, if you only need to use an object once for a specific method call, you can avoid the extra line needed to declare a variable.

            Syntax and Semantics: Java's syntax and semantics permit this usage. The creation of an object and the invocation of its methods are two distinct operations, and combining them is allowed and often useful.

        Ex: 
            // This line declares and initializes a byte array named array with a size of 256. Each element in the array can hold a byte value (from -128 to 127).
            byte[] array = new byte[256];
            // Here, a new instance of the Random class is created, and its nextBytes method is called. This method fills the array with random byte values.
            new Random().nextBytes(array);

## Data Structures

## Design Patterns

    Stateful / mutable state

        Ex: Matcher class, Database Connections, File streams

        Classes with stateful operations are those that maintain internal state that can change over time and must be managed carefully to ensure consistency and correct behavior.

        Key Concepts

            Stateful Classes:

                These classes maintain internal state that influences their behavior and the results of their methods. For example, Matcher maintains the current position and state of the search operation.
            
            Mutable State:

                The internal state of the object can change, and methods may depend on or modify this state. In Matcher, the position within the input string changes as you call find().
            
            State Management:

                Stateful classes require careful management of their state. Operations that depend on the internal state (like calling start() after find()) must be performed in the correct order to avoid errors.

    Factory

        The Factory Design Pattern is a creational design pattern that provides a way to create objects without specifying the exact class of object that will be created. It encapsulates the instantiation process, allowing you to manage and control object creation in a centralized manner.

        Key Concepts

            Factory Method: A method or a class responsible for creating instances of a certain type.

            Encapsulation: It hides the instantiation logic from the client code. The client only interacts with the factory and not with the specific classes.

        Example:

            Step 1: Define a Common Interface
                
                Define a common interface or abstract class for the products.

                    public interface Product {
                        void use();
                    }

            Step 2: Implement Concrete Products

                Define concrete classes that implement the Product interface.

                // ConcreteProductA.java
                public class ConcreteProductA implements Product {
                    @Override
                    public void use() {
                        System.out.println("Using ConcreteProductA");
                    }
                }

                // ConcreteProductB.java
                public class ConcreteProductB implements Product {
                    @Override
                    public void use() {
                        System.out.println("Using ConcreteProductB");
                    }
                }

            Step 3: Create a Generic Factory

                Define a generic factory class that can create instances of any type that extends Product.

                // GenericFactory.java
                public class GenericFactory<T extends Product> {
                    private Class<T> productClass;

                    public GenericFactory(Class<T> productClass) {
                        this.productClass = productClass;
                    }

                    public T createProduct() {
                        try {
                            return productClass.getDeclaredConstructor().newInstance();
                        } catch (Exception e) {
                            throw new RuntimeException("Failed to create product", e);
                        }
                    }
                }

            Step 4: Use the Generic Factory

                Instantiate the factory with the desired product type and create instances using it.

                public class Main {
                    public static void main(String[] args) {
                        // Create factory for ConcreteProductA
                        GenericFactory<ConcreteProductA> factoryA = new GenericFactory<>(ConcreteProductA.class);
                        Product productA = factoryA.createProduct();
                        productA.use();

                        // Create factory for ConcreteProductB
                        GenericFactory<ConcreteProductB> factoryB = new GenericFactory<>(ConcreteProductB.class);
                        Product productB = factoryB.createProduct();
                        productB.use();
                    }           
                }
                
        Explanation

            Generic Factory (GenericFactory<T>): This class is parameterized with a type that extends Product. It uses reflection to create instances of the specified type.

            Concrete Products (ConcreteProductA, ConcreteProductB): Implement the Product interface and provide specific behavior.
            
            Usage: You create a factory for each type of product by passing the class type to the GenericFactory. This allows you to create instances of that type without knowing the specifics of the instantiation process.

## Exceptions

    Methods in Java use exceptions to tell the calling code, “Something Bad Happened. I failed.”

    This means these methods NEED to be wrapped in a try/catch block. If a method has a "throws ...Exception", then it needs to be wrapped in a try/catch block unless it is an unchecked exception.

    Check the "throws" entry under "keywords" for more information.

    The compiler checks for everything except RuntimeExceptions since most of them come from a problem in our code logic. A try/catch is for handling exceptional situations, not flaws in our code. We use catch blocks to try to recover from situations we can’t guarantee will succeed. ( like is the server up, does the file exist).

    Exceptions are polymorphic

    In Java, exceptions are divided into checked and unchecked exceptions:

        Checked Exceptions: These must be either caught or declared in the method signature using throws. Examples include IOException, SQLException, etc.

        Unchecked Exceptions: These are subclasses of RuntimeException and are not required to be caught or declared. Examples include NullPointerException, IllegalArgumentException, etc.

## Generics

    Generic Method: Generic Java method takes a parameter and returns some value after performing a task. It is exactly like a normal function, however, a generic method has type parameters that are cited by actual type. This allows the generic method to be used in a more general way. The compiler takes care of the type of safety which enables programmers to code easily since they do not have to perform long, individual type castings.

        When declaring a type paramenter of a class, it can be used anywhere where a real class or interface type.

            public class MyList<E> {
                public boolean add(E o) // E has been defined as part of the class
            }

        We can also use a type paramater that was not defined in the class declaration

            public class AnimalList() {
                public <T extends Animal> void listAnimals(ArrayList<T> list) // T was declared at the start of themethod declaration before the return type. This method says that T can be any type of Animal
            }

    Generic Classes: A generic class is implemented exactly like a non-generic class. The only difference is that it contains a type parameter section. There can be more than one type of parameter, separated by a comma. The classes, which accept one or more parameters, are known as parameterized classes or parameterized types.

    As convention we use “T” (“Type”) unless we’re specifically writing a collection class, where we would use “E” to represent the “type of the Element the collection will hold.” Sometimes we see “R” for “Return type.”

    ex:

        // Java program to show working of user defined
        // Generic classes
        
        // We use < > to specify Parameter type
        class Test<T> {
            // An object of type T is declared
            T obj;
            Test(T obj) { this.obj = obj; } // constructor
            public T getObject() { return this.obj; }
        }
        
        // Driver class to test above
        class Main {
            public static void main(String[] args)
            {
                // instance of Integer type
                Test<Integer> iObj = new Test<Integer>(15);
                System.out.println(iObj.getObject());
        
                // instance of String type
                Test<String> sObj
                    = new Test<String>("GeeksForGeeks");
                System.out.println(sObj.getObject());
            }
        }

        You cannot use primitives between <>, you have to use their respective parameterised type so instead of "int" you use "Integer".

        Type inference

            The compiler infers the type on the right hand side from the type on the left hand side.

                ArrayList<String> sentences = new ArrayList<> is the same as ArrayList<String> sentences = new ArrayList<String>

        In generics "extends" means EXTENDS or IMPLEMENTS

        Wildcards - ? extends

            Wildcard is a way to create a method argument that can accept a LIST of any subclass. This allows us to call methods on the elements of the List, but we cannot add elements to the list, we can do things with the list elements but not put new things in the list.

            public void takeAnimals(List<? extends Animal> animals) {
                for (Animal a : animals) {
                    a.eat();
                }
            }
        
        Using the method's generic type parameter

            When we call this method we know we are going to get the same type back

                public <T extends Foo> List<T> takeFoos(List<T> list) { }
            
            While if we use the wildcard for both the method parameter and return type, there is nothing to guarantee they're the same type.

                public List<? extends Foo> takeFoos(List<? extends Foo> foos) { }

            If we want to do something with the type itself, use type parameter (T). If we just want to allow all subtypes of a type, use a wildcard.


## I/O

    NIO - Non blocking I/O

    NIO.2 - Enhanced version of NIO, released with Java 7.

    Java sockets article https://www.linkedin.com/pulse/java-sockets-io-blocking-non-blocking-asynchronous-aliaksandr-liakh/


## Lambda

    Functional Interfaces a.k.a Single Abstract Method (SAM) Interfaces
    
    If an interface only has one method thet needs to be implemented, that interface can be implemented as a lambda expression. We don't need to create the entire class, the compiler can infer it. We only have to worry about the logic inside the method. The interface itself might have more methods but it can only have one Single Abstract Method for it to be a Functional Interface. 

        // check JukeBox program for a working example
        ex:
            // usual way
            class TitleCompare implementes Comparator<Song> {
                public int compare(Song one, Song two) {
                    return one.getTitle().compareTo(two.getTitle());
                }
            }
            // using a lambda
            songList.sort((one, two) -> one.getTitle().compareTo(two.getTitle()));

    Lambda expressions are usually passed as parameters to a function:

        import java.util.ArrayList;

        public class Main {
            public static void main(String[] args) {
                ArrayList<Integer> numbers = new ArrayList<Integer>();
                numbers.add(5);
                numbers.add(9);
                numbers.add(8);
                numbers.add(1);
                numbers.forEach( (n) -> { System.out.println(n); } );
            }
        }

    Lambda expressions can be stored in variables if the variable's type is an interface which has only one method. The lambda expression should have the same number of parameters and the same return type as that method. Java has many of these kinds of interfaces built in, such as the Consumer interface (found in the java.util package) used by lists

        import java.util.ArrayList;
        import java.util.function.Consumer;

        public class Main {
            public static void main(String[] args) {
                ArrayList<Integer> numbers = new ArrayList<Integer>();
                numbers.add(5);
                numbers.add(9);
                numbers.add(8);
                numbers.add(1);
                Consumer<Integer> method = (n) -> { System.out.println(n); };
                numbers.forEach( method );
            }
        }

    To use a lambda expression in a method, the method should have a parameter with a single-method interface as its type (see above).
    Calling the interface's method will run the lambda expression:

        interface StringFunction {
            String run(String str);
        }

        public class Main {

            public static void main(String[] args) {
                StringFunction exclaim = (s) -> s + "!";
                StringFunction ask = (s) -> s + "?";
                printFormatted("Hello", exclaim);
                printFormatted("Hello", ask);
            }

            public static void printFormatted(String str, StringFunction format) {
                String result = format.run(str);
                System.out.println(result);
            }
        }

## Operators

    Boolean operators
    
        Not Equals (!= and !)

        Short circuit operators (&& and ||)

            if the JVM determines that the left side of a && expression is FALSE, it immediately evaluates to false.

            If the JVM sees that the left side of a || expression is TRUE, it immediately evaluates to true.

        Non short circuit operators (& and |)

            When used in boolean expressions, the & and | operators act like their && and || counterparts except they FORCE THE JVM to check both sides.
            Usually they are used in other contexts, bit manipulation.
    
    Method References ::

        The :: operator in Java is known as the method reference operator. It is used to refer to a method without invoking it. In this context, Song::getYear is a method reference that refers to the getYear method of the Song class.

        Comparator.comparing(Song::getYear): 
        
            This creates a comparator that compares Song objects based on the value returned by the getYear method. 
            
        reversed(): 
        
            This reverses the order of the comparator, changing it from ascending to descending order.

        So, Song::getYear is a shorthand way of saying "use the getYear method of the Song class to get the year for comparison."

        This is equivalent to writing:

            Comparator<Song> comparator = (s1, s2) -> Integer.compare(s1.getYear(), s2.getYear());

        And then reversing it with:

            comparator = comparator.reversed();

## Multithreading

    Runnable is the core interface provided for representing multithreaded tasks, and Java 1.5 provided Callable as an improved version of Runnable. https://www.baeldung.com/java-runnable-callable

    Thread class

        Part of the java.lang package so no need to import it.
    
        No longer recommended

        Requires to keep track of all threads and make sure that they are closed at end.

    
    ExecutorService

        Interface in java.util.concurrent

        The java.util.concurrent.Executors class has factory methods to create the ExecutorService instances.

        ExecutorServices become really helpful when we’re starting lots of independent jobs. We don’t necessarily want to create a new Thread for each of these jobs, and we don’t want to keep track of all these Threads. There are different ExecutorService implementations depending upon how many threads we’ll want to start (or especially if we don’t know how many Threads we’ll need), including ExecutorServices that create Thread pools. Thread pools let us reuse Thread instances, so we don’t have to pay the cost of starting up new Threads for every job.

        https://www.baeldung.com/java-executor-service-tutorial

        Factory methods:

            ExecutorService newCachedThreadPool()

                Creates a thread pool that creates new threads as needed, but will reuse previously constructed threads when they are available.

            ExecutorService newFixedThreadPool(int nThreads)

                 Creates a thread pool that reuses a fixed number of threads operating off a shared unbounded queue.

            ScheduledExecutorService newScheduledThreadPool(int corePoolSize)

                Creates a thread pool that can schedule commands to run after a  given delay, or to execute periodically.

            ExecutorService newSingleThreadExecutor()

                Creates an Executor that uses a single worker thread operating off an unbounded queue.

            ScheduledExecutorService newSingleThreadScheduledExecutor()

                Creates a single-threaded executor that can schedule commands to run  after a given delay, or to execute periodically.

            ExecutorService newWorkStealingPool()
    
                Creates a work-stealing thread pool using the number of available processors as its target parallelism level.

        These ExecutorServices use some form of Thread Pool. This is a collection of Thread instances that can be used (and reused) to perform jobs. 
        How many threads are in the pool, and what to do if there are more jobs to run than threads available, depends on the ExecutorService implementation.

        Although the thread pools will take care of individual Threads, we do need to close the pool when we’re finished with it. 
        That way, the pool can empty its job queue and shut down all of its threads to free up system resources. ExecutorService has two shutdown methods.

            ExecutorService.shutdown()

                Calling shutdown() asks the ExecutorService nicely if it wouldn’t mind awfully wrapping things up so everyone can go home.  
                All of the Threads that are currently running jobs are allowed to finish those jobs, and any jobs waiting in the queue will also be finished off. 
                The ExecutorService will reject any new jobs too.  If we need our code to wait until all of those things are finished, we can useawaitTermination to sit and wait until it’s finished. 
                We give awaitTermination a maximum amount of time to wait for everything to end, so awaitTermination will hang around until either the ExecutorService has finished everything or the timeout has been reached, whichever is earlier.

            ExecutorService.shutdownNow()

                When this is called, the ExecutorService will try to stop any Threads that are running, will not run any waiting jobs, and definitely won’t let anyone else into the pool.  
                Use this if you need to put a stop to everything. This is sometimes used after first calling shutdown() to give the jobs a chance to finish before pulling the plug entirely.

                

    3 states of a new thread    

        (new) - instance has been created BUT not started

        (runnable) - after we start the thread. It is ready to run and just waiting for the JVM to execute it. Only the JVM thread scheduler can make that decision, we can only nudge it sometimes.

        (running) - Only the JVM thread scheduler can make that decision, we can only nudge it sometimes. In the running state, a thread ( and only this thread) has an active call stack.

    Non-runnable state

        The thread scheduler can move a running thread into a blocked state, for a variety of reasons. For example, the  thread might be executing code to read from an input stream, but there isn’t any data to read. The scheduler will move the thread out of the running state until something becomes available. Or the executing code might have told the thread to put itself to sleep (sleep()). Or the thread might be waiting because it tried to call a method on an object, and that object was “locked.” In that case, the thread can’t continue until the object’s lock is freed by the thread that has it.

    The thread scheduler

        The thread scheduler makes all the decisions about who moves from runnable to running, and about when (and under what circumstances) a thread leaves the running state. The scheduler decides who runs, for how long, and where the threads go when it decides to kick them out of the currently running state.  
        
        You can’t control the scheduler. There is no API for calling methods on the scheduler. Most importantly, there are no guarantees about scheduling!

        So what does this mean for write-once-run-anywhere? It means that to write platform-independent Java code, your multithreaded program must work no matter how the thread scheduler behaves.

        Multithreaded programs are not deterministic; they don’t run the same way every time. The thread scheduler can schedule each thread differently each time the program runs.

    Synchronizers
    
        high-level concurrency constructs introduced in the java. util. concurrent package, designed to coordinate the execution of multiple threads and control access to shared resources.

        https://www.baeldung.com/java-cyclicbarrier-countdownlatch

        CountDownLatch

            A CountDownLatch is a construct that a thread waits on while other threads count down on the latch until it reaches zero.

            We can think of this like a dish at a restaurant that is being prepared. No matter which cook prepares however many of the n items, the waiter must wait until all the items are on the plate. If a plate takes n items, any cook will count down on the latch for each item she puts on the plate.

        CyclicBarrier

            A CyclicBarrier is a reusable construct where a group of threads waits together until all of the threads arrive. At that point, the barrier is broken and an action can optionally be taken.

            We can think of this like a group of friends. Every time they plan to eat at a restaurant they decide a common point where they can meet. They wait for each other there, and only when everyone arrives can they go to the restaurant to eat together.

        Phaser API

            https://www.baeldung.com/java-phaser

            The Phaser is a barrier on which the dynamic number of threads need to wait before continuing execution. Contrary to CountDownLatch that requires the number be supplied when creating the instance.

        Concurrency

            

## Java library/API

    import

        import packageName.className for specific classes

        import packageName.* can be used to import all classes under that package name but should be avoided.

        import static packageName.ClassName.* can be used to access ClassName methods without putting the class name first

            import without static import

                import java.util.stream.Collectors; // requires Collectors before the method. Collectors.toList()

            import with static

                java.util.stream.Collectors.*; // with .* all the class methods can be used directly

                java.util.stream.Collectors.toList; // only the toList method is available without class name first. 

        We can also use the fully-qualified name if we dont want to import it.

            java.util.ArrayList<String> arrayList = new java.util.ArrayList<String>();

    java.lang

        These classes come pre-built. No need to import.

    java.util

        java.utilArrayList

            ArrayList<Egg> myList = new ArrayList<Egg>();

        java.util.List

            List is an interface of ArrayList, which means its methods are available in ArrayList, LinkedList, CopyOnWriteArrayList and others

            sort(Comparator) sorts the list according to the order defined by the comparator

        java.util.Collections

            sort(List) sorts the specified list in ascending order, according to the natural ordering of its elements.

            sort(List, Comparator) sorts the list according to the order defined by the comparator

            list - when sequence matters
            
                Lists know where something is in the list. You can have more than one element referencing the same object.

            set - when uniqueness matters
            
                Sets know whether something is already in the collection. You can never have more than one element referencing the same object or more than one element referencing two objects that are considered equal.

            map - when finding something by key matters
            
                Collections that use key-value pairs. Maps know the value associated with a given key. You can have two keys that reference the same value, but you cannot have duplicate keys. A key can be any object.

            Convenience Factory Methods for Collections

                The resulting collections cannot be changed. You can’t add to them or alter the values

                The resulting collections are not standard Collections. These are not ArrayList, HashSet, HashMap, etc. We can rely on them to behave according to their interface: a List will always preserve the order in which the elements were placed; a Set will never have duplicates. But we can’t rely on them being a specific implementation of List, Set, or Map.

                Creating a List: List.of()

                    To create the list of Strings:

                        List<String> strings = List.of("somersault", "cassidy", "$10");

                    If we want to add Foo objects instead:

                        List<Foo> foos = List.of(new Foo("somersault", "zero 7", 147),
                                                 new Foo("cassidy", "grateful dead", 158),
                                                 new Foo("$10", "hitchhiker", 140));
                
                Creating a Set: Set.of()
            
                    Creating a Set uses very similar syntax:

                        Set<Book> books = Set.of(new Book("How Cats Work"),
                                                 new Book("Remix your Body"),
                                                 new Book("Finding Emo"));
                
                Creating a Map: Map.of(), Map.ofEntries()
                    
                    Maps are different, because they take two objects for each “entry”—a key and a value. If we want to put less than 10 entries into your Map, we can use Map.of, passing in key, value, key, value, etc.:
                        
                        Map<String, Integer> scores = Map.of("Kathy", 42, "Bert", 343, "Skyler", 420);

                    If we have more than 10 entries, or if we want to be clearer about how our keys are paired up to their values, we can use Map.ofEntries instead:

                        Map<String, String> stores = Map.ofEntries(Map.entry("Riley", "Supersports"),
                                                                   Map.entry("Brooklyn", "Camera World"),
                                                                   Map.entry("Jay", "Homecase"));
    
        java.util.stream
        
            Classes to support functional-style operations on streams of elements, such as map-reduce transformations on collections.

            Intermediate operations:

                Stream methods that RETURN ANOTHER STREAM

                    ex: limit

            Terminal operations:
            
                Stream methods that return something that can be used, usually a collection.

                ex: collect, count

                IMPORTANT - Once a terminal operation runs the stream is closed.

                The terminal operation is responsible for looking at the whole list of instructions, all those intermediate operations in the pipeline, and then running the whole set together in one go. Terminal operations are eager; they are run as soon as they’re called.

                This means that in theory it’s possible to run the combination of instructions in the most efficient way. Instead of having to iterate over the original collection for each and every intermediate operation, it may be possible to do all the operations while only going through the data once.

            The source, intermediate operation(s) and the terminal operation all combine to form a stream pipeline. This pipeline represents a query on the original collection.

            Stream<T> distinct()

                Returns a stream consisting of the distinct elements
                
            Stream<T> filter (Predicate<? super T> predicate)

                Returns a stream of the elements that match the given predicate.

            Stream<T> limit (long maxSize)

                Returns a stream of elements truncated to be no longer than max-Size in length.

            <R> Stream<R> map (Function<? super T, ? extends R> mapper)

                Returns a stream with the results of applying the given function to the elements of this stream.

            Stream<T> skip (long n)

                Returns a stream of the remaining elements of this stream after discarding the first n elements of the stream.

            Stream<T> sorted()

                Returns a stream of the elements of this stream, sorted according to natural order.

    java.nio

        java.nio.file

            Is all we need to do common text file reading and writing, and it also provides us with the ability to manipulate a computer’s directories and directory structure.

                The Path interface: We’ll always need a Path object to locate the directories or files we want to work with.

                The Paths class: We’ll use the Paths.get() method to make the Path object we’ll need when we use methods in the Files class.

                The Files class: This is the class whose (static) methods do all the work we’ll want to do: making new Readers and Writers, and creating, modifying, and searching through directories and files on file systems.

        java.nio.file.attribute

            Used to manipulate the metadata associated with a computer’s files and directories. For example, we would use the classes in this package if we wanted to read or change a file’s
            permissions settings.

    Here's how you typically work with Java libraries:

        Binary (JAR files):

            You download the JAR file(s) of the library.
            Include the JAR file(s) in your project's classpath.
            Use the classes and methods provided by the library in your code.


            When you download a Java library, it's common to get two JAR files:

                Library JAR (e.g., library-name.jar): This is the main binary JAR file containing the compiled classes that you will include in your project's classpath.

                Source JAR (e.g., library-name-sources.jar): This JAR file contains the source code of the library. It's useful for debugging or understanding how the library works. It doesn’t need to be included in the classpath for your project to work, but it can be helpful if your IDE supports attaching source files for better debugging and code navigation.

            To summarize:

                Include the main JAR file (library-name.jar) in your project’s classpath to use the library.
                The source JAR (library-name-sources.jar) is optional and can be attached in your IDE for reference or debugging.

        Source files:

            Source files (.java) are generally used if you want to modify the library or understand its internal workings.
            Some developers prefer to include the source files for debugging purposes, but in regular usage, the binary files are sufficient.

## Buffers

Writing Process with BufferedWriter

    Initial Write to Buffer:

        When you write data to a BufferedWriter, the data is first stored in its internal buffer. The buffer accumulates the data until it's full.

    Buffer Full:
        
        If the data you’re writing exceeds the buffer size, the BufferedWriter automatically passes the contents of the buffer to the underlying Writer (like FileWriter). The FileWriter then writes this data to the file.

    Buffer Empties and Refill:
    
        Once the buffer is flushed to the underlying Writer, the buffer is cleared and ready to accept more data. The BufferedWriter continues to accumulate more data in the buffer until it's full again, at which point it repeats the flushing process.

    Flush or Close:

        The buffer can also be manually flushed using the flush() method, which forces the BufferedWriter to write all data currently in the buffer to the underlying Writer. This is important to do if you need to ensure that all data is written out before the program continues or terminates. When you close the BufferedWriter, it automatically flushes any remaining data in the buffer before closing the underlying Writer.

    Example Flow
    
        Let’s say the buffer size is 8 KB, and you’re writing 20 KB of data:

            The first 8 KB fills the buffer.
            The buffer is flushed, and the 8 KB is passed to the FileWriter, which writes it to the file.
            The buffer is cleared, and the next 8 KB is written to the buffer.
            The buffer is again flushed to the FileWriter.
            The remaining 4 KB of data is written to the buffer.
            When you close the BufferedWriter or flush it manually, the last 4 KB is flushed to the FileWriter.
    
    Advantages of BufferedWriter
    
        Efficiency: Reduces the number of I/O operations by grouping multiple writes into a single operation.
    
        Performance: Improves performance, especially when writing small amounts of data multiple times, by avoiding frequent disk writes.
    
    In summary, the BufferedWriter manages the buffering automatically. It flushes the buffer to the underlying Writer (like FileWriter) when necessary, ensuring efficient data writing even when the data 
    size exceeds the buffer size.

## GUI

    https://www.javatpoint.com/awt-and-swing-in-java

    https://www.linkedin.com/pulse/awt-vs-swing-javafx-incus-data-pty-ltd/

    AWT is indeed a dependency of Swing. Swing is built on top of AWT, meaning many Swing components inherit from AWT classes. For example, JPanel, JFrame, and other Swing containers inherit from java.awt.Container, which itself inherits from java.awt.Component.

    When you import and use Swing components, the necessary AWT classes are implicitly available because they are part of the inheritance chain. But if you need to use specific AWT classes (like Color, Font, or Toolkit), you would need to import them explicitly

    AWT

    Swing

    JavaFX



## JUnit - Tests

    https://junit.org/junit5/docs/current/user-guide/

    

            


