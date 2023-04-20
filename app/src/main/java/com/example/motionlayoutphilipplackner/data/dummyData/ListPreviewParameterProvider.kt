package com.example.motionlayoutphilipplackner.data.dummyData

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.motionlayoutphilipplackner.R
import com.example.motionlayoutphilipplackner.data.model.Item

class ListPreviewParameterProvider : PreviewParameterProvider<List<Item>> {
    override val values = sequenceOf(
        populateList()
    )
}

fun populateList(): List<Item> {
    return listOf(
        Item("The Godfather", itemImage = R.drawable.ic_vacatiob_minion),
        Item("The Shawshank Redemption", itemImage = R.drawable.ic_captain_minion),
        Item("Schindler's List", itemImage = R.drawable.ic_hawai_minion),
        Item("Casablanca ", itemImage = R.drawable.ic_prestone_minion),
        Item("Forrest Gump", itemImage = R.drawable.ic_vacatiob_minion),
        Item("Star Wars", itemImage = R.drawable.ic_cabana_banana_minion),
        Item("The Lord of the Rings", itemImage = R.drawable.ic_hawai_minion),
        Item("Jurassic Park", itemImage = R.drawable.ic_captain_minion),
        Item("The Deer Hunter", itemImage = R.drawable.ic_prestone_minion),
        Item("Bonnie and Clyde", itemImage = R.drawable.ic_hawai_minion),
        Item("The Silence of the Lambs", itemImage = R.drawable.ic_cabana_banana_minion),
        Item("E.T. the Extra-Terrestrial", itemImage = R.drawable.ic_vacatiob_minion)
    )
}
