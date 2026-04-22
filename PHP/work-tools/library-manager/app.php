<?php
// rewrite like this?
// [
//     path:{
//         path:[
//             {file},
//             {file}
//         ]
//     }
// ]


function processAssets(string $root): array
{
    // Create a recursive directory iterator
    $directory = new RecursiveDirectoryIterator($root, RecursiveDirectoryIterator::SKIP_DOTS);

    // Flatten the recursive structure into a single loop
    $iterator = new RecursiveIteratorIterator($directory, RecursiveIteratorIterator::SELF_FIRST);

    $assets = [];

    foreach ($iterator as $res) {
        if ($res->isFile()) {

            $assets[] = [
                "filename"=>$res->getFilename(),
                "path"=>$res->getRealPath(),
                "mime-type"=>mime_content_type($res->getRealPath()),
                "subdirectories"=>$iterator->getSubPath()
            ];
        }
    }
    return $assets;
}