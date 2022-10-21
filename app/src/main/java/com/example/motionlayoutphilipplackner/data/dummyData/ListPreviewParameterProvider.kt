package com.example.motionlayoutphilipplackner.data.dummyData

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.motionlayoutphilipplackner.R
import com.example.motionlayoutphilipplackner.data.model.Item

class ListPreviewParameterProvider : PreviewParameterProvider<List<Item>> {
    override val values = sequenceOf(
        listOf(
            Item("The Godfather", itemImage = R.drawable.ic_profile_pic),
            Item("The Shawshank Redemption",itemImage =  R.drawable.ic_profile_pic),
            Item("Schindler's List",itemImage =  R.drawable.ic_profile_pic),
            Item("Casablanca ",itemImage =  R.drawable.ic_profile_pic),
            Item("Forrest Gump",itemImage =  R.drawable.ic_profile_pic),
            Item("Star Wars",itemImage =  R.drawable.ic_profile_pic),
            Item(" The Lord of the Rings: The Return of the King",itemImage =  R.drawable.ic_profile_pic),
            Item("Jurassic Park",itemImage =  R.drawable.ic_profile_pic),
            Item("The Deer Hunter",itemImage =  R.drawable.ic_profile_pic),
            Item("Bonnie and Clyde",itemImage =  R.drawable.ic_profile_pic),
            Item("The Silence of the Lambs",itemImage =  R.drawable.ic_profile_pic),
            Item("E.T. the Extra-Terrestrial",itemImage =  R.drawable.ic_profile_pic)
        )
    )
}
