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

    Container ids are long strings 3e1da2944b28b5b1027d211ac21cebe4fc3ddec34504634b69db237d60252610 but docker is smart enough to choose
    the correct id if we provide a few starting characters so in this case 3e1da would be enough to id that container.

# Docker commands

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


# Useful containers

    Running a webserver ( NGINX )

        docker run -it -p 8888:80 nginx

        detached mode

            docker run -d -p 8888:80 nginx

