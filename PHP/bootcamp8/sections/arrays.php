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

// // array unpacking
// $numbers = [2,4,5];
// $moreNumbers = [0, ...$numbers, 6];

// // array destructuring - assigning values from an array to variables
// $fruits = ["apple", "orange", "watermelon"];
// [$first,,$third] = $fruits; // we can skip elements in the array
// var_dump($first, $third);

$set1 = [1, 2, 3, 4, 5];
$set2 = [3, 4, 5, 6, 7, 2];

// var_dump(
//     array_intersect($set1, $set2), // returns an array based on set1 with all the matches with set2. keeps all the keys
//     array_intersect($set2, $set1), // returns an array based on set2 with all the matches with set1. keeps all the keys
//     array_diff($set1, $set2), // returns the values in set1 not present in set2
//     array_diff($set2, $set1) // returns the values in set2 not present in set1
// );

$res = array_intersect($set2, $set1);

// var_dump($res);

// for($i = 0; $i < count($res); $i++) {
//     echo "$i - $res[$i]\n";
// }


// these are usually used with associative arrays
// $keys = array_keys($res); // get all keys
// $values = array_values($res); // get all values

// var_dump($keys, $values);

var_dump(
    array_merge($set1, $set2), // because they are indexed arrays the values of set2 are just added to the end of set1. a new array is returnded
    array_merge(["a"=>"a", "b"=>"b"], ["b"=>"c"]), // in associative arrays keys are unique, so the value in the second array for key "b" will be used
    $set1 + $set2, // the + here is an union operator, only elements in the second with different keys than the first will be merged. this is true for both indexed and associative arrays 
    ["a"=>"a", "b"=>"b"] + ["b"=>"c", "c" => "d"], // returns ["a"=>"a", "b"=>"b", "c" => "d"]
    array_unique(array_merge($set1, $set2)), // removes all duplicate values
    array_search(2, $set2) // returns the first key with value 2
);