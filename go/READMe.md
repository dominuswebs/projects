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

        dev commmand: go run {file}

            Runs that particular file - useful for dev

        dev command: go run . 

            Only if you have a a go.mod file - will run that module file

    main function - go will run this function when the application starts

        Only one can exist

        The only time we dont need one is when we are creating a library that is supposed to included in a project. 



# variables

    Go infers type 

    We can set the type during variable definition

        var [var name] [var type] = [value]

            var myVal float64 = 10

    Go provides helper functions to transform variable types 

        var myInt = 1000 can be cast to float with float64(myInt)

