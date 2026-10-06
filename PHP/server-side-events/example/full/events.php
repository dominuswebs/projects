<?php

while (ob_get_level() > 0) {
    ob_end_flush();
}

header('Content-Type: text/event-stream');
header('Cache-Control: no-cache');
header('X-Accel-Buffering: no');

$filename = '/tmp/message.txt';

$lastModified = file_exists($filename)
    ? filemtime($filename)
    : 0;

$lastHeartbeat = time();

while (!connection_aborted()) {

    clearstatcache(true, $filename);

    $newModified = file_exists($filename)
        ? filemtime($filename)
        : 0;

    if ($newModified !== $lastModified) {

        $lastModified = $newModified;

        $contents = file_get_contents($filename);
        $data = json_decode($contents, true);

        if ($data && $data['status'] === 'new') {

            echo "data: " . json_encode($data) . "\n\n";

            flush();

            // Mark it as processed
            $data['status'] = 'deployed';

            file_put_contents(
                $filename,
                json_encode($data),
                LOCK_EX
            );
        }
    }

    // Keep proxies/load balancers from considering the connection idle
    if (time() - $lastHeartbeat >= 15) {
        echo ": heartbeat\n\n";
        flush();

        $lastHeartbeat = time();
    }

    sleep(1);
}