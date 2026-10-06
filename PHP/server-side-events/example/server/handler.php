<?php

$filename = '/tmp/message.txt';

$message = $_POST['message'] ?? null;

file_put_contents(
    $filename,
    $message,
    LOCK_EX
);