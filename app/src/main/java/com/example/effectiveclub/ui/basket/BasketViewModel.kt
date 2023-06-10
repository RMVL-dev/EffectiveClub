package com.example.effectiveclub.ui.basket

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectiveclub.data.basket.Basket
import com.example.effectiveclub.data.categories.Dish
import com.example.effectiveclub.repositories.database.ItemRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class BasketViewModel(private val itemsRepository: ItemRepository):ViewModel() {

    fun deleteAllItems(){
        viewModelScope.launch {
            itemsRepository.deleteAll()
        }
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

    val basketList: StateFlow<BasketUiState> = itemsRepository.getAllItemsStream()
        .map {BasketUiState(it)}
        .stateIn(
            scope =viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = BasketUiState()
        )

    fun increment(id: Int){
        val currentItem: StateFlow<Basket> = itemsRepository.getItemStream(id = id)
            .map { Basket(
                id = it.id,
                name = it.name,
                price = it.price,
                weight = it.weight,
                imageUrl = it.imageUrl,
                amount = it.amount
            ) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = Basket()
            )
        viewModelScope.launch {
            Log.d("DataBase","${currentItem.value.id} ${currentItem.value.amount} $id")
            itemsRepository.updateItem(currentItem.value.copy(amount = currentItem.value.amount+5))
            Log.d("DataBase","${currentItem.value.id} ${currentItem.value.amount} ${currentItem.value.copy(amount = currentItem.value.amount+5)} $id")
        }
    }

    fun decrement(id: Int){
        val currentItem: StateFlow<Basket> = itemsRepository.getItemStream(id = id)
            .map { Basket(
                id = it.id,
                name = it.name,
                price = it.price,
                weight = it.weight,
                imageUrl = it.imageUrl,
                amount = it.amount
            )  }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = Basket()
            )
        viewModelScope.launch {
            if (currentItem.value.amount>0) {
                itemsRepository.updateItem(currentItem.value.copy(amount = currentItem.value.amount - 1))
            }
        }
    }
}

data class BasketUiState(val list:List<Basket> = listOf())