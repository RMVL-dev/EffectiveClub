package com.example.effectiveclub.repositories.network.interfaces

import com.example.effectiveclub.data.categories.Dishes

interface DishesRepository {
    suspend fun getDishes():Dishes
}