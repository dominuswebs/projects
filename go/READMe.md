packages are how we split the application, every application needs at least one
multiples files can be part of the same package

similar to js modules where we can import and export package features

# main

    main package and main function are important because go seems them as our application entry point

    main module - (NOT PACKAGE) - a module is a collection of packages - it is basically our application

        command: go mod init {path/app-name}

            generates go.mod file

        command: go build

            generates an OS specific executable of our application

    main function - go will run this function when the application starts

        Only one can exist

        The only time we dont need one is when we are creating a library that is supposed to included in a project. 

