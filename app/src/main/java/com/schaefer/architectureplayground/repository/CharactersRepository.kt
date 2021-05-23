package com.schaefer.architectureplayground.repository

import com.schaefer.architectureplayground.model.Character
import com.schaefer.architectureplayground.model.PagedResult
import com.schaefer.architectureplayground.network.ResultWrapper
import com.schaefer.architectureplayground.network.RickAndMortyApiDataSource
import com.schaefer.architectureplayground.network.safeApiCall
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val dataSource: RickAndMortyApiDataSource
) {
    suspend fun getCharacters() : ResultWrapper<PagedResult<Character>> {
        return safeApiCall { dataSource.getCharacters() }
    }
}