<?php

use Classes\Route;
use Classes\Test\Test;

Route::set('about-us', function () {
    echo "about us";
});

echo Test::$test;
