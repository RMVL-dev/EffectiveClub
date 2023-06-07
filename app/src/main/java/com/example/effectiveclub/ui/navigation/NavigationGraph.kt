package com.example.effectiveclub.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.effectiveclub.ui.AppViewModelProvider
import com.example.effectiveclub.ui.dishes.DishesScreen
import com.example.effectiveclub.ui.dishes.DishesViewModel
import com.example.effectiveclub.ui.main.MainScreen
import com.example.effectiveclub.ui.main.MainViewModel


enum class NavigationGraph (title:String){
    Main(title = "main"),
    Categories(title = "categories"),
}


@Composable
fun EffectiveClubNavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    mainViewModel: MainViewModel = viewModel(
        factory = AppViewModelProvider.Factory
    ),
    dishesViewModel:DishesViewModel = viewModel(
        factory = AppViewModelProvider.Factory
    )
){
    val categories by dishesViewModel.categoryChip.collectAsState()
    val selected by dishesViewModel.select.collectAsState()
    NavHost(
        navController = navController,
        startDestination = NavigationGraph.Main.name,
    ){
        composable(route = NavigationGraph.Main.name){
            MainScreen(
                viewModel = mainViewModel,
                navigationToCategories = {
                    navController.navigate(
                        route = NavigationGraph.Categories.name
                    )
                }
            )
        }
        composable(route = NavigationGraph.Categories.name){
            DishesScreen(
                viewModel = dishesViewModel,
                category = "Азиатская кухня",
                categories = categories as MutableList<String>,
                selected = selected as MutableList<Boolean>,
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
    }
}