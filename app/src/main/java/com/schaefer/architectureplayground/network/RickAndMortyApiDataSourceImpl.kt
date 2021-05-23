package com.schaefer.architectureplayground.network

import com.schaefer.architectureplayground.model.Character
import com.schaefer.architectureplayground.model.Episode
import com.schaefer.architectureplayground.model.Location
import com.schaefer.architectureplayground.model.PagedResult
import javax.inject.Inject

class RickAndMortyApiDataSourceImpl @Inject constructor(
    private val api: RickAndMortyApi
) : RickAndMortyApiDataSource {

    override suspend fun getCharacters(): PagedResult<Character> {
        return api.getCharacters()
    }

    override suspend fun getLocations(): PagedResult<Location> {
        return api.getLocations()
    }

    override suspend fun getEpisodes(): PagedResult<Episode> {
        return api.getEpisodes()
    }

}