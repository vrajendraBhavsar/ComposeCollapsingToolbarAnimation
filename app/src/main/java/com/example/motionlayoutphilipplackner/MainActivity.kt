package com.example.motionlayoutphilipplackner

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.motionlayoutphilipplackner.data.model.Item
import com.example.motionlayoutphilipplackner.ui.composables.ProductCatalog
import com.example.motionlayoutphilipplackner.ui.theme.MotionLayoutPhilippLacknerTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MotionLayoutPhilippLacknerTheme {
                Column {
                    var progress by remember {
                        mutableStateOf(0f)
                    }
//                    Toolbar(progress = progress)
                    Slider(value = progress, onValueChange = {
                        progress = it
                    }, modifier = Modifier.padding(horizontal = 32.dp))
                }

                val systemUiController = rememberSystemUiController()
                val useDarkIcons = !MaterialTheme.colors.isLight
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        Color.Transparent,
                        darkIcons = useDarkIcons
                    )
                }

                ScreenContent(populateList())
            }
        }
    }
}

fun populateList(): List<Item> {
    return listOf(
        Item("The Godfather", itemImage = R.drawable.ic_profile_pic),
        Item("The Shawshank Redemption",itemImage =  R.drawable.ic_profile_pic),
        Item("Schindler's List",itemImage =  R.drawable.ic_profile_pic),
        Item("Casablanca ",itemImage =  R.drawable.ic_profile_pic),
        Item("Forrest Gump",itemImage =  R.drawable.ic_profile_pic),
        Item("Star Wars",itemImage =  R.drawable.ic_profile_pic),
        Item(" The Lord of the Rings: The Return of the King",itemImage =  R.drawable.ic_profile_pic),
        Item("Jurassic Park",itemImage =  R.drawable.ic_profile_pic),
        Item("The Deer Hunter",itemImage =  R.drawable.ic_profile_pic),
        Item("Bonnie and Clyde",itemImage =  R.drawable.ic_profile_pic),
        Item("The Silence of the Lambs",itemImage =  R.drawable.ic_profile_pic),
        Item("E.T. the Extra-Terrestrial",itemImage =  R.drawable.ic_profile_pic)
    )
}

@Composable
fun ScreenContent(item: List<Item>) {
    MotionLayoutPhilippLacknerTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            val context = LocalContext.current
            ProductCatalog(
                item = item,
                columns = 2,
                onPrivacyTipButtonClicked = {
                    Toast.makeText(context, "Privacy Tip button clicked!", Toast.LENGTH_SHORT)
                        .show()
                },
                onSettingsButtonClicked = {
                    Toast.makeText(context, "Setting button clicked!", Toast.LENGTH_SHORT).show()
                },
            )
        }
    }
}