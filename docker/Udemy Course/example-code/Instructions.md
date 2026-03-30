# For development purposes

    Using bind mount - run the command from example-code folder

        docker run -it -p 3000:80 -v "$(pwd)/Gym":/usr/share/nginx/html:ro nginx

# For production purposes

    Using cp

    start the container
        
        docker run -it -p 3000:80 nginx

    get the container ID

        docker container ls

    copy the files - 

        docker cp ./Gym/. 84d1ca8fd345:/usr/share/nginx/html

    If we want to debug with bash just run exec in another terminal window - or start the container in detached mode -d

        docker container exec -it 84d1ca8fd345 bash

# Remote - Transfer files to server and deploy in Docker

    1) Copy the files to the server

        rsync/scp -r [source] [destination] or use cyberduck app

    2) Copy the files from the server to the container