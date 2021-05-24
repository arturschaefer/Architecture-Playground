package com.schaefer.architectureplayground.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.schaefer.architectureplayground.model.Character
import com.schaefer.architectureplayground.model.Episode
import com.schaefer.architectureplayground.model.PagedResult
import com.schaefer.architectureplayground.network.ResultWrapper
import com.schaefer.architectureplayground.network.datasource.CharactersPagingSource
import com.schaefer.architectureplayground.network.datasource.EpisodesPagingSource
import com.schaefer.architectureplayground.network.datasource.RickAndMortyApiDataSource
import com.schaefer.architectureplayground.network.safeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val PAGE_SIZE = 20

class EpisodesRepository @Inject constructor(
    private val dataSource: RickAndMortyApiDataSource
) {
    fun getEpisodesPageData(): Flow<PagingData<Episode>> {
        return Pager(PagingConfig(PAGE_SIZE)) {
            EpisodesPagingSource(dataSource)
        }.flow
    }
}