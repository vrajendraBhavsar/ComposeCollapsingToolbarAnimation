package com.example.motionlayoutphilipplackner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Slider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.core.view.WindowCompat
import com.example.motionlayoutphilipplackner.data.dummyData.populateList
import com.example.motionlayoutphilipplackner.data.model.Item
import com.example.motionlayoutphilipplackner.ui.MainScreenContent
import com.example.motionlayoutphilipplackner.ui.composables.*
import com.example.motionlayoutphilipplackner.ui.theme.MotionLayoutPhilippLacknerTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MotionLayoutPhilippLacknerTheme {
                MainScreenContent()
            }
        }
    }
}

/*fun populateList(): List<Item> {
    return listOf(
        Item("The Godfather", itemImage = R.drawable.ic_darth_vader),
        Item("The Shawshank Redemption",itemImage =  R.drawable.ic_darth_vader),
        Item("Schindler's List",itemImage =  R.drawable.ic_darth_vader),
        Item("Casablanca ",itemImage =  R.drawable.ic_darth_vader),
        Item("Forrest Gump",itemImage =  R.drawable.ic_darth_vader),
        Item("Star Wars",itemImage =  R.drawable.ic_darth_vader),
        Item(" The Lord of the Rings: The Return of the King",itemImage =  R.drawable.ic_darth_vader),
        Item("Jurassic Park",itemImage =  R.drawable.ic_darth_vader),
        Item("The Deer Hunter",itemImage =  R.drawable.ic_darth_vader),
        Item("Bonnie and Clyde",itemImage =  R.drawable.ic_darth_vader),
        Item("The Silence of the Lambs",itemImage =  R.drawable.ic_darth_vader),
        Item("E.T. the Extra-Terrestrial",itemImage =  R.drawable.ic_darth_vader)
    )
}*/

/*@Composable
fun ScreenContent(item: List<Item>) {
    MotionLayoutPhilippLacknerTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            ProductCatalog(
                item = item,
                columns = 2,
            )
        }
    }
}*/