package com.example.motionlayoutphilipplackner.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.motionlayoutphilipplackner.R
import com.example.motionlayoutphilipplackner.data.dummyData.ListPreviewParameterProvider
import com.example.motionlayoutphilipplackner.data.model.Item
import com.example.motionlayoutphilipplackner.ui.theme.GoldYellow
import com.example.motionlayoutphilipplackner.ui.theme.Gray245
import com.example.motionlayoutphilipplackner.ui.theme.MarioRedDark
import com.example.motionlayoutphilipplackner.ui.theme.MinionYellowDark
import com.example.motionlayoutphilipplackner.ui.theme.MotionLayoutPhilippLacknerTheme

private const val BottomBarHeightFraction = 0.14f
private const val TopBarHeightFraction = BottomBarHeightFraction / 1.5f
private val BarColor = Color(red = 0f, green = 0f, blue = 0f, alpha = 0.5f)

@Composable
fun GridItemCard(
    item: Item,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.aspectRatio(0.66f),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Gray245)) {
            item.itemImage?.let { painterResource(it) }?.let {
                Image(
                    painter = it,
                    contentDescription = item.itemDescription,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .padding(35.dp)
                        .fillMaxWidth()
                )
            }
            TopBar()
            item.itemName?.let { BottomBar(it) }
        }
    }
}

@Composable
private fun BoxScope.TopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(TopBarHeightFraction)
            .background(MarioRedDark)
            .padding(horizontal = 8.dp, vertical = 2.dp)
            .align(Alignment.TopCenter)
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight(0.75f)
                .wrapContentWidth()
                .align(Alignment.CenterStart),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Rounded.Star, contentDescription = null, tint = GoldYellow)
            Icon(imageVector = Icons.Rounded.Star, contentDescription = null, tint = GoldYellow)
            Icon(imageVector = Icons.Rounded.Star, contentDescription = null, tint = GoldYellow)
        }

        Row(
            modifier = Modifier
                .fillMaxHeight(0.75f)
                .wrapContentWidth()
                .align(Alignment.CenterEnd),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_coin),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp)),
                contentDescription = "Content image holder"
            )
            Text(
                text = "87",
                color = Color.Black,
                modifier = Modifier,
                fontFamily = FontFamily(
                    Font(R.font.super_mario_bros, FontWeight.Normal)
                ),
            )
            /*IconButton(
                onClick = {  },
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .background(
                        color = LocalContentColor.current.copy(alpha = 0.0f),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = vectorResource(R.drawable.your_icon),
                    contentDescription = null
                )
            }*/
            /*IconButton(
                onClick = {  },
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .background(
                        color = LocalContentColor.current.copy(alpha = 0.0f),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = null
                )
            }*/
        }
    }
}

@Composable
private fun BoxScope.BottomBar(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(BottomBarHeightFraction)
            .background(MarioRedDark)
            .align(Alignment.BottomCenter)
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            fontFamily = FontFamily(
                Font(R.font.super_mario_bros, FontWeight.Normal)
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GridItemCardPreview(
    @PreviewParameter(ListPreviewParameterProvider::class) list: List<Item>
) {
    MotionLayoutPhilippLacknerTheme() {
        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
        ) {
            list.take(2).forEach {
                GridItemCard(
                    item = it,
                    modifier = Modifier
                        .padding(2.dp)
                        .weight(1f)
                        .wrapContentHeight()
                )
            }
        }
    }
}