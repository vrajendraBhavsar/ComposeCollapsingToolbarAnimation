package com.example.motionlayoutphilipplackner.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.example.motionlayoutphilipplackner.R
import com.example.motionlayoutphilipplackner.data.dummyData.ListPreviewParameterProvider
import com.example.motionlayoutphilipplackner.data.model.Item
import com.example.motionlayoutphilipplackner.extra.scrollflags.ExitUntilCollapsedState
import com.example.motionlayoutphilipplackner.ui.management.ToolbarState
import com.example.motionlayoutphilipplackner.ui.theme.MinionYellowLight
import com.example.motionlayoutphilipplackner.ui.theme.MotionLayoutPhilippLacknerTheme

val MinToolbarHeight = 96.dp
val MaxToolbarHeight = 176.dp

@Composable
fun rememberToolbarState(toolbarHeightRange: IntRange): ToolbarState {
    return rememberSaveable(saver = ExitUntilCollapsedState.Saver) {
        ExitUntilCollapsedState(toolbarHeightRange)
    }
}

@OptIn(ExperimentalMotionApi::class)
@Composable
fun MotionAppBar(progress: Float, lazyListState: LazyListState? = null) {
    val context = LocalContext.current  //to get the raw file, we need context.

    val motionScene = remember {    // To include raw file, the JSON5 script file
        context.resources.openRawResource(R.raw.motion_scene_minion)
            .readBytes()
            .decodeToString()   //readBytes -> cuz we want motionScene in String
    }

    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier
            .fillMaxWidth()
            .background(MinionYellowLight)
            .height((-1f * progress).dp)
    ) {

        /**
         * Text - Collapsing
         **/
        /*val motionTextProperties = motionProperties(id = "motion_text")
        Text(
            text = stringResource(id = R.string.collapsing_text_minion),
            color = motionTextProperties.value.color("textColor"),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .layoutId("motion_text")
                .zIndex(1f)
        )*/

        val boxProperties = motionProperties(id = "collapsing_box")
        val roundedShape = RoundedCornerShape(
            bottomStart = boxProperties.value.int("roundValue").dp,
            bottomEnd = boxProperties.value.int("roundValue").dp
        )

        /**
         * bg-image
         **/
        Image(
            painter = painterResource(id = R.drawable.ic_topbar_minion),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .layoutId("collapsing_box")
                .clip(roundedShape)
                .fillMaxWidth(),
            alignment = BiasAlignment(0f, 1f - ((1f - progress) * 0.75f)),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MotionAppBarCatalogPreview(
    @PreviewParameter(ListPreviewParameterProvider::class) list: List<Item>
) {
    MotionLayoutPhilippLacknerTheme {
        MotionAppBar(
            progress = 1f,
            LazyListState(5, 1)
        )
    }
}
