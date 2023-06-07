package com.example.effectiveclub.ui.dish

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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.effectiveclub.R
import com.example.effectiveclub.data.categories.Dish

@Composable
fun DishItem(
    modifier: Modifier = Modifier,
    dish:Dish,
    navigateUp:()->Unit
){
    Box(modifier = modifier
        .height(450.dp)
        .width(350.dp)
    ){
        Dialog(
            onDismissRequest = { /*TODO*/ },
        ) {
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
                    onClick = { /*TODO*/ },
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
/*
@Preview(showBackground = true)
@Composable
fun PreviewDialog(){
    val dish = Dish(
        id = 1,
        name = "Зеленый салат",
        price = 699,
        weight = 360,
        description = "Зеленый салат - это свежее и здоровое блюдо, доступное в нашем кафе. Салат состоит из различных видов зелени, включая свежий листовой салат, рукколу, цикорий и шпинат, а также дополнительных ингредиентов, таких как огурцы, томаты, морковь и лук.",
        imageUrl = "https://lh3.googleusercontent.com/fife/APg5EOaUlM5LIJ7RJAgnA_O260Q9EBTqlKUSR258lZFimED04hgD8ks7jYrhMLrp_atad3KPdx_jrnZCfyLNUh5dejumo5rNJfFFdLx_S0t_AJh09BwSjszu82QKFrwiwNdqw8OKbKGLnbcy8c4ZOGcuSL79ZRUyiFZKvQ-E230L5_S-fT4yEOKuQwwtnq2w66KBkqbNGV0p_moe_Be1cuezoz2xcYyfj7FbkXw8wl1vwOORgvXqQs7cY2qKpbTDU8jg4L5aHVoX4Y6GZfLmNwV-kBRUVXgwKRXurjkmrReoO05fauwwjK7OxQV4WEng88hkMdhXUmm4Cq_WyE2L-oNfiKfZyMrOG1r_7xx-G49mmpCnGI0Ct4aXWhW6drGtpu1mR1B51vs61AhlPz0nLsIPMwZvu7C62dTVXNSpPFT5n3pDdsOdTMo1Cora0r-ClhX2OCyYdg_vkgJRGagfYooM17ZIfc73H5NP6Cf4FizDkJl1UE4T2AdOq2qt2Z3GDY3R-0Do-U83sM0G-InegZLrE-Uqh0xEz8mPa8NE-yLvyjKf8lcPc7g2loSPOtnxhhTvKciORuZVajA5-Ee3y8BvisKuhxVnGMbkf-eQyBWBk7I4KCrTjunaq5SLr7Bhm1roE9zaEBC6YJYfvbrACXiymnH5DBK8XaW0yb8vy3NUPnCv-WnyqN4XIg0S1Qy_qmpY8iIVF9L32rvoLBVzkawlFpT4JUU_lqpj4KZrAOYeHk6CQi5W3MkcT2jz1ieN_GkTvrY3WhQPcw0pCxEqf375pV71JQn8NzlTVJKPtjTPhJ4TF_8MHzfY5V1_M9WhN4HO3CPg0xvY-tvWRDzzSwsuulyM_2kD7Lz63uzYmUAVjY4HoPDC1F-Q9Po0vjijUjvlTqOK9g60cGUFpurNFCgvwDLMg4rO-jPDh_YBBxhkv1DHWINUvIWhcar3r8VJDPkH0VYUF8HBKmgJwj-wf5GlEoarsADYj4j_ep5C7hu1Vj1AaTr9DMGglvQ1GAwLBZFrqLkj4WPMn0CvFY7sG4o7-sfRaKD6k5MwSy4riqILwRbQzgbzmu5WYsOEAAhB-pLyDpiOY2v9YQoStI_G45CObq8PAsl_BbjPz3R1gZaImsydjW9YJAtuzlPDxFAgG7mo16aRnMi_PoYrPS7EYKrDVMNVietx-yS4MlWZ_BktwWA-7zo4I1SAEPsPinyptTFMwgUAAi0tF1uqSEXBTpdoCQuik9SwhX77943m62loncliGr5MElnsncrtVeRtvtlGQowIgX4Cfvpph3C1ljZBX24TUp3UQHAuGYFw2kZ9nDM4wvhWQufh_RxsBlB4JYjLVQU0wask61jk9p7gwCo7089PFL7SRu5fN5cBAowwxipBpeujf9pZBIa4iTZxdj9mCqfavEuxGWSe_rCC4I_rdt5IbrVfaLR_sw4lh7QCkrvAnGVxitGrDXnyPsKkH1-vGUIregl4qohL_XJQsys51l-Mm040P3ufZCBGnXNtVUC5cezMi-PeEgQceWc1t9UAO666iqF_rcXacSBVxm7dwQQZbl2GjnX81FVDq0wCc6r0hL8DU0wlwwBMKg1gQ9KYB_2NGxoc5uqOb1u-KiOdPEjmtJF8seVW-9E41XEK-AP6NhuDqHbJ=w1366-h617",
        tags = listOf("Все меню", "Салаты")
    )

    DishItem(dish = dish)
}*/