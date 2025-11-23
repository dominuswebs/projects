<?php

function recursion(int $n) : int {
    echo ("inside function " . $n . "\n");
    // base case - once we hit this recursion ends
    if($n == 0) {
        return $n;
    }

    return $n + recursion($n - 1);
}

    // Calling a function → pushes a frame onto the call stack
    // Returning from a function → pops that frame
    // Recursion builds up stack frames until the base case
    // Then the stack unwinds (frames pop one by one)

    // stack will look like this
    // recursion (0) --> last call first being executed (0 + 1 + 2 + 3 + 4 + 5)
    // recursion (1) 
    // recursion (2)
    // recursion (3)
    // recursion (4)
    // recursion (5) -- first call bottom of the stack
    
echo recursion(5);