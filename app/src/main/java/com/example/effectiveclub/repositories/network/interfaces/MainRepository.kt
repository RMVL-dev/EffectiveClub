package com.example.effectiveclub.repositories.network.interfaces

import com.example.effectiveclub.data.main.Main

interface MainRepository {

    suspend fun getMain():Main

}