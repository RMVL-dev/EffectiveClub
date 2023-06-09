package com.example.effectiveclub.data.basket

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basket")
data class Basket(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val price: Int,
    val weight: Int,
    val imageUrl: String,
    val amount: Int
)
