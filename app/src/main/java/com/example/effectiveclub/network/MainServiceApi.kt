package com.example.effectiveclub.network

import com.example.effectiveclub.data.main.Main
import retrofit2.http.GET
import retrofit2.http.Url

interface MainServiceApi {

    @GET
    suspend fun getMain(@Url url:String):Main
}