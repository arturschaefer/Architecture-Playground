package com.schaefer.architectureplayground.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.schaefer.architectureplayground.model.Character
import com.schaefer.architectureplayground.repository.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    private val _charactersList = MutableLiveData<PagingData<Character>>()
    val charactersList: LiveData<PagingData<Character>> = _charactersList

    fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            charactersRepository.getCharactersPageData().cachedIn(this).collectLatest {
                _charactersList.postValue(it)
            }
        }
    }
}