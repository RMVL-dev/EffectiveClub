package com.example.effectiveclub

import android.app.Application
import com.example.effectiveclub.repositories.appcontainer.DefaultAppContainer
import com.example.effectiveclub.repositories.appcontainer.AppContainer

class EffectiveClubApplication:Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(context = this)
    }
}