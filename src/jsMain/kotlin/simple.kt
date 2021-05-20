import kotlinx.browser.document
import kotlinx.html.dom.create
import kotlinx.html.js.a
import kotlinx.html.js.p
import org.w3c.dom.MessageEvent
import org.w3c.dom.Worker

fun main() {
    println("Client is running...")
    val root = document.getElementById("root")!!.apply {
        append (document.create.p {
            +"Hello, ${greet()}!"
        })
        append (document.create.a {
            href = "https://github.com/ethanmdavidson/KotlinJSWebWorkerDemo"
            +"Get the source here!"
        })
    }

    val worker = Worker("worker.js")
    worker.onmessage = { m: MessageEvent ->
        println("Client got message: ${m.data}")
        root.append(document.create.p {
            +"Message from worker: ${m.data}"
        })
    }

    println("Client sending message: $TIME_REQUEST")
    worker.postMessage(TIME_REQUEST)
}

fun greet() = "world"