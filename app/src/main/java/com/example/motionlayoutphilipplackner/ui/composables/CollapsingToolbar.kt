package com.example.motionlayoutphilipplackner.ui.composables

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionLayoutDebugFlags
import androidx.constraintlayout.compose.MotionScene
import com.example.motionlayoutphilipplackner.R
import com.example.motionlayoutphilipplackner.ui.theme.MotionLayoutPhilippLacknerTheme
import java.util.*
import kotlin.math.roundToInt

private val ContentPadding = 8.dp
private val Elevation = 4.dp
private const val Alpha = 0.75f

@OptIn(ExperimentalMotionApi::class)
@Composable
fun CollapsingToolbar(
    @DrawableRes backgroundImageResId: Int,
    progress: Float,
    modifier: Modifier = Modifier
) {

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
    ) {


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
            Box(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(horizontal = ContentPadding)
                    .fillMaxSize()
            ) {
                CollapsingToolbarLayout(progress = progress) {
                    //#region Buttons
                    MotionLayout(
                        motionScene = MotionScene(content = motionScene),
                        progress = progress,
                        modifier = modifier,
                        debug = EnumSet.of(MotionLayoutDebugFlags.SHOW_ALL)
                    ) {

                        val motionTextProperties = motionProperties(id = "motion_text")
                        /**
                         * Main image with Animation - Collapsing
                         */
                        Image(
                            painter = painterResource(id = R.drawable.ic_darth_vader),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .layoutId("content_img"),
                            contentDescription = "Content image holder"
                        )
                        /**
                         * Text - Collapsing
                         */
                        Text(
                            text = stringResource(id = R.string.collapsing_text_star_wars_IX),
                            color = motionTextProperties.value.color("textColor"),
                            modifier = Modifier.layoutId("motion_text")
                        )
                    }
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
            countryMap.placeRelative(
                x = 0,
                y = collapsedHorizontalGuideline - countryMap.height / 2,
            )
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
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        )
    }
}

@Preview
@Composable
fun PreviewMotionLayoutAppBar() {
    val motionLayoutProgress = remember { Animatable(0.0f) }

    LaunchedEffect(Unit) {
        motionLayoutProgress.animateTo(
            1.0f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    delayMillis = 1000,
                    durationMillis = 1000,
                    easing = LinearEasing
                )
            )
        )
    }

    /*Motion(
        title = "Title",
        subTitle = "Subtitle",
        backgroundColor = Color(0xFF214561),
        progress = motionLayoutProgress.value
    )*/
    MotionLayoutPhilippLacknerTheme {
        CollapsingToolbar(
            backgroundImageResId = R.drawable.ic_starwars,
            progress = motionLayoutProgress.value,
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        )
    }
}
