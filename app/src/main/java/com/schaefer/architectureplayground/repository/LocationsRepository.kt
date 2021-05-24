package com.schaefer.architectureplayground.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.schaefer.architectureplayground.model.Location
import com.schaefer.architectureplayground.network.datasource.LocationsPagingSource
import com.schaefer.architectureplayground.network.datasource.RickAndMortyApiDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val PAGE_SIZE = 20

class LocationsRepository @Inject constructor(
    private val dataSource: RickAndMortyApiDataSource
) {
    fun getLocations(): Flow<PagingData<Location>> {
        return Pager(PagingConfig(PAGE_SIZE)) {
            LocationsPagingSource(dataSource)
        }.flow
    }
}