package com.example.effectiveclub

import android.app.Application
import com.example.effectiveclub.repository.classes.DefaultAppContainer
import com.example.effectiveclub.repository.interfaces.AppContainer

class EffectiveClubApplication:Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}