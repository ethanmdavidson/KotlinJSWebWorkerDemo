import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.w3c.dom.DedicatedWorkerGlobalScope
import org.w3c.dom.MessageEvent

external val self: DedicatedWorkerGlobalScope

fun main() {
    println("Worker is running...")
    self.onmessage = { m: MessageEvent ->
        val assignment:Assignment = Json.decodeFromString(m.data as String)
        println("Worker got message: $assignment")
        val time = Clock.System.now()
        val hour = time.toLocalDateTime(TimeZone.currentSystemDefault()).hour
        val completedWork = CompletedWork(time.epochSeconds,
            "$hour ${assignment.suffix}")
        println("Worker sending message: $completedWork")

        self.postMessage(Json.encodeToString(completedWork))
    }
}