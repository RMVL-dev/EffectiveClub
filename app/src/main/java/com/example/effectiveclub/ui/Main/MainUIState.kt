package com.example.effectiveclub.ui.Main

import com.example.effectiveclub.data.main.Main

sealed interface MainUIState{
    data class Success(val mainlist:Main):MainUIState

    object Loading:MainUIState

    object Error:MainUIState
}