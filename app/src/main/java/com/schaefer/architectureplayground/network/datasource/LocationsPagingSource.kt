package com.schaefer.architectureplayground.network.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.schaefer.architectureplayground.model.Location
import com.schaefer.architectureplayground.network.ResultWrapper
import com.schaefer.architectureplayground.network.safeApiCall
import javax.inject.Inject

class LocationsPagingSource @Inject constructor(
    private val dataSource: RickAndMortyApiDataSource
) : PagingSource<Int, Location>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Location> {
        val nextPageNumber = params.key ?: 1
        return safeApiCall { dataSource.getLocations(nextPageNumber) }.let {
            when (it) {
                is ResultWrapper.Success -> LoadResult.Page(
                    data = it.value.results,
                    prevKey = null,
                    nextKey = nextPageNumber + 1
                )
                is ResultWrapper.GenericError -> LoadResult.Error(
                    Throwable(
                        message = it.error?.errorDescription
                    )
                )
                ResultWrapper.NetworkError -> LoadResult.Error(
                    Throwable(
                        message = "Network exception"
                    )
                )
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Location>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}