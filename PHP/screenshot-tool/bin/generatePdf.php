<?php

// look into running tasks via the command line so they keep going even if the user closes the window

// exec('php ' . __DIR__ . '/../bin/generatePdf.php > /dev/null 2>&1 &');

/*
Instead of raw exec():

Store job in database

CLI worker polls DB

Browser just inserts job record

That’s how queue systems work.
*/