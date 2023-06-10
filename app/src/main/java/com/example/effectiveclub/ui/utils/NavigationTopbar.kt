package com.example.effectiveclub.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.effectiveclub.R
import com.example.effectiveclub.ui.dishes.DishesFilter
import com.example.effectiveclub.ui.dishes.DishesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier:Modifier = Modifier,
    category:String,
    navigateUp:()-> Unit,
    isShowing:Boolean
){
    if (isShowing) {
        TopAppBar(
            modifier = modifier,
            title = {
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = category,
                        modifier = Modifier
                            .weight(8f)
                            .padding(start = 20.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = "",
                        modifier = Modifier
                            .size(44.dp)
                            .weight(1f)
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = ""
                    )
                }
            }
        )
    }
}


@Composable
fun TopBarDishes(
    modifier: Modifier = Modifier,
    categories: MutableList<String>,
    selected: MutableList<Boolean>,
    viewModel: DishesViewModel,
    category: String,
    navigateUp:()->Unit
){
    Column(modifier = modifier) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primary),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = ""
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = category,
                modifier = Modifier
                    .weight(4f)
                    .padding(start = 20.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.user),
                contentDescription = "",
                modifier = Modifier
                    .size(44.dp)
                    .weight(1f)
            )
        }
        DishesFilter(categories = categories, selected = selected, viewModel = viewModel)
    }
}