package com.schaefer.architectureplayground.ui.episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.schaefer.architectureplayground.model.Episode
import com.schaefer.architectureplayground.repository.EpisodesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val episodesRepository: EpisodesRepository
) : ViewModel() {

    private val _episodesList = MutableLiveData<PagingData<Episode>>()
    val episodesList: LiveData<PagingData<Episode>> = _episodesList

    fun getEpisodes() {
        viewModelScope.launch(Dispatchers.IO) {
            episodesRepository.getEpisodesPageData().cachedIn(this).collectLatest {
                _episodesList.postValue(it)
            }
        }
    }
}