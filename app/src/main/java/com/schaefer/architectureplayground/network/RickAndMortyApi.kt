package com.schaefer.architectureplayground.network

import com.schaefer.architectureplayground.model.Character
import com.schaefer.architectureplayground.model.Episode
import com.schaefer.architectureplayground.model.Location
import com.schaefer.architectureplayground.model.PagedResult
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(): PagedResult<Character>

    @GET("location")
    suspend fun getLocations(): PagedResult<Location>

    @GET("episode")
    suspend fun getEpisodes(): PagedResult<Episode>
}