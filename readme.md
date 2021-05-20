# Web Workers in KotlinJS

This repo demonstrates how to set up a 
[Web Worker](https://developer.mozilla.org/en-US/docs/Web/API/Web_Workers_API)
in Kotlin/JS. It is a very simple demo that creates a new worker that 
sends back the current time.

## How does it work?

By [setting up an additional compilation target](https://kotlinlang.org/docs/mpp-set-up-targets.html)
so that two separate .js files will be generated. The client code (which runs in the 
main execution thread) can then spawn a worker that executes the worker code.

This allows us to share the commonMain code between both client and 
worker, without requiring the worker to have all the client code (and vice versa).
This "separation of concerns" makes the code simpler and helps keep file sizes low.

