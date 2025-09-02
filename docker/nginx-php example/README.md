Docker compose

# To run ( first time)

docker-compose up --build

# To run from existing build

docker-compose up -d

    -d (detached mode)

# use a different yml file

    -f path/to/yml/file

# connect to a running container

    find the container name/id

        docker ps

    attach a shell - in this case bash

        docker exec -it [container name or id] /bin/bash

# To stop

    docker-compose stop

# To stop and clean up

    docker-compose down

# Volumes

    Why Both web (Nginx) and php (PHP-FPM) Need the Volume:

    1. Shared Codebase

    Both services need access to the same application code — usually your PHP files — so they can perform their roles:

    The PHP-FPM container (php) needs the code to interpret and execute PHP files.

    The Nginx container (web) needs the same files to serve static assets (HTML, CSS, JS, images, etc.) and to pass PHP requests to the PHP-FPM service via FastCGI.

    If one of them doesn't have the volume mounted:

    Nginx may fail to find files to serve.

    PHP-FPM may not be able to execute any PHP scripts (leading to 404 or 500 errors).

    How They Work Together

    When a request hits Nginx:

    Nginx serves static files directly from /var/www/html.

    If it encounters a .php file, it proxies the request to PHP-FPM.

    PHP-FPM also reads the same file from /var/www/html, executes the PHP code, and sends the response back to Nginx.

    If the volume isn't mounted in both containers, this process breaks — they won't be "looking at" the same code.

    Additional Mounts (e.g., custom content)

    You also have a custom mount:

    - ~/workspace/designintoto/git/Framework/DDMB-Framework/P4 Dev/assets/library FLG25:/var/www/html/content

    This must be mounted in the Nginx container because it's probably used for static content. If PHP also needs access to it (e.g., reading files or configs), then it should be mounted in both.

# errors

    volumes:

    docker-compose down --volumes --remove-orphans
    docker system prune -a --volumes --force