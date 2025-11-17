<?php 

function sum (int $a, int $b ) : int
{
    return $a + $b;
}

// this will throw an error the return is type string but we set the function return type to int
function badSum($a, $b) : int
{
    return "oops";
}

function greeting(string $name, string $message, int $loops = 1) {
    for ($i = 0 ; $i < $loops ; $i++) {
        echo "Hello $name. $message!" . PHP_EOL;
    }
}

function sumAll(int ...$numbers) {
    return array_sum($numbers);
}

// run via command line passing 2 arguments 
// $argv is an array with all the arguments passed via cli
// $argc is the number of elements in $argv
// ex: php functions.php 1 2
if(isset($argv[1]) && isset($argv[2])) {
    echo sum($argv[1], $argv[2]);
}


/* ============ TYPES ==================== */
// this works because PHP coerces the string "2" to the number 2
// echo sum(1, "2");
// if we want it to fail, we need to add this line at the top
// declare(strict_types=1);
// this stops type coercion from happening
// with strict_types true, this will also fail sum(5.0 ,2);
// because 5.0 is a float
// two cant be converted to an integer so typeerror is thrown
// echo sum(1, "two");
// typeerror
// echo badSum(1,2);


/* ========= PARAMETER NAMES ============== */
// without using the parameter name, need to respect order
greeting("John", "Booyaa");
// using the parameter names we can change order
greeting(message:"Cowabunga", name:"Raphael", loops:3);

/* ========= variadic parameters ============= */

echo sumAll(2,3,4,5,6); // prints 20

echo sumAll(); // prints 0

// echo sumAll(1, 4, "s", 5); // TypeError argument #3 needs to be an int
// if we change the sumAll function to not have the type int for its argument
// it will throw a warning and only sum the numbers printing 10 

// here we are passing an existing array as an argument and using ... to pass each value in the array
// as subsequent variadic arguments to that function
$numbers = [2, 3, 4, 5, 6, 7, 8];
echo PHP_EOL . sumAll(...$numbers); // 35

// we can also mix passing a variable number of values plus existing arrays
$moreNumbers = [1, 2, 2];
echo PHP_EOL . sumAll(2, 3, 5, ...$numbers, ...$moreNumbers); // 50


/* ====== anonymous functions / closures / arrow functions ============= */

// can only be assigned to a variable or passed to a function argument

$greet = function ($message) {
    return "Message: $message has been sent.";
};

// passing arrow function as an argument
$squared = array_map(fn($n) => $n * $n, $moreNumbers);

var_dump($squared);

// anonymous functions, but not arraow functions, do not have access to the parent scope

$result = "success";

$status = function ($name) {
    return "Hi $name. Your status is $result.";
};

echo PHP_EOL . $status("Dave");
// throws a warning. Undefined variable $result. It still echo's `Hi Dave. Your status is .`
// it does not have access to parent scope unless you use the "use" keyword and the variable names that you want to use
// named functions also do not have access to parent scope but can use the keyword "global" to reference an external variable

// examples:
// anonymous function. Pass external variable via "use"
// IMPORTANT: value is passed by copy, if the function changes $result value it wont affect the original. 
// Only if we pass via reference `... use (&$result)` will affect the variable value in global scope
$newStatus = function ($name) use ($result) {
    return "Using improved function. Hi $name. Your status is $result.";
};

echo PHP_EOL . $newStatus("Dave");

// named function
// declare $result as global within the function, granting access to the variable in global scope
// IMPORTANT - this explicitly declares that the $result in the function refers to the global scope $result, meaning that any changes made inside the function
// will be reflected outside. Same as accessing a variable like this &$myVar  

function anotherStatus($name) {
    global $result;
    return "Using another improved function. Hi $name. Your status is $result.";
}

echo PHP_EOL . anotherStatus("Bruce");