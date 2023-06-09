package com.example.effectiveclub.repositories.network.classes

import com.example.effectiveclub.data.categories.Dishes
import com.example.effectiveclub.network.DishesServiceApi
import com.example.effectiveclub.repositories.network.interfaces.DishesRepository

class DishesNetworkRepository(
    private val retrofitDishesService:DishesServiceApi
): DishesRepository {
    override suspend fun getDishes(): Dishes = retrofitDishesService.getDishes(url = "c7a508f2-a904-498a-8539-09d96785446e")
}