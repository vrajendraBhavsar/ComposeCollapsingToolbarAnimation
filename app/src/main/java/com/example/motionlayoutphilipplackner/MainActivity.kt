package com.example.motionlayoutphilipplackner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.example.motionlayoutphilipplackner.ui.theme.MotionLayoutPhilippLacknerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MotionLayoutPhilippLacknerTheme {
                Column {
                    var progress by remember {
                        mutableStateOf(0f)
                    }
                    ProfileHeader(progress = progress)
                    Slider(value = progress, onValueChange = {
                        progress = it
                    }, modifier = Modifier.padding(horizontal = 32.dp))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MotionLayoutPhilippLacknerTheme {
        Greeting("Android")
    }
}

@OptIn(ExperimentalMotionApi::class)
@Composable
fun ProfileHeader(progress: Float) {
    val context = LocalContext.current  //to get the raw file, we need context.
    val motionScene = remember {    // To include raw file, the JSON5 script file
        context.resources.openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()   //readBytes -> cuz we want motionScene in String
    }
    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier.fillMaxWidth()
    ) {
        val propertiesOfProfilePic = motionProperties(id = "profile_pic")   //motionProperties -> to get the custom properties
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.DarkGray)
                .layoutId("box")    // Background - Keeping layoutId same as what we have used in motion_scene.json5 file.
        )
        Image(
            painter = painterResource(id = R.drawable.profile_pic), contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .border(
                    width = 2.dp,
//                    color = Color.Green,
                    color = propertiesOfProfilePic.value.color(name = "color"),
                    shape = CircleShape
                )
                .layoutId("profile_pic")    // Same ID that we have used in motion_scene.json5
        )
        Text(
            text = "Socrates",
            fontSize = 24.sp,
            modifier = Modifier.layoutId("user_name"),
            color = propertiesOfProfilePic.value.color(name = "color")
        )
    }
}