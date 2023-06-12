package com.example.effectiveclub.ui.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.effectiveclub.ui.utils.NavigationRailClub
import com.example.effectiveclub.ui.AppViewModelProvider
import com.example.effectiveclub.ui.basket.Basket
import com.example.effectiveclub.ui.basket.BasketViewModel
import com.example.effectiveclub.ui.dish.DishItem
import com.example.effectiveclub.ui.dishes.DishesScreen
import com.example.effectiveclub.ui.dishes.DishesViewModel
import com.example.effectiveclub.ui.main.MainScreen
import com.example.effectiveclub.ui.main.MainViewModel
import com.example.effectiveclub.ui.utils.TopBar
import kotlinx.coroutines.launch


enum class NavigationGraph (title:String){
    Main(title = "main"),
    Categories(title = "categories"),
    DialogDish(title = "dialogDish"),
    Basket(title = "basket")
}


@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EffectiveClubNavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    mainViewModel: MainViewModel = viewModel(
        factory = AppViewModelProvider.Factory
    ),
    dishesViewModel:DishesViewModel = viewModel(
        factory = AppViewModelProvider.Factory
    ),
    basketViewModel: BasketViewModel = viewModel(
        factory = AppViewModelProvider.Factory
    )
){
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
                category = mainViewModel.topBarString,
                isShowing = !mainViewModel.mainIsShowing,
                navigateUp = {
                    mainViewModel.changePage()
                    navController.navigateUp()
                }
            )
        },
        bottomBar = {
            NavigationRailClub(
                navigateToMain = {
                    if(!mainViewModel.mainIsShowing) {
                        mainViewModel.changePage()
                    }
                    navController.navigate(route = NavigationGraph.Main.name)
                },
                navigateToBasket = {
                    if(!mainViewModel.mainIsShowing) {
                        mainViewModel.changePage()
                    }
                    navController.navigate(route = NavigationGraph.Basket.name)
                }
            )
        }
    ) {paddingValues->
        val categories by dishesViewModel.categoryChip.collectAsState()
        val selected by dishesViewModel.select.collectAsState()
        val dish by dishesViewModel.currentDish.collectAsState()
        val coroutineScope = rememberCoroutineScope()
        val basketUiState by basketViewModel.basketList.collectAsState()
        NavHost(
            navController = navController,
            startDestination = NavigationGraph.Main.name,
        ) {
            composable(route = NavigationGraph.Main.name) {
                MainScreen(
                    viewModel = mainViewModel,
                    navigationToCategories = {
                        mainViewModel.changePage()
                        navController.navigate(
                            route = NavigationGraph.Categories.name
                        )
                    },
                    paddingValues = paddingValues,
                    changeNameTopBar = {name ->
                        mainViewModel.changeTopBar(name = name)
                    }
                )
            }
            composable(route = NavigationGraph.Categories.name) {
                DishesScreen(
                    viewModel = dishesViewModel,
                    categories = categories as MutableList<String>,
                    selected = selected as MutableList<Boolean>,
                    navigateToDialog = {
                        mainViewModel.changePage()
                        navController.navigate(route = NavigationGraph.DialogDish.name)
                    },
                    paddingValues = paddingValues
                )
            }
            composable(route = NavigationGraph.DialogDish.name) {
                DishItem(
                    dish = dish,
                    navigateUp = {
                        mainViewModel.changePage()
                        navController.navigateUp()
                    },
                    addToBasket = { dish ->
                        coroutineScope.launch {
                            basketViewModel.saveItem(dish = dish)
                        }
                    }
                )
            }
            composable(route = NavigationGraph.Basket.name){
                Basket(
                    basket = basketUiState.list,
                    paddingValues = paddingValues,
                    increment = { item ->
                        basketViewModel.increment(item.id)
                    },
                    decrement = {item ->
                        basketViewModel.decrement(item.id)
                    },
                    deleteAll = { basketViewModel.deleteAllItems()}
                )
            }
        }
    }
}