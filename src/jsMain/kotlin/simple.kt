import kotlinx.browser.document
import kotlinx.html.dom.create
import kotlinx.html.js.a
import kotlinx.html.js.p
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
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
        val completedWork:CompletedWork = Json.decodeFromString(m.data as String)
        println("Client got message: $completedWork")
        root.append(document.create.p {
            +"Message from worker: ${completedWork.timeSaying}"
        })
    }

    val assignment = Assignment("o'clock")
    println("Client sending message: $assignment")
    worker.postMessage(Json.encodeToString(assignment))
}

fun greet() = "world"