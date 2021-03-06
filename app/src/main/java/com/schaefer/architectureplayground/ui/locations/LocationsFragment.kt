package com.schaefer.architectureplayground.ui.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.schaefer.architectureplayground.R
import com.schaefer.architectureplayground.databinding.FragmentLocationsBinding
import com.schaefer.architectureplayground.network.ResultWrapper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import viewLifecycle

@AndroidEntryPoint
class LocationsFragment : Fragment(R.layout.fragment_locations) {

    private var binding: FragmentLocationsBinding by viewLifecycle()
    private val viewModel: LocationsViewModel by viewModels()
    private val locationsAdapter: LocationsAdapter by lazy {
        LocationsAdapter(
            LocationComparator
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.locationsList.observe(viewLifecycleOwner) {
            viewLifecycleOwner.lifecycleScope.launch {
                locationsAdapter.submitData(it)
            }
        }
    }

    private fun setupView() {
        binding.rvLocations.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = locationsAdapter
        }

        viewModel.getLocations()
    }
}