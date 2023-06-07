package com.example.effectiveclub.repository.interfaces

import com.example.effectiveclub.data.categories.Dishes

interface DishesRepository {
    suspend fun getDishes():Dishes
}