<?php

require 'vendor/autoload.php';

// trying without var config = 
// $json = file_get_contents('config-no-var.js');

// trying with var
$config = file_get_contents('config.js');
// strip all characters until {
$json = substr($config, strpos($config, "{"));
// decode
$data = json5_decode($json, true);
// convert back to json to minimize it
// no flag defaults to single file
// use JSON_PRETTY_PRINT to preview it
// $newJson = json_encode($data);

echo "<pre>";
print_r($data);
echo "</pre>";