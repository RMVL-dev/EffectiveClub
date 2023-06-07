package com.example.effectiveclub.ui.dishes

import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectiveclub.repository.interfaces.DishesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class DishesViewModel(
    val dishesRepository: DishesRepository
):ViewModel() {

    var dishesUiState:DishesUIState by mutableStateOf(DishesUIState.Loading)
        private set

    private val _categoryChip = MutableStateFlow(mutableListOf("Все меню", "С рисом", "Салаты", "С рыбой"))
    val categoryChip:StateFlow<List<String>> = _categoryChip.asStateFlow()

    private val _select = MutableStateFlow(mutableListOf(false,false,false,false))
    val select:StateFlow<List<Boolean>> = _select.asStateFlow()


    init {
        getDishesList()
    }

    fun selectUpdate(item:String){

        for (i in 0 until _select.value.size)
            _select.value[i]=false

        if (_categoryChip.value.contains(item)){
            _select.value[_categoryChip.value.indexOf(item)] = true
        }

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

    fun updateCategoryList(list:List<String>){

        for(item in list){
            if (
                !_categoryChip.value.contains(item)
            ){
                _categoryChip.update {currentList->
                    currentList.add(item) as MutableList<String>
                }
                _select.value.add(false)
            }
        }

    }
}