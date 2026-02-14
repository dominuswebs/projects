<?php
ini_set('max_execution_time', 300);

// update php.ini and set it to 300 seconds

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

$mpdf = new Mpdf([ // set page orientation to landscape (default is portrait)
    'orientation' => 'L'
]);

// testing multiple paghe orientations in the same doc
// $mpdf->WriteHTML('<h1>Screen1</h1><img src="' . __DIR__ . "/screenshots/bar1.png" . '"/>');
// $mpdf->AddPage('P'); // set this page to portrait
// $mpdf->WriteHTML('$<h1>Screen2</h1><img src="' . __DIR__ . "/screenshots/bar1.png" . '"/>');
// $mpdf->AddPage('L'); // set this page to landscape
// $mpdf->WriteHTML('$<h1>Screen2</h1><img src="' . __DIR__ . "/screenshots/bar2.png" . '"/>');

$configuration = "4PL Lunch Burgers";


// for the pdf the images require __DIR__ because it is loading via the filesystem
// the browser does not have access to the filesystem so if we want to preview the result BEFORE generating the PDF
// we need to use relative paths

$imagesForPDF = [
    __DIR__ . "/screenshots/bar0.png",
    __DIR__ . "/screenshots/bar1.png",
    __DIR__ . "/screenshots/bar2.png",
    __DIR__ . "/screenshots/bar3.png"
];

$imagesForBrowser = [
    "screenshots/bar0.png",
    "screenshots/bar1.png",
    "screenshots/bar2.png",
    "screenshots/bar3.png"
];

// $html = renderTemplate($configuration, $imagesForBrowser);
// echo $html;

// mpdf doesnt do flex or grid
$html = renderTemplate($configuration, $imagesForPDF);
$mpdf->WriteHTML($html);

// Output the PDF to the browser for inline viewing or force a download
// 'I' sends the file inline to the browser (default)
// 'D' sends to the browser and forces a file download
$mpdf->Output('my_document.pdf', \Mpdf\Output\Destination::INLINE);

function renderTemplate(string $configuration, array $images = []): string
{
    ob_start();
    require __DIR__ . "/templates/combined.php";
    return ob_get_clean();
}