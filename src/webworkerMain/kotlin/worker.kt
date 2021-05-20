import kotlinx.datetime.Clock
import org.w3c.dom.DedicatedWorkerGlobalScope
import org.w3c.dom.MessageEvent

external val self: DedicatedWorkerGlobalScope

fun main() {
    println("Worker is running...")
    self.onmessage = { m: MessageEvent ->
        println("Worker got message: ${m.data}")
        if(m.data == TIME_REQUEST){
            val time = Clock.System.now().epochSeconds
            println("Worker sending message: $time")
            /* pass time.toString() because only certain object types are allowed
                https://developer.mozilla.org/en-US/docs/Web/API/DedicatedWorkerGlobalScope/postMessage
                I think it would be pretty simple to use kotlinx-serialization to pass more
                complex objects, might add that to this demo at some point.
             */
            self.postMessage(time.toString())
        }
    }
}