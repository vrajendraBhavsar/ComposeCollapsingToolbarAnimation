package com.example.motionlayoutphilipplackner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.example.motionlayoutphilipplackner.ui.MainScreenContent
import com.example.motionlayoutphilipplackner.ui.temp.CollapsingToolbarParallaxEffect
import com.example.motionlayoutphilipplackner.ui.theme.MotionLayoutPhilippLacknerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MotionLayoutPhilippLacknerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
//                    CollapsingToolbarParallaxEffect()
                    MainScreenContent()
                }
            }
        }
    }
}