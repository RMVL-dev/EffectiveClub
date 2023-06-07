package com.example.effectiveclub.data.main

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Main(
    @SerialName("сategories")
    val categories: List<Categories>
)
