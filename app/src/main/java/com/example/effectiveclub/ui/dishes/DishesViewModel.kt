package com.example.effectiveclub.ui.dishes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectiveclub.repository.interfaces.DishesRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class DishesViewModel(
    val dishesRepository: DishesRepository
):ViewModel() {

    var dishesUiState:DishesUIState by mutableStateOf(DishesUIState.Loading)
        private set

    init {
        getDishesList()
    }

    fun getDishesList(){
        viewModelScope.launch {
            dishesUiState = try {
                DishesUIState.Success(
                    dishes = dishesRepository.getDishes()
                )
            }catch (e:IOException){
                DishesUIState.Error
            }catch (e:HttpException){
                DishesUIState.Error
            }
        }
    }
}