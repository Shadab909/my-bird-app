package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Bird(
    val type : String,
    val path : String,
    val photographer : String,
)
