<?php

    class Person {
        
        
        // // before php 8 
        // public string $name;
        // public int $age;

        // public function __construct(string $name, int $age) {
        //     $this->name = $name;
        //     $this->age = $age;
        // }

        // php 8 - using property promotion
        // we declare and initialize class properties directly in the constructor's parameter list
        // this does exactly the same as the above code
        public function __construct(public string $name, public int $age) {
        }

        public function introduce(): string {
            return "Hi my name is {$this->name} and I'm {$this->age} years old.";
        }
    }

    $person = new Person("Alice", 23);

    echo $person->introduce() . "\n";


    class Employee extends Person {
        public function __construct(
            public string $name, 
            public int $age,
            public string $position) {
        }

        // override introduce method. We use parent::methodName to re use part of the message. 
        public function introduce(): string {
            return parent::introduce() . " I work as {$this->position}.";
        }
    }

    $emp = new Employee("Bob", 54, "Manager");
    echo $emp->introduce() . "\n";

    // Polimorphism 

    /*

        interface Talkative {
            public function speak();
        }

        class Dog extends Animal implements Talkative {
            public function speak() {
                return "Woof, woof!";
            }
        }

        Any animal or human (or alien) that implements the Talkative interface can be used in a context where talkative beings are needed:

        protected function makeItSpeak(Talkative $being) {
            echo $being->speak();
        }

        This is a properly used polymorphic method. You don't care what you're dealing with as long as it can speak().
    
    */

    // since employee is a subclass of person it can be passed on to any parameter of type Person. This is another example of polymorphism
    
    $people = [
        new Person("Anna", 34),
        new Employee("Jack", 56, "CEO")
    ];

    function introduction(Person $person) {
        echo $person->introduce() . "\n";
    }

    foreach ($people as $individual) {
        introduction($individual);
    }

    // static keyword and singelton pattern

    class Connection {
        // only accessible from inside the class
        private static $instance = null;
        // cannot create Connection objects with new keyword
        // this will fail
        // $conn = new Connection();
        private function __construct() {
        }
        // by using the class name we lose the ability to extend the class. 
        // We can replace Connection:: with static:: so all subclasses also work
        // this is called late static binding https://www.php.net/manual/en/language.oop5.late-static-bindings.php
        public static function singleton() {
            
            // using the class name
            // if (Connection::$instance === null) { // only create an new instance if none exists. We want to reuse it.
            //     Connection::$instance = new Connection(); // call the constructor from inside the class
            // }
            // return Connection::$instance;
            
            // using late static binding
            if (static::$instance === null) { // only create an new instance if none exists. We want to reuse it.
                static::$instance = new Connection(); // call the constructor from inside the class
            }
            return static::$instance;
        }
    }

    $connection = Connection::singleton();