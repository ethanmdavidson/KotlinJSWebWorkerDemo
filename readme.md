# Web Workers in KotlinJS

This repo demonstrates how to set up a 
[Web Worker](https://developer.mozilla.org/en-US/docs/Web/API/Web_Workers_API)
in Kotlin/JS. It is a very simple demo that creates a new worker that 
sends back the current time. 

Check out [the live demo!](https://ethanmdavidson.github.io/KotlinJSWebWorkerDemo/)

## How does it work?

By [setting up an additional compilation target](https://kotlinlang.org/docs/mpp-set-up-targets.html)
so that two separate .js files will be generated. The client code (which runs in the 
main execution thread) can then spawn a worker that executes the worker code.

This allows us to share the commonMain code between both client and 
worker, without requiring the worker to have all the client code (and vice versa).
This "separation of concerns" makes the code simpler and helps keep file sizes low.

## Limitations

The `jsBrowserRun` tasks currently don't work because (I think) webpack isn't 
serving the worker code. I think this can be fixed with a little gradle magic
but I haven't got it figured out yet.

Running the built files locally is just a tiny bit more difficult than usual
because you can't just open index.html and have everything work; the worker.js
script is blocked because of security things. The simple workaround is to run
an http server in that dir. For example, with python you can 
```shell
cd build/distributions && python -m http.server
```
and then open your browser to the port it's hosting on (probably localhost:8000)