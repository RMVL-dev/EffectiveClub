package com.example.effectiveclub.ui.dish

import android.view.WindowManager
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import androidx.compose.ui.window.SecureFlagPolicy
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.effectiveclub.R
import com.example.effectiveclub.data.categories.Dish

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DishItem(
    modifier: Modifier = Modifier,
    dish:Dish,
    navigateUp:()->Unit,
    addToBasket:(dish:Dish)->Unit = {},
){
    Box(modifier = modifier
        .height(450.dp)
        .width(350.dp)
        //.border(2.dp, color = Color.Black)
    ){
        Dialog(
            onDismissRequest = { navigateUp() },
        ) {
            (LocalView.current.parent as DialogWindowProvider)?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Favorite, contentDescription = "")
                    }
                    IconButton(onClick = {navigateUp()}) {
                        Icon(imageVector = Icons.Filled.Close, contentDescription = "")
                    }
                }
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(dish.imageUrl)
                        .crossfade(enable = true).build(),
                    contentDescription = dish.name
                )
                dish.name?.let {
                    Text(
                        text = it,
                        modifier = modifier.padding(start = 8.dp)
                    )
                }
                Row(modifier = modifier) {
                    Text(text = "${dish.price} \u20BD")
                    Text("\u2022")
                    Text(text = "${dish.weight}г")
                }
                dish.description?.let { Text(text = it) }
                Button(
                    onClick = {
                        addToBasket(dish)
                              },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.add_to_basket)
                    )
                }
            }
        }
    }
}



