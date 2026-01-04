<?php

// interfaces define a contract that guarantees that classes implementing those interfaces implement certain methods
// interfaces only define the methods, they do not implement them

/*
abstract class is a blueprint for other classes that cannot be instantiated on its own. 
It serves as a base class, allowing you to define a common structure and share functionality (methods and properties) among related child classes, 
while forcing those child classes to implement specific methods. 

Key Characteristics

    Cannot be instantiated: You cannot create an object from an abstract class directly using the new keyword.
    Can contain both abstract and concrete methods:
    Abstract methods are declared with the abstract keyword and have no body (implementation).
    Concrete (non-abstract) methods have full implementations and can be inherited and used by subclasses.
    Must be extended: An abstract class is designed to be inherited using the extends keyword.
    Child classes must implement abstract methods: Any non-abstract class that extends an abstract class must provide an implementation for all of its inherited abstract methods, or it must also be declared abstract.
    Can have properties and a constructor: Unlike interfaces, abstract classes can contain member variables and constructors to initialize them.
    Single inheritance: A PHP class can only extend one abstract class. 
*/

/*

composition vs DI

Composition

class Logger
{
    public function log(string $message): void
    {
        echo "LOG: $message\n";
    }
}

class UserService
{
    private Logger $logger;

    public function __construct()
    {
        // Composition: dependency is created internally
        $this->logger = new Logger();
    }

    public function createUser(string $name): void
    {
        $this->logger->log("User created: $name");
    }
}

characteristics:

Tight coupling

UserService cannot easily use a different logger

Harder to test (you can’t swap Logger)

DI 

class Logger
{
    public function log(string $message): void
    {
        echo "LOG: $message\n";
    }
}

class UserService
{
    private Logger $logger;

    // Dependency Injection via constructor
    public function __construct(Logger $logger)
    {
        $this->logger = $logger;
    }

    public function createUser(string $name): void
    {
        $this->logger->log("User created: $name");
    }
}

$logger = new Logger();
$userService = new UserService($logger);

$userService->createUser("Hugo");

characteristics:

Loose coupling

Easy to swap implementations

Much easier to test (mock logger)

Key Difference (one-liner)

Composition = “I build what I need”
Dependency Injection = “You give me what I need”

Using Interfaces in DI

It is still Dependency Injection, and in fact that’s the preferred / “proper” form of DI.

Short answer:

DI is about how the dependency is supplied, not what type it is.

Whether the constructor takes a class or an interface, it’s still DI.

Why it’s still Dependency Injection

Dependency Injection means:

The object does not create its own dependencies — they are provided from the outside.

An interface changes coupling, not injection.

class UserService
{
    private LoggerInterface $logger;

    public function __construct(LoggerInterface $logger)
    {
        // Dependency is injected
        $this->logger = $logger;
    }
}


✔ Dependency is external
✔ Dependency is injected via constructor
✔ Still DI

Why using an interface is BETTER

Using a concrete class:

public function __construct(Logger $logger)


Couples you to one implementation

Using an interface:

public function __construct(LoggerInterface $logger)


Depends on a contract, not an implementation

Allows multiple implementations

Follows SOLID → Dependency Inversion Principle


*/

interface PaymentProcessor 
{
    public function processPayment(float $amount): bool;
    public function refundPayment(float $amount): bool;
}

abstract class OnlinePaymentProcessor implements PaymentProcessor 
{
    public function __construct(
        protected string $apiKey
    ){}
    // classes that extend this class need to implement this method
    abstract protected function validateApi(): bool;
    abstract protected function executePayment(float $amount): bool;
    abstract protected function executeRefund(float $amount): bool;

    public function processPayment(float $amount): bool {
        // we can use the method here because $this will refer to class that extends this class
        if(!$this->validateApi()) {
            throw new \Exception('Invalid API key');
        }
        return $this->executePayment($amount);
    }
    
    public function refundPayment(float $amount): bool {
        if(!$this->validateApi()) {
            throw new \Exception('Invalid API key');
        }
        return $this->executeRefund($amount);
    }
}

class StripeProcessor extends OnlinePaymentProcessor 
{
    protected function validateApi(): bool {
        return strpos($this->apiKey, 'sk_') === 0;
    }

    protected function executePayment(float $amount): bool {
        return $amount > 0;
    }

    protected function executeRefund(float $amount): bool {
        return $amount > 0;
    }
}

class PaypalProcessor extends OnlinePaymentProcessor 
{
    protected function validateApi(): bool {
        return strlen($this->apiKey) === 32;
    }

    protected function executePayment(float $amount): bool {
        return $amount > 0;
    }

    protected function executeRefund(float $amount): bool {
        return $amount > 0;
    }
}


// in this case we don't need the API key since we are dealing with physical money,
// so we implement the interface
class CashProcessor implements PaymentProcessor 
{
    public function processPayment(float $amount): bool {
        echo "Cash payment...";
        return true;
    }
    
    public function refundPayment(float $amount): bool {
        echo "Cash refund...";
        return true;
    }
}

class OrderProcessor
{
    // Dependency Injection
    public function __construct(private PaymentProcessor $paymentProcessor) {}

    public function processOrder(float $amount): void {
        if($this->paymentProcessor->processPayment($amount)) {
            echo "Order processed successfully\n";
        } else {
            echo "Order processing failed\n";
        }
    }

    public function refundOrder(float $amount): void {
        if($this->paymentProcessor->refundPayment($amount)) {
            echo "Refund processed successfully\n";
        } else {
            echo "Refund processing failed\n";
        }
    }
}

$stripeProcessor = new StripeProcessor("sk_test_123456");
$paypalProcessor = new PaypalProcessor("valid_paypal_api_key_32charslong");
$cashProcessor = new CashProcessor();

$stripeOrder = new OrderProcessor($stripeProcessor);
$paypalOrder = new OrderProcessor($paypalProcessor);
$cashOrder = new OrderProcessor($cashProcessor);

$stripeOrder->processOrder(100.00);
$paypalOrder->processOrder(150.00);
$cashOrder->processOrder(10.00);

$stripeOrder->refundOrder(25.00);
$paypalOrder->refundOrder(50.00);
$cashOrder->refundOrder(1.00);