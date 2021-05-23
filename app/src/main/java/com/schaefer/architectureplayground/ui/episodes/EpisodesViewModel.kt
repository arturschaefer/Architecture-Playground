package com.schaefer.architectureplayground.ui.episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schaefer.architectureplayground.model.Episode
import com.schaefer.architectureplayground.model.PagedResult
import com.schaefer.architectureplayground.network.*
import com.schaefer.architectureplayground.repository.EpisodesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val episodesRepository: EpisodesRepository
) : ViewModel() {

    private val _episodesList = MutableLiveData<ResultWrapper<PagedResult<Episode>>>()
    val episodesList: LiveData<ResultWrapper<PagedResult<Episode>>> = _episodesList

    fun getEpisodes() {
        viewModelScope.launch(Dispatchers.IO) {
            episodesRepository.getEpisodes().let {
                when (it) {
                    is ResultWrapper.GenericError -> Timber.d("ApiEmptyResponse")
                    ResultWrapper.NetworkError -> Timber.d("NetworkError")
                    is ResultWrapper.Success -> {
                        Timber.d(it.value.toString())
                        _episodesList.postValue(ResultWrapper.Success(it.value))
                    }
                }
            }
        }
    }
}