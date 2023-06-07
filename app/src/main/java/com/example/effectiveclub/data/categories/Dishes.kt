package com.example.effectiveclub.data.categories

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dishes(
    @SerialName("dishes")
    val dishes: List<Dish>
)
