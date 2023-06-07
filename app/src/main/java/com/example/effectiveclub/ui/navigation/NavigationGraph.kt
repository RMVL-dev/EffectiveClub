package com.example.effectiveclub.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.effectiveclub.ui.AppViewModelProvider
import com.example.effectiveclub.ui.Main.MainScreen
import com.example.effectiveclub.ui.Main.MainViewModel


enum class NavigationGraph (title:String){
    Main(title = "main"),
    Categories(title = "categories"),
}


@Composable
fun EffectiveClubNavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    mainViewModel: MainViewModel = viewModel(
        factory = AppViewModelProvider.Factory
    )
){

    NavHost(
        navController = navController,
        startDestination = NavigationGraph.Main.name,
    ){
        composable(route = NavigationGraph.Main.name){
            MainScreen(viewModel = mainViewModel)
        }
        composable(route = NavigationGraph.Categories.name){

        }
    }
}