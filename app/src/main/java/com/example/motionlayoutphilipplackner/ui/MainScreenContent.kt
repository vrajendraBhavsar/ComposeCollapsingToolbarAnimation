package com.example.motionlayoutphilipplackner.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.example.motionlayoutphilipplackner.data.dummyData.populateList
import com.example.motionlayoutphilipplackner.ui.composables.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MainScreenContent() {
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

//                ScreenContent(populateList())
    MotionAppBar()
}

@Composable
fun MotionAppBar() {
    val toolbarHeightRange = with(LocalDensity.current) {
        MinToolbarHeight.roundToPx()..MaxToolbarHeight.roundToPx()
    }
    val toolbarState = rememberToolbarState(toolbarHeightRange)
    val scrollState = rememberScrollState()

    toolbarState.scrollValue = scrollState.value

    val progress = toolbarState.progress

    Scaffold(//
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            MotionAppBar(progress)
        }
    ) { padding ->
        GridItemHandler(
            list = populateList(),
            columns = 2,
            modifier = Modifier
                .layoutId("data_content")
                .padding(padding)
                .zIndex(0f)
                .padding(top = (200 * progress).dp),
            scrollState = scrollState,
//            contentPadding = PaddingValues(top = MaxToolbarHeight)
        )
    }
}