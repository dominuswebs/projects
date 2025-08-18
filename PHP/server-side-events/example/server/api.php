<?php

// this is so we don't need to change php.ini regarding output_buffer

// first clear buffer
ob_end_flush();
// Set file mime type event-stream
header('Content-Type: text/event-stream');
header('X-Accel-Buffering: no'); // allows unbuffered responses
header('Cache-Control: no-cache');

// Loop until the client close the stream
while (true) {
    // Echo time
    $time = date('r');
    echo "data: The server time is: {$time}\n\n";
    // Flush buffer (force sending data to client)
    flush();
    // Wait 2 seconds for the next message / event
    sleep(2);

    if (connection_aborted()) {
        exit();
    }
}
