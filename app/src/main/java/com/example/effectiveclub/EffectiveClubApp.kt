package com.example.effectiveclub

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.effectiveclub.ui.navigation.EffectiveClubNavigationGraph

@Composable
fun EffectiveClubApp(
    navController: NavHostController = rememberNavController()
){
    EffectiveClubNavigationGraph(navController = navController)
}