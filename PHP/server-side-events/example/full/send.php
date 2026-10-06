<?php

$filename = '/tmp/message.txt';

$message = $_POST['message'] ?? '';

if ($message !== '') {

    $data = [
        'status' => 'new',
        'message' => $message,
        'timestamp' => time()
    ];

    file_put_contents(
        $filename,
        json_encode($data),
        LOCK_EX
    );
}

?>

<form id="messageForm">
    <input type="text" name="message">
    <button type="submit">Send</button>
</form>

<script>
document.getElementById('messageForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    const formData = new FormData(this);

    const response = await fetch('send.php', {
        method: 'POST',
        body: formData
    });

    console.log(await response.text());
});
</script>