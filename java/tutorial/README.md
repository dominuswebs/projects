Javac - Java compiler

Compiles .java files into .class files 

JVM - Java Virtual Machine

Runs .class files

Each Java file can only have one Class or Interface

# Visual Studio Code

extension installed that auto imports packages or just classes

type _packageName and it will autocomplete

# Command Line

Compiling

    javac filename.java

Run compiled file

    java filename ( No need to add .class )

Generating JavaDoc

    For whole project

    https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html#CHDJBGFC

    For specific files

    javadoc -d (folder where javadoc will be created) (.java files)


# Language

Comments

// single line

/* block */

/** Java Doc */


Data types

    Primitive (stores the actual value)

        Integers
            byte 1 byte
            short 2 bytes
            int 4 bytes
            long 8 bytes - requires the suffix l or it will be treated as int. so 20000000000l 

                can use underscore to space the digits for readability - 2_000_890 is the same as  2000890

        Floating point numbers
            float 4 bytes - when declaring a float it is required to add f at the end or it will be assumed to be a double. ex float f = 1.2f ( can be upper or lowercase)
            double 8 bytes
        Booleans
            boolean
        Characters
            char 2 bytes - use single quotes ''. Double quotes are for strings

        Autoboxing is the automatic conversion that the Java compiler makes between the primitive types and their corresponding object wrapper classes. For example, converting an int to an Integer, a double to a Double, and so on. If the conversion goes the other way, this is called unboxing.

    Reference (stores the address of the object they refer to)

        Classes
        
        Annotations
        
        Interfaces
        
        Enumerations
        
        Arrays

            Arrays are objects that can contain objects or primitives. They need to be initialised with a size and that size cannot be changed.

                int numbers[] = new int[8] // creates an array of integers with a length of 8

            To create arrays with dynamic size use the Java library/API ArrayList  
Packages

    Similar to namespaces in php
    https://www.w3schools.com/java/java_packages.asp

Classes

    main is the entry point

    methods are written like:

        "visibility" "method" "return value" "name"

        ex: public static void main

