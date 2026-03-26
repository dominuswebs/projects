<?php

ini_set('display_errors', '1');
ini_set('display_startup_errors', '1');
error_reporting(E_ALL);

define("APP_ROOT", dirname(__DIR__));

require_once '../vendor/autoload.php';

// use App\Models\Task;

// $task = new Task(["data" => [1,2,3]]);

// print_r($task->execTask());

require_once("../src/Controllers/ScreenshotController.php");