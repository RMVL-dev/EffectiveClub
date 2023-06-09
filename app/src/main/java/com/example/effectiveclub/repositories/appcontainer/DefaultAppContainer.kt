package com.example.effectiveclub.repositories.appcontainer

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.example.effectiveclub.data.database.BasketDataBase
import com.example.effectiveclub.network.DishesServiceApi
import com.example.effectiveclub.network.MainServiceApi
import com.example.effectiveclub.repositories.database.ItemRepository
import com.example.effectiveclub.repositories.database.OfflineItemsRepository
import com.example.effectiveclub.repositories.network.classes.DishesNetworkRepository
import com.example.effectiveclub.repositories.network.classes.MainNetworkRepository
import com.example.effectiveclub.repositories.network.interfaces.DishesRepository
import com.example.effectiveclub.repositories.network.interfaces.MainRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class DefaultAppContainer(private val context: Context): AppContainer {

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
    override val basketRepository: ItemRepository by lazy{
        OfflineItemsRepository(BasketDataBase.getDataBase(context = context).itemDao())
    }
}