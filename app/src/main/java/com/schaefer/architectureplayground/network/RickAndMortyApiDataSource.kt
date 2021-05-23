package com.schaefer.architectureplayground.network

import com.schaefer.architectureplayground.model.Character
import com.schaefer.architectureplayground.model.Episode
import com.schaefer.architectureplayground.model.Location
import com.schaefer.architectureplayground.model.PagedResult

interface RickAndMortyApiDataSource {
    suspend fun getCharacters(): PagedResult<Character>

    suspend fun getLocations(): PagedResult<Location>

    suspend fun getEpisodes(): PagedResult<Episode>
}