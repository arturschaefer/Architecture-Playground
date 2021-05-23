package com.schaefer.architectureplayground.repository

import com.schaefer.architectureplayground.model.Location
import com.schaefer.architectureplayground.model.PagedResult
import com.schaefer.architectureplayground.network.ResultWrapper
import com.schaefer.architectureplayground.network.RickAndMortyApiDataSource
import com.schaefer.architectureplayground.network.safeApiCall
import javax.inject.Inject

class LocationsRepository @Inject constructor(
    private val dataSource: RickAndMortyApiDataSource
) {
    suspend fun getLocations(): ResultWrapper<PagedResult<Location>> {
        return safeApiCall { dataSource.getLocations() }
    }
}