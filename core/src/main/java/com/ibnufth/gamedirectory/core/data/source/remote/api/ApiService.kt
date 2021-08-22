package com.ibnufth.gamedirectory.core.data.source.remote.api

import com.ibnufth.gamedirectory.core.data.source.remote.response.GamesResponse
import retrofit2.http.GET

interface ApiService {
    @GET("games?category=battle-royale&?platform=pc&sort-by=release-date")
    suspend fun getGames(): List<GamesResponse>
}