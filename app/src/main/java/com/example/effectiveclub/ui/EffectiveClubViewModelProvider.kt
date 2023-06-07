package com.example.effectiveclub.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.effectiveclub.EffectiveClubApplication
import com.example.effectiveclub.ui.Main.MainViewModel

object AppViewModelProvider{
    val Factory = viewModelFactory{
        initializer {
            MainViewModel(mainNetworkRepository = effectiveClubApplication().container.appRepository)
        }
    }
}

fun CreationExtras.effectiveClubApplication(): EffectiveClubApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as EffectiveClubApplication)