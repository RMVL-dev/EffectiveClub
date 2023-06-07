package com.example.effectiveclub.repository.classes

import com.example.effectiveclub.network.DishesServiceApi
import com.example.effectiveclub.network.MainServiceApi
import com.example.effectiveclub.repository.interfaces.AppContainer
import com.example.effectiveclub.repository.interfaces.DishesRepository
import com.example.effectiveclub.repository.interfaces.MainRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class DefaultAppContainer:AppContainer {

    private val BASE_URl = "https://run.mocky.io/v3/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URl)
        .addConverterFactory(Json{ignoreUnknownKeys = true}.asConverterFactory("json/application".toMediaType()))
        .build()

    private val retrofitMainService: MainServiceApi by lazy {
        retrofit.create(MainServiceApi::class.java)
    }
    private val retrofitDishesServiceApi: DishesServiceApi by lazy {
        retrofit.create(DishesServiceApi::class.java)
    }

    override val mainRepository: MainRepository by lazy {
        MainNetworkRepository(retrofitMainService = retrofitMainService)
    }
    override val dishesRepository: DishesRepository by lazy {
        DishesNetworkRepository(retrofitDishesService = retrofitDishesServiceApi)
    }
}