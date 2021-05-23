package com.schaefer.architectureplayground.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schaefer.architectureplayground.model.Character
import com.schaefer.architectureplayground.model.PagedResult
import com.schaefer.architectureplayground.network.*
import com.schaefer.architectureplayground.repository.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    private val _charactersList = MutableLiveData<ResultWrapper<PagedResult<Character>>>()
    val charactersList: LiveData<ResultWrapper<PagedResult<Character>>> = _charactersList

    fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            charactersRepository.getCharacters().let {
                when (it) {
                    is ResultWrapper.GenericError -> Timber.d("ApiEmptyResponse")
                    ResultWrapper.NetworkError -> Timber.d("NetworkError")
                    is ResultWrapper.Success -> {
                        Timber.d(it.value.toString())
                        _charactersList.postValue(ResultWrapper.Success(it.value))
                    }
                }
            }
        }
    }
}