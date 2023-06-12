package com.example.effectiveclub.ui.dishes


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.effectiveclub.R
import com.example.effectiveclub.data.categories.Dish
import com.example.effectiveclub.data.categories.Dishes
import com.example.effectiveclub.ui.utils.TopBarDishes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DishesScreen(
    modifier: Modifier = Modifier,
    viewModel: DishesViewModel,
    categories: MutableList<String>,
    selected:MutableList<Boolean>,
    navigateToDialog: () -> Unit,
    paddingValues: PaddingValues
){
        when(viewModel.dishesUiState){
            is DishesUIState.Success -> {
                DishesContent(
                    dishesList =
                    (viewModel.dishesUiState as DishesUIState.Success).dishes,
                    paddingValues = paddingValues,
                    categories = categories,
                    selected = selected,
                    viewModel = viewModel,
                    navigateToDialog = {navigateToDialog()}
                )
            }
            is DishesUIState.Loading -> {

            }
            is DishesUIState.Error -> {

            }
        }
}

@Composable
fun DishesContent(
    modifier: Modifier = Modifier,
    dishesList:Dishes? = null,
    paddingValues: PaddingValues,
    categories: MutableList<String>,
    selected: MutableList<Boolean>,
    viewModel: DishesViewModel,
    navigateToDialog:()->Unit
){

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        DishesFilter(
            categories = categories,
            selected = selected,
            viewModel = viewModel
        )
        dishesList?.let {
            DishGrid(
                dishes = it,
                onCardClick = {dish->
                    viewModel.updateCurrentDish(dish = dish)
                    navigateToDialog()
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DishesFilter(
    modifier: Modifier = Modifier,
    categories: MutableList<String>,
    selected: MutableList<Boolean>,
    viewModel: DishesViewModel
){
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        items(categories){item ->
            FilterChip(
                modifier = Modifier.padding(start = 5.dp),
                selected = selected[categories.indexOf(item)],
                onClick = {
                    Log.d("Change","${selected[categories.indexOf(item)]}")
                    viewModel.selectUpdate(item = item)
                    Log.d("Change","${selected[categories.indexOf(item)]}")
                },
                label = {
                    Text(text = item)
                }
            )
        }
    }
}
@Composable
fun DishGrid(
    modifier: Modifier = Modifier,
    dishes: Dishes,
    onCardClick: (Dish) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ){
        items(dishes.dishes){dish ->
            DishCard(
                item = dish,
                onCardClick = onCardClick
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DishCard(
    modifier: Modifier = Modifier,
    item: Dish,
    onCardClick:(Dish) -> Unit
) {
    Card(
        modifier = modifier
            .height(190.dp)
            .width(109.dp),
        onClick = {
            onCardClick(item)
        }
    ) {
        Box(
            modifier = modifier.size(109.dp),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.imageUrl)
                    .crossfade(enable = true)
                    .build(),
                contentDescription = item.name,
                error = painterResource(id = R.drawable.image_break),
                modifier = Modifier.padding(start = 20.dp)
            )
        }
        item.name?.let { Text(
            text = it,
            modifier = Modifier.padding(start = 10.dp)
        ) }
    }
}
