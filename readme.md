# Web Workers in KotlinJS

This repo demonstrates how to set up a 
[Web Worker](https://developer.mozilla.org/en-US/docs/Web/API/Web_Workers_API)
in Kotlin/JS. It works by 
[setting up an additional compilation target](https://kotlinlang.org/docs/mpp-set-up-targets.html)
so that two separate .js files will be generated. The client code (which runs in the 
main execution thread) can then spawn a worker that executes the worker code.



