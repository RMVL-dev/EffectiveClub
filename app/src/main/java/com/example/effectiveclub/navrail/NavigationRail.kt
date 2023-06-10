package com.example.effectiveclub.navrail

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.effectiveclub.R

@Composable
fun NavigationRailClub(
    modifier: Modifier = Modifier,
    navigateToMain:()->Unit,
    navigateToBasket:()->Unit
){
    BottomAppBar(modifier = modifier) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 5.dp, end = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NavigationRailItem(
                imageRes = R.drawable.home,
                text = R.string.main,
                navigateRoute = {navigateToMain()}
            )
            NavigationRailItem(
                imageRes = R.drawable.search,
                text = R.string.search,
                navigateRoute = {}
            )
            NavigationRailItem(
                imageRes = R.drawable.shopping_basket,
                text = R.string.basket,
                navigateRoute = {navigateToBasket()}
            )
            NavigationRailItem(
                imageRes = R.drawable.user,
                text = R.string.account,
                navigateRoute = {navigateToMain()}
            )
        }
    }
}


@Composable
fun NavigationRailItem(
    modifier: Modifier = Modifier,
    @DrawableRes imageRes: Int,
    @StringRes text:Int,
    navigateRoute:()->Unit
){
    Column(modifier = modifier
        .size(65.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        IconButton(onClick = { navigateRoute() }) {
            Icon(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
        }
        Text(
            text = stringResource(id = text),
            fontSize = 11.sp
        )
    }
}


/*@Preview(showBackground = true)
@Composable
fun PreviewRail(){
    NavigationRailClub()
}*/