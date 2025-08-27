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