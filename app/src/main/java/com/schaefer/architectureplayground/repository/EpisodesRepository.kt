package com.schaefer.architectureplayground.repository

import com.schaefer.architectureplayground.model.Episode
import com.schaefer.architectureplayground.model.PagedResult
import com.schaefer.architectureplayground.network.ResultWrapper
import com.schaefer.architectureplayground.network.RickAndMortyApiDataSource
import com.schaefer.architectureplayground.network.safeApiCall
import javax.inject.Inject

class EpisodesRepository @Inject constructor(
    private val dataSource: RickAndMortyApiDataSource
) {
    suspend fun getEpisodes(): ResultWrapper<PagedResult<Episode>> {
        return safeApiCall { dataSource.getEpisodes() }
    }
}