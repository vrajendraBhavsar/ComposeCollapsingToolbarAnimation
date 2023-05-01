package com.example.collapsingToolbarWithMotionCompose.ui.management

abstract class DynamicOffsetScrollFlagState(
    heightRange: IntRange,
    scrollValue: Int
) : ScrollFlagState(heightRange, scrollValue) {

    protected abstract var scrollOffset: Float

}