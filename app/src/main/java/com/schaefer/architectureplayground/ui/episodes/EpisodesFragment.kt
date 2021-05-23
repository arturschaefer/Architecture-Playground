package com.schaefer.architectureplayground.ui.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.schaefer.architectureplayground.R
import com.schaefer.architectureplayground.databinding.FragmentEpisodesBinding
import com.schaefer.architectureplayground.network.ResultWrapper
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import viewLifecycle

@AndroidEntryPoint
class EpisodesFragment : Fragment(R.layout.fragment_episodes) {

    private var binding: FragmentEpisodesBinding by viewLifecycle()
    private val viewModel: EpisodesViewModel by viewModels()
    private val episodesAdapter: EpisodesAdapter by lazy { EpisodesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.episodesList.observe(viewLifecycleOwner) {
            when (it) {
                is ResultWrapper.GenericError -> Timber.d("GenericError")
                ResultWrapper.NetworkError -> Timber.d("NetworkError")
                is ResultWrapper.Success -> episodesAdapter.episodeList = it.value.results
            }
        }
    }

    private fun setupView() {
        binding.rvEpisodes.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = episodesAdapter
        }

        viewModel.getEpisodes()
    }
}