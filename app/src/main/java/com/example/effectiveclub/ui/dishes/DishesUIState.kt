package com.example.effectiveclub.ui.dishes

import com.example.effectiveclub.data.categories.Dishes

sealed interface DishesUIState{

    data class Success(val dishes: Dishes):DishesUIState

    object Loading:DishesUIState

    object Error:DishesUIState
}