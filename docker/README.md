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

    Docker will not re-launch a container by default ( VM reboot ). Once it stops it will remain stopped. For a web server we want it to start automatically and restart it on failure. For that we need to set the restart policy:

        on-failure[:max-retries]
            - only on error, can't detect all failures - if the main process is still running this will not trigger
        always
            - container should always be restarted unless we stop it manually, then it only starts again on the next launch of Docker Deamon or if we start it manually.
        unless-stopped
            - similar to above but it wont start again even in the next launch of docker deamon

    We use these flags when starting a new container - examples in Docker Commands section - restart policy

    In Docker commands, using an equals sign (=) in a flag (e.g., --key=value) serves as a explicit separator between an argument name (the flag) and its value. 
    It is used to assign values to configurations, labels, environment variables, or build arguments.

        Example:

            docker update --restart unless-stopped [container-id / container-name] is the same as docker update --restart=unless-stopped [container-id / container-name]

# Docker commands

    Create a container

        docker container create -it [image-name]

    Run a container

        docker run -it -p [host-port]:[container-port] [image-name]

        docker run ... is a shorthand for docker container create ... and docker container start ... 

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

    Delete a container automatically

        Use --rm flag when running the container. Will deleted once the container stops.

            docker run -it --rm ubuntu

    Running a container in the background

        A container is only kept alive as long as its main process is running.
        For a container to run on the background it needs a main process.
        This main process needs to keep running or the container will stop.

        docker run -d -p [host-port]:[container-port] [image-name]

    Setting a container restart policy 

        --restart [restart policy]

        New container

            docker run -d --restart unless-stopped [image-name]

        Existing containeer

            docker [container] update --restart unless-stopped [container-id / container-name]

            Updating the restart policy on a stopped container will not start it again. We need to do it manually.
    
    To show logs

        docker logs [container_id or name]

    How to launch programs in containers

        Most images specify a program that will be launched by default

            Example: ubuntu image starts bash
        
        To change default we can specify the command:

            docker container run [options] [image] [command]

        If a container is running, we can launch additional programs

            docker container exec [options] [container_id or name] [command]

            Example, we start an nginx container but we want to install php as well

            First we start the nginx container

                docker run -it --name my-server nginx

            Then in another terminal we run

                docker container exec my-server apt install php

            Then we can go back to the first terminal and php will be installed in that container.

    Commands

        run - docker [container] run ...

            -t: attach a terminal to the container.
            -i: interactive. Accepts user input from terminal.
            -p: map host to container port [host:container]
            --name: set the container name
            -d or --detach: detached mode
            -f: force
            --rm: automatically delete the container once it stops

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

    Info

        To display info about the client and server running on the host

        docker info 


# Useful containers

    Running a webserver ( NGINX )

        docker run -it -p 8888:80 nginx

        detached mode

            docker run -d -p 8888:80 nginx

        In detached mode we can run programs in that container

            docker container exec [options] [container_id or name] [program]

        running bash

            docker container exec -it my_container bash

        installing programs while running bash

            apt update 

            apt install [program]

# Manage Files within a Container

    How are files stored in a container

        All files are stored directly in the container. They are stored in the writable layer of the container.

            For example we spin an ubuntu container and then run apt update / upgrade. The package data is downloaded and is written into the writable layer of the container. 

        This allows the images to be read-only. If you delete the container, all the data is lost but the image can still
        be used to create a new container.

    Copying files into / out of a container

        docker cp

        copy into a container

            docker cp [host-file/folder] [container-name/id]:[container-directory]

                example - updating the index.html in nginx container

                    docker cp host/path/to/index.html [container name/id]:/usr/share/nginx/html/

        copy data from a container into the host

            docker cp [container-name/id]:[container-directory] [host-file/folder]
        
        The docker cp command works even when the container is not running.


    
    Enable the container to use the host's filesystem

        Bind mounts

    Bind mounts vs Copy files

    What -v does



# Common errors

    Permission denied (error 13)

        Your user doesn’t have permission to access the Docker daemon (i.e. to run Docker commands).

            This usually happens because:

                You're trying to run Docker without root privileges

                You're not in the docker group
        
        OR

        Docker cannot access a file or folder on your host system (likely in your bind mount).

        This is often due to file permissions, especially on Linux or WSL2.


    Error response from daemon: cannot start/stop container: xxxxxx: permission denied

        Check if AppArmor is the culprit.

            sudo aa-status

        sudo aa-remove-unknown allows us to stop the containers but the issue will return

        We can also disable/remove the application

            disable

                sudo systemctl disable apparmor.service --now
                sudo service apparmor teardown

            remove

                sudo apt-get purge --auto-remove apparmor

                sudo service docker restart

                docker system prune --all --volumes