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
            self.postMessage(time.toString())
        }
    }
}