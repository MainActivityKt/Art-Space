package com.practice.artspace

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ArtWork(
    @DrawableRes val drawableId: Int,
    @StringRes val descriptionId: Int,
    @StringRes val creditId: Int
)
