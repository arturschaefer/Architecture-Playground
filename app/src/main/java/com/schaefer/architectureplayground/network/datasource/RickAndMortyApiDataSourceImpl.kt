package com.schaefer.architectureplayground.network.datasource

import com.schaefer.architectureplayground.model.Character
import com.schaefer.architectureplayground.model.Episode
import com.schaefer.architectureplayground.model.Location
import com.schaefer.architectureplayground.model.PagedResult
import com.schaefer.architectureplayground.network.RickAndMortyApi
import javax.inject.Inject

class RickAndMortyApiDataSourceImpl @Inject constructor(
    private val api: RickAndMortyApi
) : RickAndMortyApiDataSource {

    override suspend fun getCharacters(page: Int): PagedResult<Character> {
        return api.getCharacters(page)
    }

    override suspend fun getLocations(page: Int): PagedResult<Location> {
        return api.getLocations(page)
    }

    override suspend fun getEpisodes(page: Int): PagedResult<Episode> {
        return api.getEpisodes(page)
    }

}