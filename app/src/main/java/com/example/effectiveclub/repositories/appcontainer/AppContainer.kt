package com.example.effectiveclub.repositories.appcontainer

import com.example.effectiveclub.repositories.database.ItemRepository
import com.example.effectiveclub.repositories.network.interfaces.DishesRepository
import com.example.effectiveclub.repositories.network.interfaces.MainRepository

interface AppContainer {
    val mainRepository: MainRepository
    val dishesRepository: DishesRepository
    val basketRepository: ItemRepository
}