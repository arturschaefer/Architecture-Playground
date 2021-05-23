package com.schaefer.architectureplayground.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.schaefer.architectureplayground.R
import com.schaefer.architectureplayground.databinding.FragmentCharactersBinding
import com.schaefer.architectureplayground.network.ResultWrapper
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import viewLifecycle

@AndroidEntryPoint
class CharactersFragment : Fragment(R.layout.fragment_characters) {

    private var binding: FragmentCharactersBinding by viewLifecycle()
    private val viewModel: CharactersViewModel by viewModels()
    private val charactersAdapter: CharactersAdapter by lazy {
        CharactersAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.charactersList.observe(viewLifecycleOwner) {
            when (it) {
                is ResultWrapper.GenericError -> Timber.d("GenericError")
                ResultWrapper.NetworkError -> Timber.d("NetworkError")
                is ResultWrapper.Success -> charactersAdapter.characterList = it.value.results
            }
        }
    }

    private fun setupView() {
        binding.rvCharacters.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = charactersAdapter
        }

        viewModel.getCharacters()
    }
}