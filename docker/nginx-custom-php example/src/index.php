<?php

require 'vendor/autoload.php';

$dotenv = Dotenv\Dotenv::createImmutable(__DIR__);
$dotenv->load();

echo "here<br>";

echo "Reading value from docker compose yaml file " . getenv("APP_ENV") . "<br>";

echo "reading value from .env file " . $_ENV['FROM_ENV_FILE'] . "<br>";

