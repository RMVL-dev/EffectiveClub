package com.example.effectiveclub.network

import com.example.effectiveclub.data.categories.Dishes
import retrofit2.http.GET
import retrofit2.http.Url

interface DishesServiceApi {
    @GET
    suspend fun getDishes(@Url url:String): Dishes
}