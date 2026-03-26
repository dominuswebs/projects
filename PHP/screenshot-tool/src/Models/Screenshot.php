<?php

namespace App\Models;

use HeadlessChromium\Browser\ProcessAwareBrowser;
use HeadlessChromium\Page;

/*
    url data

    data = array(
        "configuration" => "page configuration - POD + DAYPART + CHANNEL + VARIATION + STATE + SCREEN - 26P04_4PL_CUPS_BURGER_S2"
        "url" => "screenshot url"
        "js" => "JS to be run on the page"
    )
*/

class Screenshot
{
    public function __construct(
        public ProcessAwareBrowser $browser,
        public array $urlData
    ){}

    public function takeScreenshot(): void
    {

        $page = $this->browser->createPage();

        $page->navigate($this->urlData["url"])->waitForNavigation(Page::NETWORK_IDLE);
        
        sleep(3);
        
        $page->screenshot([
            'captureBeyondViewport' => true,
            'clip' => $page->getFullPageClip()
        ])->saveToFile(APP_ROOT . "/screenshots/" . $this->urlData["configuration"] . ".png");
    }
}