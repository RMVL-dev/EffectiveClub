package com.example.effectiveclub.ui.dishes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.effectiveclub.data.categories.Dish
import com.example.effectiveclub.data.categories.Dishes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DishesScreen(
    modifier: Modifier = Modifier,
    viewModel: DishesViewModel,
    category: String
){
    Scaffold (
        topBar = {

        }
            ) {paddingValues ->
        when(viewModel.dishesUiState){
            is DishesUIState.Success -> {

            }
            is DishesUIState.Loading -> {

            }
            is DishesUIState.Error -> {

            }
        }
    }
}

@Composable
fun DishGrid(
    modifier: Modifier = Modifier,
    dishes: Dishes
){

}

@Composable
fun DishCard(
    modifier:Modifier = Modifier,
    item:Dish
){
    Card(
        modifier = modifier
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.imageUrl)
                .crossfade(enable = true)
                .build(),
            contentDescription = item.name
        )
        item.name?.let { Text(text = it) }
    }
}


