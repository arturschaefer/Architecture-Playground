package com.schaefer.architectureplayground.network

import com.schaefer.architectureplayground.model.Character
import com.schaefer.architectureplayground.model.Episode
import com.schaefer.architectureplayground.model.Location
import com.schaefer.architectureplayground.model.PagedResult
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): PagedResult<Character>

    @GET("location")
    suspend fun getLocations(@Query("page") page: Int): PagedResult<Location>

    @GET("episode")
    suspend fun getEpisodes(@Query("page") page: Int): PagedResult<Episode>
}