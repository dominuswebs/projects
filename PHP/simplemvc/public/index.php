<?php

spl_autoload_register(function ($className) {
    // this allows us to use namespaces
    $className = str_replace("\\", DIRECTORY_SEPARATOR, $className);
    include_once '../' . $className . '.php';
});

require_once("../lib/Routes.php");

