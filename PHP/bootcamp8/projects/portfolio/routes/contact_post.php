<?php
// CSRF

$name = $_POST['name'] ?? '';
$email = $_POST['email'] ?? '';
$message = $_POST['message'] ?? '';

if(empty($name) || empty($email) || empty($message))
{
    badRequest("All fields are necessary");
}

if(!filter_var($email, FILTER_VALIDATE_EMAIL)) 
{
    badRequest("Invalid email.");
}

var_dump($email, $name, $message);
die;