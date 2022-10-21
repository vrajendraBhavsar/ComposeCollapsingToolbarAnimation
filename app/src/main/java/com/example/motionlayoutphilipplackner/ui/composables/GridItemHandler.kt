package com.example.motionlayoutphilipplackner.ui.composables

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.motionlayoutphilipplackner.data.dummyData.ListPreviewParameterProvider
import com.example.motionlayoutphilipplackner.data.model.Item
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
            modifier = Modifier.fillMaxSize()
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
@Composable
fun GridItemHandler(
    list: List<Item>,
    columns: Int,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {

    val chunkedList = remember(list, columns) {
        list.chunked(columns)
    }

    Column(
        modifier = modifier.verticalScroll(scrollState)
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
