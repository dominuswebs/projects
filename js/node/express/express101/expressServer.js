// express is a 3rd party module
// 1. run npm init
// 2. since we are using es module we need to update the type to `module`
import express from 'express';
// return value from createApplication inside express module 
const app = express();

// all is a method, and it takes 2 args
// 1. route
// 2. callback to run if the route is requested
app.all('/{*splat}', (req, res) => {
    // since we are using express 5 we need to use /{*splat} - this handles all routes

    // Express handles the basic headers
    res.send(`<h1>This is the homepage</h1>`);
    // Express handles the end (closing the connection)

});
// 1. port
// 2. callback (optional)
app.listen(3000);