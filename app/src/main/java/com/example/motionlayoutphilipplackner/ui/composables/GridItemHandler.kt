package com.example.motionlayoutphilipplackner.ui.composables

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.example.motionlayoutphilipplackner.ui.theme.MinionYellowLight
import com.example.motionlayoutphilipplackner.ui.theme.MotionLayoutPhilippLacknerTheme

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
    val context = LocalContext.current  //to get the raw file, we need context.

    val chunkedList = remember(list, columns) {
        list.chunked(columns)
    }
    Log.d("TAG", "!@# GridItemHandler: chunkedList:: ${chunkedList.size}, List:: ${list.size}, scrollState:: ${scrollState.value}")

    val motionScene = remember {    // To include raw file, the JSON5 script file
        context.resources.openRawResource(R.raw.motion_scene_minion)
            .readBytes()
            .decodeToString()   //readBytes -> cuz we want motionScene in String
    }

    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier
            .fillMaxSize()
            .background(MinionYellowLight)
    ) {
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

        /**
         * Text - Collapsing
         */
        val motionTextProperties = motionProperties(id = "motion_text")

        Text(
            text = stringResource(id = R.string.collapsing_text_minion),
            color = motionTextProperties.value.color("textColor"),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .layoutId("motion_text")
                .zIndex(1f)
        )

        /**
         * Main image
         **/
        Image(
            painter = painterResource(id = R.drawable.ic_minion_primary_image),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .layoutId("content_img")
                .clip(RoundedCornerShape(5.dp)),
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

                    chunk.forEach { listItem ->
                        GridItemCard(
                            item = listItem,
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
                    .height(140.dp)
            )
        }
    }
//    Log.d("TAG", "!@# GridItemHandler: chunkedList:: ${chunkedList.size}, List:: ${list.size}, scrollState:: ${scrollState.value}")
}

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