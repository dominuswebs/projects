<?php 

function countDown(int $start): Generator {
    for($i = $start; $i > 0; $i--) {
        echo "Generating number...\n";
        yield random_int(1,100);
    }
}

// 