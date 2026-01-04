<?php
/* this session id is stored in a cookie in the browser, PHP handles that automatically */
session_start();

if($_SERVER['REQUEST_METHOD'] == 'POST') {
    setcookie('user', $_POST['name'], time() + 3600, '/');
    header('Location: index.php?submitted=true', true, 303);
    exit();
}

/* 
    cookies

    stored in the browser
    set by JS or server headers
    size limit 4kB
    not for sensitive data
*/
$hasCookie = isset($_COOKIE['user']);

if( !$hasCookie) {
    $welcomeMessage = "Welcome back, user!";
} else {
    $welcomeMessage = "Welcome back, " . htmlspecialchars($_COOKIE['user']) . "!";
}

/*
    sessions

    stored on the server
    identified by cookies
    secure
    store large data
    auto-expiry, 30-60 minutes after no activity
*/

if(!isset($_SESSION['visits'])) {
    $_SESSION['visits'] = 1;
} else {
    $_SESSION['visits']++;
}

$visitsMessage = "This is your " . $_SESSION['visits'] . " visit";
?>
<html>
    <body>
        <?php if(!$hasCookie): ?>
            <form method="POST">
                <label>Name:</label>
                <input type="text" name="name" />
                <button>Track</button>
            </form>
        <?php endif;?>
        <p><?=$welcomeMessage?></p>
        <p><?=$visitsMessage?></p>
    </body>
</html>