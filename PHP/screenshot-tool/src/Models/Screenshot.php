<?php

// creates screenshots
// the prefix should be the task id

use HeadlessChromium\BrowserFactory;
use HeadlessChromium\Page;

// $browserFactory = new BrowserFactory();

// // starts headless Chrome
// $browser = $browserFactory->createBrowser(
//     ['windowSize' => [1920, 1080],]
// );

// try {
//     // creates a new page and navigate to an URL
//     $page = $browser->createPage();

//     $urls = [
//         'http://localhost/Work/DIT/DDMB-Framework/P4%20Dev/content/P12_D_4PL1/?tags=[hide_burger_section,mall_condition]',
//         // 'http://localhost/Work/DIT/DDMB-Framework/P4%20Dev/content/P12_D_4PL2/?disableanimation=true&tags=[hide_burger_section,mall_condition]',
//         // 'http://localhost/Work/DIT/DDMB-Framework/P4%20Dev/content/P12_D_4PL3/?disableanimation=true&tags=[hide_burger_section,mall_condition]',
//         // 'http://localhost/Work/DIT/DDMB-Framework/P4%20Dev/content/P12_D_4PL4/?disableanimation=true&tags=[hide_burger_section,mall_condition]'
//     ];

//     for($i = 0; $i < count($urls); $i++) {
//         $page->navigate($urls[$i])->waitForNavigation(Page::NETWORK_IDLE);
//         sleep(3);
//         // using heredoc for readability
//         // we can also pass data 
//         $time = 3;

//         $js = <<<JS
//             const v = document.querySelectorAll('video')[0];
//             v.currentTime = $time;
//             v.pause();
//         JS;
//         // we use page evaluate to run JS before taking a screenshot         
//         $page->evaluate($js)->getReturnValue();  
//         sleep(0.5); 
//         $page->screenshot([
//             'captureBeyondViewport' => true,
//             'clip' => $page->getFullPageClip()
//         ])->saveToFile(__DIR__ . "/screenshots/new{$i}.png");
//     }
// } finally {
//     // bye
//     $browser->close();
// }