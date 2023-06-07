package com.example.effectiveclub.ui.main

import com.example.effectiveclub.data.main.Main

sealed interface MainUIState{
    data class Success(val mainlist:Main):MainUIState

    object Loading:MainUIState

    object Error:MainUIState
}