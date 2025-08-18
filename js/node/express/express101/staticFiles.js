import express from 'express';
const app = express();

// app comes with a use method
// use takes 1 arg
// 1. the middleware you want to run

// this tells our app that any request to a static file can be found in public folder
// http://localhost:3000/home.html will display the home.html and the actual image
// but if you go to http://localhost it says "Cannot GET /"
app.use(express.static('public'));

app.listen(3000);