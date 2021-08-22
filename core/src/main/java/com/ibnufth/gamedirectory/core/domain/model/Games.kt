package com.ibnufth.gamedirectory.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Games(
    var id: Int,

    var title: String,

    var platform: String,

    var shortDescription: String,

    var thumbnail: String,

    var gameUrl: String,

    var releaseDate: String,

    var genre: String,

    var publisher: String,

    var developer: String,

    var favorite: Boolean = false
) : Parcelable