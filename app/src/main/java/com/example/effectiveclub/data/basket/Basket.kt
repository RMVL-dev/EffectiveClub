package com.example.effectiveclub.data.basket

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basket")
data class Basket(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val price: Int = 0,
    val weight: Int = 0,
    val imageUrl: String = "",
    val amount: Int = 0
)
