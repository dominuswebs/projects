<?php

/*  
password_hash returns the algorithm, cost and salt as part of the returned hash. 
Therefore, all information that's needed to verify the hash is included in it.
This allows the verify function to verify the hash without needing separate storage for the salt or algorithm information.
We can store just the hashed value in the database.

password_verify verifies that the given hash matches the given password.

*/

/* password hash benchmark 

Class PasswordCost
{
    private float $timeTarget = 0.350;
    private int $cost = 11;

    public function costBenchmark() {
        $cost = $this->cost;
        do {
            $cost++;
            $start = microtime(true);
            password_hash("test", PASSWORD_BCRYPT, ["cost" => $cost]);
            $end = microtime(true);
        } while (($end - $start) < $this->timeTarget);

        echo "Appropriate Cost Found: " . $cost - 1;
    }

}

$bench = new PasswordCost();

$bench->costBenchmark();

*/

$options = [
    "cost" => 12
];

$hashed = password_hash("test-password", PASSWORD_DEFAULT, $options);

if (password_verify('test-password', $hashed)) {
    echo 'Password is valid!';
} else {
    echo 'Invalid password.';
}
