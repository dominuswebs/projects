<?php

ini_set('display_errors', '1');
ini_set('display_startup_errors', '1');
error_reporting(E_ALL);
/**
 * Load config
*/

require_once '../config/config.php';
/**
 * Autoloader
*/
require_once '../vendor/autoload.php';

// Routes
require_once '../routes/web.php';
require_once '../app/Router.php';

echo "Index file";
