package com.schaefer.architectureplayground.ui.locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.schaefer.architectureplayground.model.Location
import com.schaefer.architectureplayground.repository.LocationsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val locationsRepository: LocationsRepository
) : ViewModel() {

    private val _locationsList = MutableLiveData<PagingData<Location>>()
    val locationsList: LiveData<PagingData<Location>> = _locationsList

    fun getLocations() {
        viewModelScope.launch(Dispatchers.IO) {
            locationsRepository.getLocations().cachedIn(this).collectLatest {
                _locationsList.postValue(it)
            }
        }
    }
}