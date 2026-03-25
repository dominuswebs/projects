<?php

// creates pdfs
// the prefix should be the task id used in the screenshot task
// maybe give the ability to create PDF's from existing screenshots

use Mpdf\Mpdf;

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