import path from 'path';
import express from 'express';
import { fileURLToPath } from 'url';

// this is required because __dirname and __filename don’t exist by default in es modules.
const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const app = express();

// check the staticFiles.js 
app.use(express.static('public'));

// app object has a few methods
// 1. get
// 2. post
// 3. delete
// 4. put
// 5. all - i accept any method
// They take 2 args
// 1. path
// 2. callback to run if an HTTP request that matches this VERB 
// is made to the path in #1

// app.all('/{*splat}', (req, res) => { //'/{*splat}' matches / and everything else like /aa/ss/ or /ss and so on
//     console.log(req);
//     res.send(`<h1>This is a catch all route</h1>`);
// });

// root only so localhost:3000. If we try localhost:3000/aa - we get an error Cannot GET /aa
app.get('/', (req, res) => {
    // console.log(req);
    // res.send(`<h1>This is a GET request</h1>`);
    // for now we will serve home.html using .sendFile, in the future we will use .render
    // it requires path
    // we can set the root as the public folder to avoid having to write the full path like this:
    // res.sendFile(path.join(__dirname, '/public/home.html'));
    // so we do this
    res.sendFile('/home.html', { root: path.join(__dirname, 'public') });
});

// here we handle /aa
app.get('/aa', (req, res) => {
    res.send(`<h1>This is a GET request for AA</h1>`);
});

// curl -X POST http://localhost:3000
app.post('/{*splat}', (req, res) => {
    res.send(`<h1>This is a POST request</h1>`);
});

// curl -X DELETE http://localhost:3000
app.delete('/{*splat}', (req, res) => {
    res.send(`<h1>This is a DELETE request</h1>`);
});

// curl -X PUT http://localhost:3000
app.put('/{*splat}', (req, res) => {
    res.send(`<h1>This is a PUT request</h1>`);
});

app.listen(3000);