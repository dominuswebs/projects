<html>
<head>
<style>
    div.results {
        display: grid;
          grid-template-columns: repeat(4, 200px); 
    /* Sets the spacing (gutters) between your grid items */
    gap: 16px; 
    }

    div.c {
        text-align: center;
    }

    div.h {
        font-weight: bolder;
    }
</style>
</head>
<body>
<?php

error_reporting(E_ALL & ~E_WARNING & ~E_NOTICE & ~E_DEPRECATED);

const CONFIGURATIONS = [
    "drinks-cans",
    "drinks-cups" ,
    "prep-hal",
    "badge-hns",
    "category-mall",
    "category-sbx", 
    "category-airport",
    "kwench",
    "kwench_national",
    "freeze-prx",
    "freeze-pxm",
    "freeze-xrm",
    // add any current tags here
    "bendigo_lunch",
    "no_sauce"
];

const MAPPING = [
    "drinks-cans" => "Cans",
    "drinks-cups" => "Cups",
    "prep-hal" => "Halal",
    "badge-hns" => "Collins",
    "category-mall" => "Mall",
    "category-sbx" => "Small-Box", 
    "category-airport" => "Airport",
    "kwench" => "Kwench-Test",
    "kwench_national" => "Kwench-National",
    "freeze-prx" => "Freeze-Pepsi-Raspberry",
    "freeze-pxm" => "Freeze-Pepsi-Mountain-Dew",
    "freeze-xrm" => "Freeze-Raspberry-Mountain-Dew",
    // add any current tags here
    "bendigo_lunch" => "Bendigo-Test",
    "no_sauce" => "Hobart"
];

$csvFilename = "2026P10.csv";

$csv = fopen($csvFilename,"r");

$results = [];

function filterTags(string $tag) {
    return in_array(strtolower($tag), CONFIGURATIONS);
}

function storeConfigAlreadyExists(array $configTags, string $channel, string $code, string $name) {

    global $results;

    foreach($results as $index => $result) {
        if(array_diff($result["tags"],$configTags) === array_diff($configTags,$result["tags"]) && $result["channel"] == $channel) {
            $results[$index]["count"] += 1;
            return;
        }
    }

    $results[] = array(
        "tags"=>$configTags,
        "count"=>1,
        "channel"=>$channel,
        "name"=>$name,
        "code"=>$code
    );
}

// get the headers

$headers = fgetcsv($csv);

$codeCol = array_search('Store Code', $headers);
$nameCol = array_search('Store Name', $headers);
$screenCol = array_search('ISM', $headers);
$tagsCol = array_search('Tags', $headers);

while (($line = fgetcsv($csv)) !== FALSE) {
    
    $tags = explode(" ", $line[$tagsCol]);
    // filter tags
    
    $validTags = [];
    
    foreach($tags as $tag) {
        if(filterTags($tag)) {
            $validTags[] = $tag;
        }
    }

    // check if configuration already exists
    storeConfigAlreadyExists(configTags: $validTags, name: $line[$nameCol], code: $line[$codeCol], channel: $line[$screenCol] );
}

fclose($csv);

$export = [];

echo "<div class='results'>";
echo "<div class='c h'>Channel</div><div class='h'>Variations</div><div class='c h'>Store Count</div><div class='h'>Example Store</div>";

foreach($results as $result) {

    if($result["tags"] != []) {
        $count = $result["count"];

        $tags = "";

        foreach($result["tags"] as $tag) {
            $tags .= MAPPING[strtolower($tag)] . " ";
        }

        echo "<div class='c'>" . $result['channel'] . "</div><div>$tags</div><div class='c'>" . $result['count'] . "</div><div class=''>" . $result["code"] . " - " . $result["name"] . "</div>";
    
        $export[] = array($result['channel'], trim($tags), $result['count'], $result["code"] . " - " . $result["name"]);
    
    }
}

echo "</table>";

$file = fopen('export.csv', 'w');

fputcsv($file, ["ISM", "Variations", "Store Count", "Example Store"]);
foreach ($export as $row) {
    fputcsv($file, $row);
}
fclose($file);


?>
</body>
</html>