package com.example.effectiveclub.ui.basket

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.effectiveclub.R
import com.example.effectiveclub.data.basket.Basket
import com.example.effectiveclub.ui.main.TopMainBar

@Composable
fun Basket(
    modifier: Modifier = Modifier,
    basket: List<Basket>,
    paddingValues: PaddingValues,
    increment: (item:Basket) -> Unit,
    decrement: (item:Basket) -> Unit,
    deleteAll: () -> Unit
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        TopMainBar()
        Box(modifier = modifier
            .fillMaxWidth()
            .weight(8f)) {
            LazyColumn {
                items(basket) { item ->
                    BasketItemCard(
                        item = item,
                        increment = { increment(item) },
                        decrement = { decrement(item) }
                    )
                }
            }
        }
        Button(
            modifier = modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(16.dp),
            onClick = { deleteAll() },
            shape = MaterialTheme.shapes.extraSmall
        ) {
            Text(text = stringResource(id = R.string.buy))
        }
    }
}

@Composable
fun BasketItemCard(
    modifier: Modifier = Modifier,
    item:Basket,
    increment: () -> Unit,
    decrement: () -> Unit
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            //horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(item.imageUrl)
                    .crossfade(enable = true)
                    .build(),
                contentDescription = "",
                modifier = Modifier.size(64.dp).weight(1f)
            )
            Column(
                modifier = Modifier.weight(2f)
            ) {
                Text(text = item.name)
                Row(
                    modifier = modifier
                ) {
                    Text(text = item.price.toString() + "₽")
                    Text("\u2022", modifier = modifier.padding(start = 1.dp, end = 1.dp))
                    Text(text = item.weight.toString()+"г")
                }
            }

            CounterDishes(
                increment = { increment() },
                decrement = { decrement() },
                amount = item.amount
            )
        }
    }
}

@Composable
fun CounterDishes(
    modifier: Modifier = Modifier,
    increment:()->Unit,
    decrement:()->Unit,
    amount:Int
){
    Surface(
        modifier = modifier
            .height(40.dp)
            .width(100.dp),
        shape = MaterialTheme.shapes.small,
        color = Color.Gray
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(onClick = { increment()}) {
                Icon(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = "",
                    modifier = modifier.size(15.dp)
                )
            }
            Text(
                text = amount.toString(),
                fontSize = 20.sp
            )
            IconButton(onClick = { decrement()}) {
                Icon(
                    painter = painterResource(id = R.drawable.minus),
                    contentDescription = "",
                    modifier = modifier.size(15.dp)
                )
            }
        }
    }
}

val basketItem = Basket(
    id = 0,
    name = "Pasta",
    price = 700,
    weight = 300,
    imageUrl = "",
    amount = 3
)


@Preview(showBackground = true)
@Composable
fun PreviewBasketItem(){
    //CounterDishes(
    //    increment = { /*TODO*/ },
    //    decrement = { /*TODO*/ },
    //    amount = 3
    //)

    BasketItemCard(
        item = basketItem,
        increment = {},
        decrement = {}
    )
}