package com.schaefer.architectureplayground.ui.locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schaefer.architectureplayground.model.Location
import com.schaefer.architectureplayground.model.PagedResult
import com.schaefer.architectureplayground.network.ResultWrapper
import com.schaefer.architectureplayground.repository.LocationsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val locationsRepository: LocationsRepository
) : ViewModel() {

    private val _episodesList = MutableLiveData<ResultWrapper<PagedResult<Location>>>()
    val episodesList: LiveData<ResultWrapper<PagedResult<Location>>> = _episodesList

    fun getLocations() {
        viewModelScope.launch(Dispatchers.IO) {
            locationsRepository.getLocations().let {
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