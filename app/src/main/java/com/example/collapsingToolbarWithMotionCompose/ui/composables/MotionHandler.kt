package com.example.collapsingToolbarWithMotionCompose.ui.composables

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.example.collapsingToolbarWithMotionCompose.R
import com.example.collapsingToolbarWithMotionCompose.data.dummyData.ListPreviewParameterProvider
import com.example.collapsingToolbarWithMotionCompose.data.model.Item
import com.example.collapsingToolbarWithMotionCompose.ui.theme.MarioRedDark
import com.example.collapsingToolbarWithMotionCompose.ui.theme.MarioRedLight
import com.example.collapsingToolbarWithMotionCompose.ui.theme.CollapsingToolbarWithMotionComposeTheme

/**-------------------------------------------------------------------------------------- *
 *                                  W  A  R  N  I  N  G                                   *
 * -------------------------------------------------------------------------------------- *
 * This composable function is only for illustration purpose.                             *
 * DO NOT attempt using a Column component whose content is built dynamically.            *
 * This is highly inefficient and you should prefer using a LazyColumn component instead. *
 * -------------------------------------------------------------------------------------- */
@OptIn(ExperimentalMotionApi::class)
@Composable
fun MotionHandler(
    list: List<Item>,
    columns: Int,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    progress: Float
) {
    val context = LocalContext.current  //to get the raw file, we need context.

    val chunkedList = remember(list, columns) {
        list.chunked(columns)
    }
    Log.d("TAG", "!@# GridItemHandler: chunkedList:: ${chunkedList.size}, List:: ${list.size}, scrollState:: ${scrollState.value}")

    val motionScene = remember {    // To include raw file, the JSON5 script file
        context.resources.openRawResource(R.raw.motion_scene_mario)
            .readBytes()
            .decodeToString()   //readBytes -> cuz we want motionScene in a String format
    }

    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier
            .fillMaxSize()
            .background(MarioRedLight)
    ) {
        val boxProperties = motionProperties(id = "collapsing_box") //Component for which we have defined custom properties
        val roundedShape = RoundedCornerShape(
            bottomStart = boxProperties.value.int("roundValue").dp, //This way you can use custom properties which is defined inside motion scene
            bottomEnd = boxProperties.value.int("roundValue").dp
        )

        /**
         * bg-image
         **/
        Image(
            painter = painterResource(id = R.drawable.ic_mario_level),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .layoutId("collapsing_box")
                .clip(roundedShape)
                .fillMaxWidth()
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = size.height / 3,
                        endY = size.height
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.Multiply)
                    }
                },
            alignment = BiasAlignment(0f, 1f - ((1f - progress) * 0.50f)),
        )

        /**
         * Text - Collapsing
         */
        Text(
            text = stringResource(id = R.string.collapsing_text_minion),
            color = MarioRedDark,
            modifier = Modifier
                .layoutId("motion_text")
                .zIndex(1f),
            fontFamily = FontFamily(
                Font(R.font.super_mario_bros, FontWeight.Light)
            ),
            fontSize = 14.sp
        )

        /**
         * Main image
         **/
        Image(
            painter = painterResource(id = R.drawable.ic_mario_reversed),
            contentScale = ContentScale.Fit,
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
                .layoutId("data_content")
                .background(MarioRedLight),
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

        /**
         * piranha flower
         **/
        Image(
            painter = painterResource(id = R.drawable.ic_piranha_flower),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .layoutId("piranha_flower"),
            contentDescription = "Content image holder"
        )

        /**
         * piranha tunnel
         **/
        Image(
            painter = painterResource(id = R.drawable.ic_piranha_tunnel),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .layoutId("piranha_tunnel"),
            contentDescription = "Content image holder"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GridItemHandlerPreview(
    @PreviewParameter(ListPreviewParameterProvider::class) list: List<Item>
) {
    CollapsingToolbarWithMotionComposeTheme {
        MotionHandler(
            list = list,
            columns = 2,
            modifier = Modifier.fillMaxSize(),
            progress = 0.5f
        )
    }
}