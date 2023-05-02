package com.example.collapsingToolbarWithMotionCompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.example.collapsingToolbarWithMotionCompose.ui.MainScreenContent
import com.example.collapsingToolbarWithMotionCompose.ui.theme.MarioRedDark
import com.example.collapsingToolbarWithMotionCompose.ui.theme.CollapsingToolbarWithMotionComposeTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            CollapsingToolbarWithMotionComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    val systemUiController = rememberSystemUiController()
                    val useDarkIcons = MaterialTheme.colors.isLight

                    // To set the status bar color
                    SideEffect {
                        systemUiController.setSystemBarsColor(
                            color = MarioRedDark,
                            darkIcons = useDarkIcons
                        )
                    }
                    MainScreenContent()
                }
            }
        }
    }
}