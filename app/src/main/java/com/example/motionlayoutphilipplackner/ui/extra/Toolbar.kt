package com.example.motionlayoutphilipplackner.ui.extra

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.example.motionlayoutphilipplackner.R

@OptIn(ExperimentalMotionApi::class)
@Composable
fun Toolbar(
    progress: Float,
    modifier: Modifier? = null) {
    val context = LocalContext.current  //to get the raw file, we need context.
    Log.d("TAG", "ProfileHeader: progress => $progress")
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
        val propertiesOfProfilePic = motionProperties(id = "back_arrow_img")   //motionProperties -> to get the custom properties
        val propertiesToolbarText = motionProperties(id = "toolbar_text")
        val propertiesContentText = motionProperties(id = "content_text")
        val propertiesToolbarImage = motionProperties(id = "toolbar_img")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.DarkGray)
                .layoutId("box")    // Background - Keeping layoutId same as what we have used in motion_scene.json5 file.
        )
        /*Image(  //background of back arrow
            painter = painterResource(id = R.drawable.profile_pic), contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .border(
                    width = 2.dp,
//                    color = Color.Green,
                    color = propertiesOfProfilePic.value.color(name = "color"),
                    shape = CircleShape
                )
                .layoutId("back_arrow_img")    // Same ID that we have used in motion_scene.json5
        )*/
        Image(  //back arrow
            painter = painterResource(id = R.drawable.ic_back_arrow), contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                /*.border(
                    width = 2.dp,
//                    color = Color.Green,
                    color = propertiesOfProfilePic.value.color(name = "color"),
                    shape = CircleShape
                )*/
                .layoutId("back_arrow_img")    // Same ID that we have used in motion_scene.json5
        )
        Text(   //ToolbarText
            text = "Mi Secure Mode",
            fontSize = 24.sp,
            modifier = Modifier.layoutId("toolbar_text"),
            color = propertiesToolbarText.value.color("color")
        )
        Icon(  //Shield icon
            painter = painterResource(id = R.drawable.ic_shield), contentDescription = null,
            tint = propertiesToolbarImage.value.color("color"),
            modifier = Modifier
                /*.clip(CircleShape)
                .border(
                    width = 2.dp,
//                    color = Color.Green,
                    color = propertiesOfProfilePic.value.color(name = "color"),
                    shape = CircleShape
                )*/
                .layoutId("toolbar_img")    // Same ID that we have used in motion_scene.json5

        )
        Text(   // Content color
            text = "Protection for events involves providing security at meetings, celebrations, and political gatherings. The guards are stationing themselves at the entrances, exits and among the crowds to ensure that no trouble occurs.",
            fontSize = 16.sp,
            modifier = Modifier.layoutId("content_text").padding(horizontal = 16.dp),
            color = propertiesContentText.value.color(name = "color"),
            textAlign = TextAlign.Center,
        )
        Button(
            onClick = {
                Toast.makeText(
                    context,
                    "Sovereignty over any foreign land is insecure.",
                    Toast.LENGTH_LONG
                ).show()
            },
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.layoutId("launch_now_button"),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Red,
                contentColor = Color.White
            )
        ) {
            Text(text = "Launch Now", color = Color.White)
        }
    }
}