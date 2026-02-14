<?php
ini_set('max_execution_time', 300);


require_once 'vendor/autoload.php';

use HeadlessChromium\BrowserFactory;
use HeadlessChromium\Page;
use Mpdf\Mpdf;

// $browserFactory = new BrowserFactory();

// // starts headless Chrome
// $browser = $browserFactory->createBrowser(
//     ['windowSize' => [1920, 1080],]
// );

// try {
//     // creates a new page and navigate to an URL
//     $page = $browser->createPage();

//     $urls = [
//         'http://localhost/Work/DIT/DDMB-Framework/P4%20Dev/content/P12_D_4PL1/?disableanimation=true&tags=[hide_burger_section,mall_condition]',
//         'http://localhost/Work/DIT/DDMB-Framework/P4%20Dev/content/P12_D_4PL2/?disableanimation=true&tags=[hide_burger_section,mall_condition]',
//         'http://localhost/Work/DIT/DDMB-Framework/P4%20Dev/content/P12_D_4PL3/?disableanimation=true&tags=[hide_burger_section,mall_condition]',
//         'http://localhost/Work/DIT/DDMB-Framework/P4%20Dev/content/P12_D_4PL4/?disableanimation=true&tags=[hide_burger_section,mall_condition]'
//     ];

//     for($i = 0; $i < count($urls); $i++) {
//         $page->navigate($urls[$i])->waitForNavigation(Page::NETWORK_IDLE);
//         // get page title
//         $pageTitle = $page->evaluate('document.title')->getReturnValue();
//         // screenshot - Say "Cheese"! 😄
//         sleep(3);
//         $page->screenshot([
//             'captureBeyondViewport' => true,
//             'clip' => $page->getFullPageClip()
//         ])->saveToFile(__DIR__ . "/screenshots/bar{$i}.png");
//     }
// } finally {
//     // bye
//     $browser->close();
// }

$mpdf = new Mpdf([ // set page orientation to landscape (default is portrait)
    'orientation' => 'L'
]);

$mpdf->WriteHTML('<h1>Screen1</h1><img src="' . __DIR__ . "/screenshots/bar1.png" . '"/>');
$mpdf->AddPage('P'); // set this page to portrait
$mpdf->WriteHTML('$<h1>Screen2</h1><img src="' . __DIR__ . "/screenshots/bar1.png" . '"/>');
$mpdf->AddPage('L'); // set this page to landscape
$mpdf->WriteHTML('$<h1>Screen2</h1><img src="' . __DIR__ . "/screenshots/bar2.png" . '"/>');

// Output the PDF to the browser for inline viewing or force a download
// 'I' sends the file inline to the browser (default)
// 'D' sends to the browser and forces a file download
$mpdf->Output('my_document.pdf', \Mpdf\Output\Destination::INLINE);