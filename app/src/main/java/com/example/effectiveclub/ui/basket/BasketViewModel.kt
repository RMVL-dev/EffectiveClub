package com.example.effectiveclub.ui.basket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.effectiveclub.data.basket.Basket
import com.example.effectiveclub.data.categories.Dish
import com.example.effectiveclub.repositories.database.ItemRepository


class BasketViewModel(private val itemsRepository: ItemRepository):ViewModel() {

    suspend fun deleteAllItems(){
        itemsRepository.deleteAll()
    }


    suspend fun saveItem(dish:Dish){
        dish.name?.let {
            dish.weight?.let { it1 ->
                dish.price?.let { it2 ->
                    dish.imageUrl?.let { it3 ->
                        Basket(
                            name = it,
                            price = it2,
                            weight = it1,
                            imageUrl = it3,
                            amount = 1
                        )
                    }
                }
            }
        }?.let {
            itemsRepository.insertItem(
                it
            )
        }

    }



}
