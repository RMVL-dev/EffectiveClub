package com.example.effectiveclub.repository.classes

import com.example.effectiveclub.network.MainServiceApi
import com.example.effectiveclub.repository.interfaces.AppContainer
import com.example.effectiveclub.repository.interfaces.AppRepository
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

    private val retrofitService: MainServiceApi by lazy {
        retrofit.create(MainServiceApi::class.java)
    }

    override val appRepository: AppRepository by lazy {
        MainNetworkRepository(retrofitService)
    }
}