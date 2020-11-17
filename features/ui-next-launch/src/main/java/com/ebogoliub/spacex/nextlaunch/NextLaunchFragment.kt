package com.ebogoliub.spacex.nextlaunch

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ebogoliub.spacex.features.ui.next_launch.R
import com.ebogoliub.spacex.features.ui.next_launch.databinding.NextLaunchFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import com.ebogoliub.spacex.data.entities.Launch
import com.ebogoliub.spacex.ui.animation.fadeInWithTranslationY
import com.ebogoliub.spacex.ui.extentions.formatDate

@AndroidEntryPoint
class NextLaunchFragment : Fragment(R.layout.next_launch_fragment) {

    private val binding: NextLaunchFragmentBinding by viewBinding()
    private val viewModel: NextLaunchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) { viewState ->
            viewState?.let { render(it) }
        }
    }

    private fun render(state: NextLaunchState) {
        with(state) {
            binding.progress.isVisible = isLoading
            binding.empty.isVisible = emptyState != null
            binding.empty.state = emptyState
            binding.content.isVisible = launch != null
            if (launch != null) {
                renderLaunch(launch)
                binding.content.takeIf { !it.isVisible }?.fadeInWithTranslationY()
            }
        }
    }

    private fun renderLaunch(launch: Launch) {
        binding.timerContainer.counter.setupCountdown(launch)
        binding.rocketContainer.rocket.text = getString(
            R.string.next_launch_rocket_name,
            launch.rocket.rocketName,
            launch.rocket.rocketType
        )
        binding.detailsContainer.launchSide.text = launch.launchSite?.siteName
            ?: getString(R.string.common_unknown)
        binding.detailsContainer.launchDate.text =
            requireContext().formatDate(
                launch.launchDateUtc,
                launch.tentativeMaxPrecision.dateFormat
            )
        binding.detailsContainer.launchMission.text = launch.missionName
    }
}