package com.example.effectiveclub.repositories.database

import com.example.effectiveclub.data.basket.Basket
import com.example.effectiveclub.data.basket.ItemDAO
import kotlinx.coroutines.flow.Flow

class OfflineItemsRepository(private val itemDAO: ItemDAO): ItemRepository {
    override suspend fun deleteAll() = itemDAO.deleteAll()

    override fun getAllItemsStream(): Flow<List<Basket>>  = itemDAO.getAllBasket()

    override fun getItemStream(id: Int): Flow<Basket> = itemDAO.getItem(id = id)

    override suspend fun insertItem(item: Basket)  = itemDAO.insert(item)

    override suspend fun deleteItem(item: Basket) = itemDAO.delete(item)

    override suspend fun updateItem(item: Basket) = itemDAO.update(item)
}