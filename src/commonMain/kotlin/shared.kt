import kotlinx.serialization.Serializable

//This source is shared between client and worker

@Serializable
data class Assignment (
    val suffix: String
)

@Serializable
data class CompletedWork (
    val epochTime: Long,
    val timeSaying: String,
)