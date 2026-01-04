<?php
    if($_SERVER['REQUEST_METHOD'] == 'POST') {
        $email = filter_var($_POST['email'], FILTER_SANITIZE_EMAIL);

        // After processing, redirect the user so we dont get the resubmit form data if we refresh the browser
        header('Location: index.php?submitted=true', true, 303);
        exit();
    }
?>

<html>
    <body>
        <h1>Please submit the form</h1>
        <!-- if we omit the action property, it's value is the same as action="/" --> 
        <form method="POST">
            <label>Email:</label>
            <input type="email" name="email" />
            <!-- by default a button INSIDE a form will submit it-->
            <button>Submit</button>
        </form>
    </body>
</html>