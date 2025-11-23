<?php

// array functions

// count($arr) - how many elements

// sort($arr) - sorts the array elements in place, modifying the original array - ascending order

// rsort($arr) - sorts the array elements in place, modifying the original array - descending order

// asort($arr) - Associative arrays. Sorts by value, modifying the original array 

// ksort($arr) - Associative arrays. Sorts by key, modifying the original array

// range($tart, $end) - creates and array from an int, float or string (char)

// $letters = range("a", "f");

// var_dump($letters);

// array_map($callback, $array) - $callback runs for every element of $array, 

// $numbers = range(1,5,3); // min max step so the resulting array is [1,4]
// var_dump($numbers);
// $numbersSquared = array_map(fn($n) => $n ** 2, $numbers);
// var_dump($numbersSquared);

// array_filter($arr, $callback) - creates a new array of the values where the callback returns true
// $numbers = range(1,10);
// $evenNumbers = array_filter(
//     $numbers,
//     fn($n) => $n % 2 == 0
// );
// var_dump($evenNumbers);

// array_reduce($arr, fn, 0) - reduce the array to a single value. Can be sum of values, concatenating strings, and so on
// $numbers = [1, 2, 45, 67, 3];
// $sum = array_reduce(
//     $numbers,
//     fn($carry, $n) => $carry + $n,
//     0 // this is the initial $carry
// );
// var_dump($sum);

// array unpacking
$numbers = [2,4,5];
$moreNumbers = [0, ...$numbers, 6];

// array destructuring - assigning values from an array to variables
$fruits = ["apple", "orange", "watermelon"];
[$first,,$third] = $fruits; // we can skip elements in the array
var_dump($first, $third);