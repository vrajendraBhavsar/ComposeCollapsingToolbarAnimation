package com.example.collapsingToolbarWithMotionCompose.data.dummyData

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.collapsingToolbarWithMotionCompose.R
import com.example.collapsingToolbarWithMotionCompose.data.model.Item

class ListPreviewParameterProvider : PreviewParameterProvider<List<Item>> {
    override val values = sequenceOf(
        populateList()
    )
}

fun populateList(): List<Item> {
    return listOf(
        Item("Spike Top", itemImage = R.drawable.ic_spike_top),
        Item("Blooper", itemImage = R.drawable.ic_blooper),
        Item("Yoshi", itemImage = R.drawable.ic_yoshi),
        Item("Luigi", itemImage = R.drawable.ic_luigi),
        Item("Princess Daisy", itemImage = R.drawable.ic_princess_daisy),
        Item("Paratroop", itemImage = R.drawable.ic_koopa_paratroopa),
        Item("Toadette", itemImage = R.drawable.ic_toadette),
        Item("Ice Mario", itemImage = R.drawable.ic_ice_mario),
        Item("Bullet", itemImage = R.drawable.ic_bullet),
        Item("Piranha Flower", itemImage = R.drawable.ic_piranha_flower),
        Item("Wii", itemImage = R.drawable.ic_wii),
        Item("Wii Dragon", itemImage = R.drawable.ic_wii_dragon)
    )
}
