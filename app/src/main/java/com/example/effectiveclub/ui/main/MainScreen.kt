package com.example.effectiveclub.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.effectiveclub.R
import com.example.effectiveclub.data.main.Categories

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
){
    Column(modifier = modifier
        .padding(
            dimensionResource(id = R.dimen.padding_main_screen)
        )
        .fillMaxWidth()
    ) {
        TopMainBar()
        when(viewModel.mainUistate){
            is MainUIState.Success ->{
                MainList(itemList = (viewModel.mainUistate as MainUIState.Success).mainlist.categories)
            }
            is MainUIState.Error ->{

            }
            is MainUIState.Loading ->{

            }
        }
    }
}

@Composable
fun MainList(
    modifier:Modifier = Modifier,
    itemList:List<Categories>
){

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        items(itemList){
            MainCard(item = it) {

            }
        }
    }
}


@Composable
fun TopMainBar(
    modifier: Modifier = Modifier
){
    Row(modifier = modifier
        .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.placeholder),
            contentDescription = stringResource(R.string.geolocation_desc),
            modifier = Modifier
                .size(
                    width = dimensionResource(id = R.dimen.geolocation_width),
                    height = dimensionResource(id = R.dimen.geolocation_height)
                )
                .weight(1f)
        )
        Column(modifier = modifier.weight(10f)) {
            Text(text = "Saint - Petersburg")
            Text(text = "12-th, August 2023")
        }
        Image(
            painter = painterResource(id = R.drawable.user),
            contentDescription = stringResource(R.string.user_photo_desc),
            modifier = Modifier.size(
                dimensionResource(id = R.dimen.user_photo_size)
            )
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainCard(
    modifier:Modifier = Modifier,
    item: Categories,
    onCardClick:() -> Unit
){

    Card(
        modifier = modifier.padding(top = dimensionResource(id = R.dimen.padding_between_cards)),
        onClick = onCardClick,
    ) {
        Box(modifier = modifier) {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(item.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null
            )
            item.name?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_between_cards))
                )
            }
        }
    }
}


@Preview
@Composable
fun CardPreview(){
    TopMainBar()
}