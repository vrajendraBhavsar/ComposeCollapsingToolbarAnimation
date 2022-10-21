package com.example.motionlayoutphilipplackner.ui.composables

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
//import androidx.compose.material.icons.rounded.PrivacyTip
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.example.motionlayoutphilipplackner.R
import com.example.motionlayoutphilipplackner.ui.theme.MotionLayoutPhilippLacknerTheme
import kotlin.math.roundToInt

private val ContentPadding = 8.dp
private val Elevation = 4.dp
private val ButtonSize = 24.dp
private const val Alpha = 0.75f

private val ExpandedPadding = 1.dp
private val CollapsedPadding = 3.dp

private val ExpandedCostaRicaHeight = 20.dp
private val CollapsedCostaRicaHeight = 16.dp

private val ExpandedWildlifeHeight = 32.dp
private val CollapsedWildlifeHeight = 24.dp

private val MapHeight = CollapsedCostaRicaHeight * 2

@OptIn(ExperimentalMotionApi::class)
@Composable
fun CollapsingToolbar(
    @DrawableRes backgroundImageResId: Int,
    progress: Float,
    onPrivacyTipButtonClicked: () -> Unit,
    onSettingsButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    /*val logoPadding = with(LocalDensity.current) {
        lerp(CollapsedPadding.toPx(), ExpandedPadding.toPx(), progress).toDp()
    }*/

    val context = LocalContext.current  //to get the raw file, we need context.
    Log.d("TAG", "ProfileHeader: progress => $progress")
    val motionScene = remember {    // To include raw file, the JSON5 script file
        context.resources.openRawResource(R.raw.motion_scene_netflix)
            .readBytes()
            .decodeToString()   //readBytes -> cuz we want motionScene in String
    }


//./......
    Surface(
        color = MaterialTheme.colors.primary,
        elevation = Elevation,
        modifier = modifier
//    MotionLayout(
//        motionScene = MotionScene(content = motionScene),
//        progress = progress,
//        modifier = modifier,
    ) {
//        val propertiesContentImage = motionProperties(id = "content_img")

        Box(modifier = Modifier.fillMaxSize()) {
            //#region Background Image
            Image(
                painter = painterResource(id = backgroundImageResId),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        alpha = progress * Alpha
                    },
                alignment = BiasAlignment(0f, 1f - ((1f - progress) * 0.75f))
            )
            //#endregion
            Box(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(horizontal = ContentPadding)
                    .fillMaxSize()
            ) {
                CollapsingToolbarLayout(progress = progress) {
                    //#region Logo Images
                    /*Image(
                        painter = painterResource(id = R.drawable.ic_shield),
                        contentDescription = null,
                        modifier = Modifier
                            .height(MapHeight)
                            .wrapContentWidth()
                            .graphicsLayer { alpha = ((0.25f - progress) * 4).coerceIn(0f, 1f) },
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary)
                    )*/
                    /*Image(
                        painter = painterResource(id = R.drawable.logo_costa),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(logoPadding)
                            .height(costaRicaHeight)
                            .wrapContentWidth(),
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.logo_rica),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(logoPadding)
                            .height(costaRicaHeight)
                            .wrapContentWidth(),
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.logo_wildlife),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(logoPadding)
                            .height(wildlifeHeight)
                            .wrapContentWidth(),
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary)
                    )*/
                    //#endregion
                    //#region Buttons
                    /*Image(
                        painter = painterResource(id = R.drawable.ic_darth_vader),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .layoutId("content_img"),
                        contentDescription = "Content image holder"
                    )*/
                    Icon(  //Shield icon
                        painter = painterResource(id = R.drawable.ic_shield), contentDescription = null,
                        modifier = Modifier
                            /*.clip(CircleShape)
                            .border(
                                width = 2.dp,
            //                    color = Color.Green,
                                color = propertiesOfProfilePic.value.color(name = "color"),
                                shape = CircleShape
                            )*/
                            .layoutId("content_img"),    // Same ID that we have used in motion_scene.json5
                        tint = Color.White
                    )
                    /*Row(
                        modifier = Modifier.wrapContentSize(),
                        horizontalArrangement = Arrangement.spacedBy(ContentPadding)
                    ) {
                        IconButton(
                            onClick = onPrivacyTipButtonClicked,
                            modifier = Modifier
                                .size(ButtonSize)
                                .background(
                                    color = LocalContentColor.current.copy(alpha = 0.0f),
                                    shape = CircleShape
                                )
                        ) {
                            Icon(
                                modifier = Modifier.fillMaxSize(),
                                imageVector = Icons.Rounded.Info,
                                contentDescription = null,
                            )
                        }
                        IconButton(
                            onClick = onSettingsButtonClicked,
                            modifier = Modifier
                                .size(ButtonSize)
                                .background(
                                    color = LocalContentColor.current.copy(alpha = 0.0f),
                                    shape = CircleShape
                                )
                        ) {
                            Icon(
                                modifier = Modifier.fillMaxSize(),
                                imageVector = Icons.Rounded.Settings,
                                contentDescription = null,
                            )
                        }
                    }*/
                    //#endregion
                }
            }
        }
    }
}

