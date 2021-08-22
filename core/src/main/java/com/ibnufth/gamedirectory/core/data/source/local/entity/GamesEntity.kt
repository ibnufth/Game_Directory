package com.ibnufth.gamedirectory.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class GamesEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "platform")
    val platform: String,

    @ColumnInfo(name = "short_description")
    val shortDescription: String,

    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,

    @ColumnInfo(name = "game_url")
    val gameUrl: String,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @ColumnInfo(name = "genre")
    val genre: String,

    @ColumnInfo(name = "publisher")
    val publisher: String,

    @ColumnInfo(name = "developer")
    val developer: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
)