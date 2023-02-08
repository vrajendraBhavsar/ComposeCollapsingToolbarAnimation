package com.example.motionlayoutphilipplackner.ui.composables

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.motionlayoutphilipplackner.ui.theme.LeafyGreen
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
    //...Motion layout
    /*val toolbarHeightRange = with(LocalDensity.current) {
        MinToolbarHeight.roundToPx()..MaxToolbarHeight.roundToPx()
    }*/
    val context = LocalContext.current  //to get the raw file, we need context.
    Log.d("TAG", "ProfileHeader: progress => $progress")
    val motionScene = remember {    // To include raw file, the JSON5 script file
        context.resources.openRawResource(R.raw.motion_scene_netflix)
            .readBytes()
            .decodeToString()   //readBytes -> cuz we want motionScene in String
    }
    val motionHeight by animateDpAsState(
        targetValue = if (lazyListState?.firstVisibleItemIndex in 0..1) 300.dp else 60.dp,
        tween(1000)
    )
    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier
            .fillMaxWidth()
            .background(LeafyGreen)
//            .height(motionHeight * progress)
            .height((-1f * progress).dp)
//            .height(motionHeight)
        /*.graphicsLayer {
            translationY = -progress / 2f // Parallax effect
        }*/
        /*.graphicsLayer {
            scrollState?.value?.let {
                alpha = (-1f / (-1f * progress)) * it + 1
            }
        }*/
    ) {

        val boxProperties = motionProperties(id = "collapsing_box")
        val roundedShape = RoundedCornerShape(
            bottomStart = boxProperties.value.int("roundValue").dp,
            bottomEnd = boxProperties.value.int("roundValue").dp
        )

        /**
         * bg-box
         **/
        /*Box(
            modifier = Modifier
                .layoutId("collapsing_box")
                .clip(roundedShape)
                *//*.background(
                        brush = Brush.verticalGradient(
                            colors,
                            endY = 350f
                        )
                    )*//*
            )*/

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
                .fillMaxWidth()
                .height(motionHeight),

//                .height((-1f * progress).dp),
            /*.graphicsLayer {
                alpha = 1f
            },*/
            alignment = BiasAlignment(0f, 1f - ((1f - progress) * 0.75f)),
//            alpha = progress + 20// Update alpha based on progress. Expanded -> 1f / Collapsed -> 0f (transparent)
        )

        /**
         * Text - Collapsing
         **/

        /*val motionTextProperties = motionProperties(id = "motion_text")

        Text(
            text = stringResource(id = R.string.collapsing_text_star_wars_IX),
            color = motionTextProperties.value.color("textColor"),
//                fontWeight = if (progress == 1f) FontWeight.Light else FontWeight.Bold,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .layoutId("motion_text")
                .zIndex(1f)
        )*/

        /**
         * Main image
         **/
        /*Image(
            painter = painterResource(id = R.drawable.ic_darth_vader),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .layoutId("content_img")
//                .size(width = 72.dp, height = 92.dp)
                .clip(RoundedCornerShape(5.dp)),
//                    .zIndex(2f),
            contentDescription = "Content image holder"
        )*/
/*        */
        /**
         * Grid
         **//*
        scrollState?.let { scrollState ->
            Log.d("TAG", "!@# MotionAppBar scrollState: ${scrollState.value}")
            GridItemHandler(
                list = populateList(),
                columns = 2,
                modifier = Modifier
                    .layoutId("data_content")
                    .padding(top = (200 * progress).dp)
                    .background(Color.Red),
                scrollState = scrollState,
    //            contentPadding = PaddingValues(top = MaxToolbarHeight)
            )
        }*/
    }
}

@Preview(showBackground = true)
@Composable
fun CatalogPreview(
    @PreviewParameter(ListPreviewParameterProvider::class) list: List<Item>
) {
    MotionLayoutPhilippLacknerTheme {
        MotionAppBar(
            progress = 0.1f,
        )
    }
}
