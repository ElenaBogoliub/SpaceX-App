package com.ebogoliub.spacex.launches

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ebogoliub.spacex.features.ui.launches.R
import com.ebogoliub.spacex.features.ui.launches.databinding.FragmentLaunchesBinding
import com.ebogoliub.spacex.ui.lists.EndlessScrollListener
import com.ebogoliub.spacex.ui.livedata.SingleLiveEvent
import com.ebogoliub.ui.base.content.getText
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.Insetter
import dev.chrisbanes.insetter.Side


@AndroidEntryPoint
class LaunchesFragment : Fragment(R.layout.fragment_launches) {

    private val binding: FragmentLaunchesBinding by viewBinding()
    private val viewModel: LaunchesViewModel by viewModels()
    private lateinit var adapter: LaunchesAdapter
    private lateinit var onScrollListener: EndlessScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.events.observe(this) { event ->
            event?.let { onEvent(it) }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        viewModel.state.observe(viewLifecycleOwner) { viewState ->
            viewState?.let { render(it) }
        }
    }

    private fun initViews() {
        Insetter.builder()
            .applySystemWindowInsetsToPadding(Side.TOP)
            .consumeSystemWindowInsets(Insetter.CONSUME_AUTO)
            .applyToView(binding.appbar)

        adapter = LaunchesAdapter()
        onScrollListener =
            EndlessScrollListener(LOAD_MORE_THRESHOLD) { viewModel.processAction(LoadMoreAction) }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.addOnScrollListener(onScrollListener)
        binding.refresh.setOnRefreshListener { viewModel.processAction(ReloadAction) }
    }

    private fun render(state: LaunchesState) {
        with(state) {
            adapter.submitList(items)
            binding.empty.isVisible = emptyState != null
            binding.empty.state = emptyState
            binding.progress.isVisible = isLoading
            binding.refresh.isRefreshing = isRefreshing
            onScrollListener.isEnabled = state.hasMore
        }
    }


    private fun onEvent(event: SingleLiveEvent) {
        when (event) {
            is ShowToast -> {
                Toast.makeText(
                    requireContext(), event.message.getText(requireContext()), Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    companion object {
        private const val LOAD_MORE_THRESHOLD = 5
    }
}