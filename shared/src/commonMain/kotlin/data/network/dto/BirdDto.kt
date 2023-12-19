package data.network.dto

import domain.model.Bird
import kotlinx.serialization.Serializable

@Serializable
data class BirdDto(
    val category : String,
    val path : String,
    val author : String,
)

fun BirdDto.toBird() = Bird(
    type = this.category,
    path = this.path,
    photographer = this.author,
)
