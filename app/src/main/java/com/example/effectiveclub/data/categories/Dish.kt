package com.example.effectiveclub.data.categories

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dish(
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("price")
    val price: Int?,
    @SerialName("weight")
    val weight: Int?,
    @SerialName("description")
    val description:String?,
    @SerialName("image_url")
    val imageUrl:String? = null,
    @SerialName("tegs")
    val tags: List<String>
)
