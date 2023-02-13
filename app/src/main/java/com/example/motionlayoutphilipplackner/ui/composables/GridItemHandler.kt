package com.example.motionlayoutphilipplackner.ui.composables

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.example.motionlayoutphilipplackner.R
import com.example.motionlayoutphilipplackner.data.dummyData.ListPreviewParameterProvider
import com.example.motionlayoutphilipplackner.data.model.Item
import com.example.motionlayoutphilipplackner.ui.theme.BloodRed
import com.example.motionlayoutphilipplackner.ui.theme.LeafyGreen
import com.example.motionlayoutphilipplackner.ui.theme.MotionLayoutPhilippLacknerTheme

@Preview(showBackground = true)
@Composable
fun GridItemHandlerPreview(
    @PreviewParameter(ListPreviewParameterProvider::class) list: List<Item>
) {
    MotionLayoutPhilippLacknerTheme {
        GridItemHandler(
            list = list,
            columns = 2,
            modifier = Modifier.fillMaxSize(),
            progress = 0.5f
        )
    }
}

/**-------------------------------------------------------------------------------------- *
 *                                  W  A  R  N  I  N  G                                   *
 * -------------------------------------------------------------------------------------- *
 * This composable function is only for illustration purpose.                             *
 * DO NOT attempt using a Column component whose content is built dynamically.            *
 * This is highly inefficient and you should prefer using a LazyColumn component instead. *
 * -------------------------------------------------------------------------------------- */
@OptIn(ExperimentalMotionApi::class)
@Composable
fun GridItemHandler(
    list: List<Item>,
    columns: Int,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    progress: Float,
    lazyListState: LazyListState? = null
) {

    val chunkedList = remember(list, columns) {
        list.chunked(columns)
    }
    val context = LocalContext.current  //to get the raw file, we need context.
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
            .wrapContentHeight()
            .background(LeafyGreen)
//            .height(motionHeight * progress)
//            .height((-1f * progress).dp)
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
        /**
         * Text - Collapsing
         */
        val motionTextProperties = motionProperties(id = "motion_text")

        Text(
            text = stringResource(id = R.string.collapsing_text_star_wars_IX),
            color = motionTextProperties.value.color("textColor"),
//                fontWeight = if (progress == 1f) FontWeight.Light else FontWeight.Bold,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .layoutId("motion_text")
                .zIndex(1f)
        )

        /**
         * Main image
         **/
        Image(
            painter = painterResource(id = R.drawable.ic_darth_vader),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .layoutId("content_img")
//                .size(width = 72.dp, height = 92.dp)
                .clip(RoundedCornerShape(5.dp)),
//                .zIndex(2f),
            contentDescription = "Content image holder"
        )

        /**
        * Grid
        **/
        Column(
            modifier = modifier
                .verticalScroll(scrollState)
                .layoutId("data_content"),
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(contentPadding.calculateTopPadding())
            )

            chunkedList.forEach { chunk ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {

                    Spacer(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(contentPadding.calculateStartPadding(LocalLayoutDirection.current))
                    )

                    chunk.forEach { list ->
                        GridItemCard(
                            item = list,
                            modifier = Modifier
                                .padding(2.dp)
                                .weight(1f)
                        )
                    }

                    val emptyCells = columns - chunk.size
                    if (emptyCells > 0) {
                        Spacer(modifier = Modifier.weight(emptyCells.toFloat()))
                    }

                    Spacer(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(contentPadding.calculateEndPadding(LocalLayoutDirection.current))
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(contentPadding.calculateBottomPadding())
            )
        }
    }
}
