package com.example.effectiveclub.data.main

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Categories(
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name:String?,
    @SerialName("image_url")
    val imageUrl:String?
)
