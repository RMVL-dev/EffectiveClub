package com.example.effectiveclub.repository.interfaces

import com.example.effectiveclub.data.categories.Dishes
import com.example.effectiveclub.data.main.Main

interface MainRepository {

    suspend fun getMain():Main

}