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
        Item("Goomba", itemImage = R.drawable.ic_goomba),
        Item("Boomareng", itemImage = R.drawable.ic_boomareng),
        Item("Hammer Bro", itemImage = R.drawable.ic_hammer_bro),
        Item("Princess Daisy", itemImage = R.drawable.ic_princess_daisy),
        Item("Luigi", itemImage = R.drawable.ic_luigi),
        Item("Paratroop", itemImage = R.drawable.ic_koopa_paratroopa),
        Item("Toadette", itemImage = R.drawable.ic_toadette),
        Item("Waluigi", itemImage = R.drawable.ic_waluigi),
        Item("Wario", itemImage = R.drawable.ic_wario),
        Item("Wii", itemImage = R.drawable.ic_wii),
        Item("Wii Dragon", itemImage = R.drawable.ic_wii_dragon),
        Item("Yoshi", itemImage = R.drawable.ic_yoshi)
    )
}
