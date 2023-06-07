package com.example.effectiveclub.repository.interfaces

import com.example.effectiveclub.data.main.Main

interface AppRepository {

    suspend fun getMain():Main
}