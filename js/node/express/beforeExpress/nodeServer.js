import http from 'http';
import fs from 'fs';
// the http module has a createServer method
// takes 1 arg
// 1. callback, callback has 2 args: req, res

const server = http.createServer((req, res) => {

    if(req.url === '/') {
        // console.log(req);
        // res = our way to responding to the requester
        // http message
        // 1. start-line - CHECK
        // 2. header 
        // 3. body

        // writeHead takes 2 args
        // 1. status code
        // 2. object for the mime-type
        res.writeHead(200, {'content-type':'text/html'});
        // body
        // instead of passing html directly using .write()
        // res.write('<h1>home page</h1>');
        // we will load a file 
        const homePageHTML = fs.readFileSync('home.html');
        // and write it
        res.write(homePageHTML);
        // tell the browser that it can close the connection
        res.end();
    } else if (req.url === '/logo.png') {
        // this is now becoming unmaintainable
        res.writeHead(200, {'content-type':'image/png'});
        const image = fs.readFileSync('logo.png');
        res.write(image);
        res.end();
    } else {
        res.writeHead(404, {'content-type':'text/html'});
        res.write(`<h2>Page ${req.url} not found</h2>`);
        res.end();
    }
});

// create server returns an object with a listen method
// listen takes 1 arg
// 1. port to listen for http traffic on

server.listen(4000);