@Composable
private fun CollapsingToolbarLayout(
    progress: Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
//        check(measurables.size == 5) // [0]: Country Map | [1-3]: Logo Images | [4]: Buttons

        val placeables = measurables.map {
            it.measure(constraints)
        }
        layout(
            width = constraints.maxWidth,
            height = constraints.maxHeight
        ) {

            val expandedHorizontalGuideline = (constraints.maxHeight * 0.4f).roundToInt()
            val collapsedHorizontalGuideline = (constraints.maxHeight * 0.5f).roundToInt()

            val countryMap = placeables[0]
            /*val costa = placeables[1]
            val rica = placeables[2]
            val wildlife = placeables[3]
            val buttons = placeables[4]*/
            countryMap.placeRelative(
                x = 0,
                y = collapsedHorizontalGuideline - countryMap.height / 2,
            )
            /*costa.placeRelative(
                x = lerp(
                    start = countryMap.width,
                    stop = constraints.maxWidth / 2 - costa.width,
                    fraction = progress
                ),
                y = lerp(
                    start = collapsedHorizontalGuideline - costa.height / 2,
                    stop = expandedHorizontalGuideline - costa.height,
                    fraction = progress
                )
            )
            rica.placeRelative(
                x = lerp(
                    start = countryMap.width + costa.width,
                    stop = constraints.maxWidth / 2 - rica.width,
                    fraction = progress
                ),
                y = lerp(
                    start = collapsedHorizontalGuideline - rica.height / 2,
                    stop = expandedHorizontalGuideline,
                    fraction = progress
                )
            )
            wildlife.placeRelative(
                x = lerp(
                    start = countryMap.width + costa.width + rica.width,
                    stop = constraints.maxWidth / 2,
                    fraction = progress
                ),
                y = lerp(
                    start = collapsedHorizontalGuideline - wildlife.height / 2,
                    stop = expandedHorizontalGuideline + rica.height / 2,
                    fraction = progress
                )
            )
            buttons.placeRelative(
                x = constraints.maxWidth - buttons.width,
                y = lerp(
                    start = (constraints.maxHeight - buttons.height) / 2,
                    stop = 0,
                    fraction = progress
                )
            )*/
        }
    }
}

@Preview
@Composable
fun CollapsingToolbarCollapsedPreview() {
    MotionLayoutPhilippLacknerTheme {
        CollapsingToolbar(
            backgroundImageResId = R.drawable.ic_starwars,
            progress = 0f,
            onPrivacyTipButtonClicked = {},
            onSettingsButtonClicked = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        )
    }
}

@Preview
@Composable
fun CollapsingToolbarHalfwayPreview() {
    MotionLayoutPhilippLacknerTheme {
        CollapsingToolbar(
            backgroundImageResId = R.drawable.ic_starwars,
            progress = 0.5f,
            onPrivacyTipButtonClicked = {},
            onSettingsButtonClicked = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )
    }
}

@Preview
@Composable
fun CollapsingToolbarExpandedPreview() {
    MotionLayoutPhilippLacknerTheme {
        CollapsingToolbar(
            backgroundImageResId = R.drawable.ic_starwars,
            progress = 1f,
            onPrivacyTipButtonClicked = {},
            onSettingsButtonClicked = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        )
    }
}
