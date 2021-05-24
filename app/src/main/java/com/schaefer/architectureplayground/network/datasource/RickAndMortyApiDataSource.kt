package com.schaefer.architectureplayground.network.datasource

import com.schaefer.architectureplayground.model.Character
import com.schaefer.architectureplayground.model.Episode
import com.schaefer.architectureplayground.model.Location
import com.schaefer.architectureplayground.model.PagedResult

interface RickAndMortyApiDataSource {
    suspend fun getCharacters(page: Int): PagedResult<Character>

    suspend fun getLocations(page: Int): PagedResult<Location>

    suspend fun getEpisodes(page: Int): PagedResult<Episode>
}