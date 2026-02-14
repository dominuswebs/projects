<!DOCTYPE html>
<html>
<head>
    <style>
        body { 
            font-family: sans-serif; 
        }

        .landscape {
            height: 1080px;
            width: 1920px;
        }

        .portrait {
            width: 1080px;
            height: 1920px;
        }

        .title { 
            font-size: 18px; font-weight: bold; 
        }

        .screens {
            width: 100%;
        }

        img {
            display: inline-block;
            width: 240px;
        }
    </style>
</head>
<body class="landscape">
<div class="title"><?= htmlspecialchars($configuration) ?></div>
<div class="screens">
<?php foreach ($images as $image): ?>
    <img src="<?= $image  ?>"/>
<?php endforeach; ?>
</div>
</body>
</html>