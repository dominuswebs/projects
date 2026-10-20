To run:

from root

    docker compose up -d --build

check if composer is running

    docker exec -it php85 composer --version

Install composer dependencies - using phpdotenv as example

    docker exec -it php85 composer require vlucas/phpdotenv

Managing dependencies

    There are two common ways to manage dependencies:

    Install after starting the container: Run composer require ... or composer install using docker exec. Convenient while developing.

    Install during image building: Run composer install in your Dockerfile. This is generally more reproducible for deployment and production environments.

    For your current learning and development setup, installing dependencies inside the running container is perfectly reasonable.

    One thing to remember: If you recreate the container, your dependencies will remain as long as they are stored in ./src/vendor, because that directory is bind-mounted from your host. If you later change your Dockerfile, rebuild with docker compose up -d --build.

Managing containers

    With our Docker Compose setup:

        Stop both containers: docker compose stop

        Start both containers: docker compose start

        Stop only Nginx: docker compose stop nginx

        Stop only PHP: docker compose stop php

        Stop and remove both containers: docker compose down

        Running docker compose stop from your project directory stops both services defined in our YAML file.