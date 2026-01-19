<?php

require_once 'vendor/autoload.php';

use HeadlessChromium\BrowserFactory;
use HeadlessChromium\Page;

$browserFactory = new BrowserFactory();

// starts headless Chrome
$browser = $browserFactory->createBrowser(
    ['windowSize' => [1920, 1080],]
);

try {
    // creates a new page and navigate to an URL
    $page = $browser->createPage();

    $urls = [
        'http://localhost/Work/DIT/DDMB-Framework/P4%20Dev/content/P12_D_4PL1/?disableanimation=true&tags=[hide_burger_section,mall_condition]',
        'http://localhost/Work/DIT/DDMB-Framework/P4%20Dev/content/P12_D_4PL2/?disableanimation=true&tags=[hide_burger_section,mall_condition]',
        'http://localhost/Work/DIT/DDMB-Framework/P4%20Dev/content/P12_D_4PL3/?disableanimation=true&tags=[hide_burger_section,mall_condition]',
        'http://localhost/Work/DIT/DDMB-Framework/P4%20Dev/content/P12_D_4PL4/?disableanimation=true&tags=[hide_burger_section,mall_condition]'
    ];

    for($i = 0; $i < count($urls); $i++) {
        $page->navigate($urls[$i])->waitForNavigation(Page::NETWORK_IDLE);
        // get page title
        $pageTitle = $page->evaluate('document.title')->getReturnValue();
        // screenshot - Say "Cheese"! 😄
        sleep(3);
        $page->screenshot([
            'captureBeyondViewport' => true,
            'clip' => $page->getFullPageClip()
        ])->saveToFile(__DIR__ . "/screenshots/bar{$i}.png");
    }


    // pdf
    // $page->pdf(['printBackground' => false])->saveToFile(__DIR__ .' bar.pdf');
} finally {
    // bye
    $browser->close();
}

// echo __DIR__;