<?php

declare(strict_types=1);

function renderView(string $template, array $data = ["page-name"=>"default"]): void
{
    // $data array will be available in the included files 
    include TEMPLATES_DIR . '/header.php';
    include TEMPLATES_DIR . '/' . $template . '.php';
    include TEMPLATES_DIR . '/footer.php';
}