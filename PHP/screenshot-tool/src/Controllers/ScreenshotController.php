<?php

namespace App\Controllers;

// creates screenshots
// the prefix should be the task id

use HeadlessChromium\BrowserFactory;
use App\Models\Screenshot;


$browserFactory = new BrowserFactory();

// starts headless Chrome

$browser = $browserFactory->createBrowser(
    ['windowSize' => [1920, 1080],]
);


try {
    $data = [
        "configuration" => "P4_LUNCH_4PL_S2",
        "url" => "https://staging.ddmb.au/P4%20Dev/content/26P4/4PL/P4_D_4PL2/"
    ];

    $screenshot = new Screenshot($browser, $data);
    
    $screenshot->takeScreenshot();

} finally {
    // bye
    $browser->close();
}