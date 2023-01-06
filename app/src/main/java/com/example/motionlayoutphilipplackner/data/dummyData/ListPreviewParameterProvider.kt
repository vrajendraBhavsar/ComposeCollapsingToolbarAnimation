package com.example.motionlayoutphilipplackner.data.dummyData

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.motionlayoutphilipplackner.R
import com.example.motionlayoutphilipplackner.data.model.Item

class ListPreviewParameterProvider : PreviewParameterProvider<List<Item>> {
    override val values = sequenceOf(
        listOf(
            Item("The Godfather", itemImage = R.drawable.ic_darth_vader),
            Item("The Shawshank Redemption",itemImage =  R.drawable.ic_darth_vader),
            Item("Schindler's List",itemImage =  R.drawable.ic_darth_vader),
            Item("Casablanca ",itemImage =  R.drawable.ic_darth_vader),
            Item("Forrest Gump",itemImage =  R.drawable.ic_darth_vader),
            Item("Star Wars",itemImage =  R.drawable.ic_darth_vader),
            Item(" The Lord of the Rings: The Return of the King",itemImage =  R.drawable.ic_darth_vader),
            Item("Jurassic Park",itemImage =  R.drawable.ic_darth_vader),
            Item("The Deer Hunter",itemImage =  R.drawable.ic_darth_vader),
            Item("Bonnie and Clyde",itemImage =  R.drawable.ic_darth_vader),
            Item("The Silence of the Lambs",itemImage =  R.drawable.ic_darth_vader),
            Item("E.T. the Extra-Terrestrial",itemImage =  R.drawable.ic_darth_vader)
        )
    )
}
