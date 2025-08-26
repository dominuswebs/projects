# Running Docker

    To keep things simple in Windows and Mac OS, install docker desktop. 

    Docker desktop NEEDS to be running (docker daemon ) if we want to run docker commands in the terminal.

    The reason is docker runs in Linux - not on the Mac. Docker Desktop is the interface between the VM that runs docker and the Mac. 
    It also does a lot of magic with networking that is otherwise difficult to do.

# About Docker 

    Docker containers are designed to be isolated from each other and the host environment.

    Inside their isolated environment, containers can open any port.

    By default, these internal ports are not connected to any ports on the host.

    This makes them inaccessible from your computer/network.

    To make them accessible we need to publish the port ( -p or --publish )

    It maps a port from our host machine to a port in the container:

        docker run -it -p [host-port]:[container-port] [image-name]

    Container ids are long strings 3e1da2944b28b5b1027d211ac21cebe4fc3ddec34504634b69db237d60252610 but docker is smart enough to choose the correct id if we provide a few starting characters so in this case 3e1da would be enough to id that container.

    All the container settings need to be set during the creation of the container, either by using the create or run commands.

# Docker commands

    Create a container

        docker container create -it [image-name]

    Run a container

        docker run -it -p [host-port]:[container-port] [image-name]

    List running containers

        docker ps

    Stop a container

        docker stop [CONTAINER_ID or NAME]

    Start an existing container 

        docker start [CONTAINER_ID or NAME]

    Delete a container

        A container needs be stopped first before we can remove it.

        docker rm [CONTAINER_ID or NAME]

        To delete multiple we can use command substitution - $()

            docker rm $(docker container ls -aq)

    Running a container in the background

        A container is only kept alive as long as its main process is running.
        For a container to run on the background it needs a main process.
        This main process needs to keep running or the container will stop.

        docker run -d -p [host-port]:[container-port] [image-name]
    
    To show logs

        docker logs [container_id or name]

    Commands

        run - docker [container] run ...

            -t: attach a terminal to the container.
            -i: interactive. Accepts user input from terminal.
            -p: map host to container port [host:container]
            --name: set the container name
            -d or --detach: detached mode
            -f: force

        ls - docker container ls ...

            -a: lists all containers. Not just active ones.
            -s: shows container size 
            -q: (quiet) shows ids only

        stats - docker container stats

        inspect - docker [container] inspect [name]

        rename - docker container rename [current_name] [new_name]

    Volumes

        Mapping 

            [path/in/host/machine] : [path/inside/container]

            The colon (:) in Docker volume mappings defines a bind mount — it tells Docker to mount a file or directory from your host into the container.

            example:

                ./default.conf:/etc/nginx/conf.d/default.conf

                Does:

                Mount the local file default.conf into the container at /etc/nginx/conf.d/default.conf, overwriting whatever’s there.


# Useful containers

    Running a webserver ( NGINX )

        docker run -it -p 8888:80 nginx

        detached mode

            docker run -d -p 8888:80 nginx


# Common errors

    Permission denied (error 13)

        Your user doesn’t have permission to access the Docker daemon (i.e. to run Docker commands).

            This usually happens because:

                You're trying to run Docker without root privileges

                You're not in the docker group
        
        OR

        Docker cannot access a file or folder on your host system (likely in your bind mount).

        This is often due to file permissions, especially on Linux or WSL2.
