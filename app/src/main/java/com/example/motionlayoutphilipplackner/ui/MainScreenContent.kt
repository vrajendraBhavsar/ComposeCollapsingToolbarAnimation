package com.example.motionlayoutphilipplackner.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.motionlayoutphilipplackner.data.dummyData.populateList
import com.example.motionlayoutphilipplackner.extra.scrollflags.ExitUntilCollapsedState
import com.example.motionlayoutphilipplackner.ui.composables.*
import com.example.motionlayoutphilipplackner.ui.management.ToolbarState
import com.example.motionlayoutphilipplackner.ui.theme.MotionLayoutPhilippLacknerTheme

val MinToolbarHeight = 96.dp
val MaxToolbarHeight = 176.dp

@Composable
fun rememberToolbarState(toolbarHeightRange: IntRange): ToolbarState {
    return rememberSaveable(saver = ExitUntilCollapsedState.Saver) {
        ExitUntilCollapsedState(toolbarHeightRange)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreenContent() {
    val toolbarHeightRange = with(LocalDensity.current) {
        MinToolbarHeight.roundToPx()..MaxToolbarHeight.roundToPx()
    }
    val toolbarState = rememberToolbarState(toolbarHeightRange)
    val scrollState = rememberScrollState()
    val lazyScrollState = rememberLazyListState()

    toolbarState.scrollValue = scrollState.value

    val progress = toolbarState.progress

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
//            MotionAppBar(progress, lazyScrollState)
        },
        content = {
            GridItemHandler(
                list = populateList(),
                columns = 2,
                modifier = Modifier.fillMaxSize(),
                scrollState = scrollState,
                progress = progress,
                lazyListState = lazyScrollState
            )
        })
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreenContent() {
    MotionLayoutPhilippLacknerTheme {
        MainScreenContent()
    }
}