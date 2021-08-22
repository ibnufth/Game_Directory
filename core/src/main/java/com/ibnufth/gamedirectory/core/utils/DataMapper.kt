package com.ibnufth.gamedirectory.core.utils

import com.ibnufth.gamedirectory.core.data.source.local.entity.GamesEntity
import com.ibnufth.gamedirectory.core.data.source.remote.response.GamesResponse
import com.ibnufth.gamedirectory.core.domain.model.Games

object DataMapper {

    fun mapResponseToEntities(game: List<GamesResponse>): List<GamesEntity> {
        val gameEntity = ArrayList<GamesEntity>()
        game.map {
            val games = GamesEntity(
                it.id,
                it.title,
                it.platform,
                it.shortDescription,
                it.thumbnail,
                it.gameUrl,
                it.releaseDate,
                it.genre,
                it.publisher,
                it.developer,
                false
            )
            gameEntity.add(games)
        }
        return gameEntity
    }

    fun mapEntitiesToDomain(game: List<GamesEntity>): List<Games> =
        game.map {
            Games(
                it.id,
                it.title,
                it.platform,
                it.shortDescription,
                it.thumbnail,
                it.gameUrl,
                it.releaseDate,
                it.genre,
                it.publisher,
                it.developer,
                it.favorite
            )
        }

    fun mapDomainToEntity(game: Games) =
        GamesEntity(
            game.id,
            game.title,
            game.platform,
            game.shortDescription,
            game.thumbnail,
            game.gameUrl,
            game.releaseDate,
            game.genre,
            game.publisher,
            game.developer,
            game.favorite
        )

    fun mapEntityToDomain(game: GamesEntity): Games =
        Games(
            game.id,
            game.title,
            game.platform,
            game.shortDescription,
            game.thumbnail,
            game.gameUrl,
            game.releaseDate,
            game.genre,
            game.publisher,
            game.developer,
            game.favorite
        )

}