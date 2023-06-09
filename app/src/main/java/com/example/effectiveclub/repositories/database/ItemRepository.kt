package com.example.effectiveclub.repositories.database

import com.example.effectiveclub.data.basket.Basket
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    fun getAllItemsStream(): Flow<List<Basket>>

    fun getItemStream(id:Int):Flow<Basket>


    suspend fun insertItem(item:Basket)

    suspend fun deleteItem(item:Basket)

    suspend fun updateItem(item: Basket)
}