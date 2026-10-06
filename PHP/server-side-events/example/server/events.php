<?php

// this is so we don't need to change php.ini regarding output_buffer

// first clear buffer
while (ob_get_level() > 0) {
    ob_end_flush();
}
// Set file mime type event-stream
header('Content-Type: text/event-stream');
header('X-Accel-Buffering: no'); // allows unbuffered responses
header('Cache-Control: no-cache');


$filename = '/tmp/message.txt';
$lastModified = file_exists($filename)
    ? filemtime($filename)
    : 0;

// Loop until the client close the stream
while (!connection_aborted()) {
    clearstatcache(true, $filename);

    $newModified = file_exists($filename)
        ? filemtime($filename)
        : 0;

    if ($newModified !== $lastModified) {
        $lastModified = $newModified;

        $message = file_get_contents($filename);

        echo "data: " . json_encode($message) . "\n\n";

        if (ob_get_level()) {
            ob_flush();
        }

        flush();
    }

    sleep(1);
}