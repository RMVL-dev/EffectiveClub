package com.example.effectiveclub.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectiveclub.repositories.network.interfaces.MainRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainViewModel(
    val mainNetworkRepository: MainRepository
    ):ViewModel() {

    var mainUiState:MainUIState by mutableStateOf(MainUIState.Loading)
        private set

    var mainIsShowing:Boolean by mutableStateOf(true)
        private set

    var topBarString:String by mutableStateOf("")
        private set

    fun changeTopBar(name: String){
        topBarString = name
    }



    fun changePage(){
        mainIsShowing = !mainIsShowing
    }
    init {
        getMainList()
    }

    fun getMainList(){
        viewModelScope.launch {
            mainUiState = try{
                MainUIState.Success(
                    mainNetworkRepository.getMain()
                )
            }catch (e:IOException){
                MainUIState.Error
            }catch (e:HttpException){
                MainUIState.Error
            }
        }
    }

}