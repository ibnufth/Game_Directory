package com.ibnufth.gamedirectory.core.data.source.remote

import android.util.Log
import com.ibnufth.gamedirectory.core.data.source.remote.api.ApiResponse
import com.ibnufth.gamedirectory.core.data.source.remote.api.ApiService
import com.ibnufth.gamedirectory.core.data.source.remote.response.GamesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getAllGames(): Flow<ApiResponse<List<GamesResponse>>> {
        return flow {
            try {
                val response = apiService.getGames()
                Log.d(TAG, "getAllGames data array: $response")
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, "getAllGames: $e")
            }
        }.flowOn(Dispatchers.IO)
    }

    companion object {
        const val TAG = "RemoteDataSource"
    }
}