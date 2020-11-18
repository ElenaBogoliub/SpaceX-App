package com.ebogoliub.spacex.ui.rockets

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ebogoliub.features.ui.rockets.R
import com.ebogoliub.features.ui.rockets.databinding.FragmentRocketsBinding
import com.ebogoliub.spacex.ui.rockets.adapter.RocketsAdapter
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.Insetter
import dev.chrisbanes.insetter.Side

@AndroidEntryPoint
class RocketsFragment : Fragment(R.layout.fragment_rockets) {

    private val binding: FragmentRocketsBinding by viewBinding()
    private val viewModel: RocketsViewModel by viewModels()
    private lateinit var adapter: RocketsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner) { viewState ->
            viewState?.let { render(it) }
        }

        initViews()
    }

    private fun initViews() {
        Insetter.builder()
            .applySystemWindowInsetsToPadding(Side.TOP)
            .consumeSystemWindowInsets(Insetter.CONSUME_AUTO)
            .applyToView(binding.root)

        adapter = RocketsAdapter(requireContext())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.itemAnimator?.run {
            removeDuration = 300 * 60 / 100
            addDuration = 300
        }
    }

    private fun render(state: RocketsState) {
        with(state) {
            adapter.submitList(items)
            binding.empty.isVisible = emptyState != null
            binding.empty.state = emptyState
            binding.progress.isVisible = isLoading
        }
    }
}