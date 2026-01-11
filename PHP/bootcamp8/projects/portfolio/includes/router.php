<?php

declare(strict_types=1);

const ALLOWED_METHODS = ['GET', 'POST'];
const INDEX_URI = '';
const INDEX_ROUTE = 'index';

function normaliseUri(string $uri): string {
    $uri = strtolower(trim($uri, '/'));
    
    return $uri == INDEX_URI ? INDEX_ROUTE : $uri;
}

function notFound(): void {
    http_response_code(404);
    echo "404 Not Found";
    // this stops the script execution
    exit;
}

function getFilePath(string $uri, string $method): string {
    return ROUTES_DIR . '/' . normaliseUri($uri) . '_' . strtolower($method) . '.php';
}

function dispatch(string $uri, string $method): void {
    
    // 1) normalise the URI: GET /guestbook -> routes/guestbook_get.php
    $uri = normaliseUri($uri);
    $method = strtoupper($method);
    // 2) !GET|POST - return 404
    if(!in_array($method, ALLOWED_METHODS)) {
        notFound();
    }

    // 3) file path - PHP file path
    // 3.5) handle the route by including the PHP file 
    $filePath = getFilePath($uri, $method);

    if( file_exists($filePath)) {
        include($filePath);
        return;
    }

    // 4) !File -> 404
    notFound();
}