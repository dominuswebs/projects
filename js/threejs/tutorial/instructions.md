Following the tutorial here - https://threejs.org/manual/#en/installation

Folder structure slightly different

index.html
bundle.js
public
app -> main.js

Using NPM to install three.js

Using esbuild for bundling.

update package.json with 
    "scripts": {
        "build": "esbuild app/main.js --bundle --minify --outfile=bundle.js"
    }

    run it with:

        npm run build

update index.html module include with:

    <script type="module" src="bundle.js"></script>