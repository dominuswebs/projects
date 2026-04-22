<?php

ini_set('display_errors', '1');
ini_set('display_startup_errors', '1');
error_reporting(E_ALL);

require_once "config.php";
require_once "AssetDB.php";
require_once "app.php";



$assets = processAssets(DEV_ASSET_ROOT);


$db = new AssetDB();

$db->writeDB($assets);

var_dump($db->readDB());
