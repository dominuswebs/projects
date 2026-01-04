<?php

/*
    Traits allow classes to use methods without extending (subclasses) or implementing (interfaces)

    requires keyword "use" inside the class. "use" is used in importing namespaces (including classes, functions, and constants), i
    ncluding traits within a class (this case), and inheriting variables in closures (anonymous functions). 
*/

/*
    We can also use interfaces that match the trait for the following reasons:
    
    there can be advantages, but it depends on why you’re doing it. An interface that “matches” a trait is a fairly common intentional pattern in PHP when used correctly.

    Traits provide implementation. Interfaces provide contracts.
    Using both together gives you compile-time guarantees + code reuse.

    What “matching” usually means

    Typically this means:

    An interface declares the methods

    A trait implements those same methods

    A class:

    implements Interface

    use Trait

    interface LoggerInterface
    {
        public function log(string $message): void;
    }

    trait LoggerTrait
    {
        public function log(string $message): void
        {
            echo $message;
        }
    }

    class FileLogger implements LoggerInterface
    {
        use LoggerTrait;
    }

    Advantages of this approach
    1. Enforces a contract (traits alone cannot)

    Traits do not enforce anything:

    class Foo {
        use LoggerTrait;
    }


    Nothing guarantees Foo should be a logger.

    With an interface:

    function writeLog(LoggerInterface $logger) { ... }


    Now PHP enforces:

    The method exists

    The signature is correct

    ✅ This is the biggest advantage

    2. Works with Dependency Injection

    Traits cannot be type-hinted:

    // ❌ impossible
    function foo(LoggerTrait $logger) {}


    Interfaces can:

    function foo(LoggerInterface $logger) {}


    Using an interface lets you:

    Swap implementations

    Mock in tests

    Use containers cleanly

    3. Allows multiple implementations

    You can reuse the same interface but change the implementation strategy:

    class FileLogger implements LoggerInterface {
        use LoggerTrait;
    }

    class DbLogger implements LoggerInterface {
        use LoggerTrait;
    }

    class CustomLogger implements LoggerInterface {
        public function log(string $message): void {
            // totally different logic
        }
    }


    The interface stays stable; the implementation can vary.
*/

interface Logger
{
    public function log(string $message): void;
}

trait Loggable
{
    public function log(string $message): void {
        echo "Logging Message: {$message}";
    }
}

class User implements Logger
{
    // we imnplement the interface to ensure that Loggable has to exist
    // is we comment it out it explodes
    // we dont have to use it but it needs to be defined
    use Loggable;

    public function __construct(public string $name){}

    public function save(): void {
        $this->log("User {$this->name} saved");
    }
}


$user = new User("Bob");
$user->save();