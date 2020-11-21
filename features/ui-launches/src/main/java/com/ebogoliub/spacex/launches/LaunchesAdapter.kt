package com.ebogoliub.spacex.launches

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.ebogoliub.spacex.data.entity.Launch
import com.ebogoliub.spacex.data.entity.missionPatch
import com.ebogoliub.spacex.features.ui.launches.R
import com.ebogoliub.spacex.features.ui.launches.databinding.ItemLaunchBinding
import com.ebogoliub.spacex.ui.extentions.formatDate
import com.ebogoliub.spacex.ui.extentions.getColorFromRes
import org.threeten.bp.Duration
import org.threeten.bp.ZonedDateTime

class LaunchesAdapter : ListAdapter<Launch, LaunchesAdapter.LaunchViewHolder>(
    object : DiffUtil.ItemCallback<Launch>() {
        override fun areItemsTheSame(oldItem: Launch, newItem: Launch): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Launch, newItem: Launch): Boolean =
            oldItem.launchSuccess == newItem.launchSuccess
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder =
        LayoutInflater.from(parent.context)
            .let { ItemLaunchBinding.inflate(it, parent, false) }
            .let { LaunchViewHolder(it) }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) =
        holder.bind(getItem(position))

    class LaunchViewHolder(
        private val binding: ItemLaunchBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        private val greenColor = itemView.context.getColorFromRes(R.color.green_1)
        private val yellowColor = itemView.context.getColorFromRes(R.color.yellow)

        fun bind(launch: Launch) {
            binding.launchName.text = launch.missionName
            binding.launchDate.text = itemView.context.formatDate(
                launch.launchDateUtc,
                launch.tentativeMaxPrecision.dateFormat
            )
            binding.launchSite.text = launch.launchSite?.siteName
                ?: itemView.context.getString(R.string.common_unknown)
            binding.logo.apply {
                load(launch.missionPatch(true)) {
                    crossfade(true)
                    error(R.drawable.launch_shape_circle)
                    placeholder(R.drawable.launch_shape_circle)
                    fallback(R.drawable.launch_shape_circle)
                    transformations(CircleCropTransformation())
                }
            }
            bindLaunchStatus(binding, launch)
        }

        private fun bindLaunchStatus(binding: ItemLaunchBinding, launch: Launch) {
            when {
                launch.launchSuccess == true -> {
                    binding.launchStatus.text =
                        itemView.context.getString(R.string.common_launch_successful)
                    binding.launchStatus.setTextColor(greenColor)
                }
                Duration.between(ZonedDateTime.now(), launch.launchDateUtc).isNegative -> {
                    binding.launchStatus.text =
                        itemView.context.getString(R.string.common_launch_finish)
                    binding.launchStatus.setTextColor(greenColor)
                }
                else -> {
                    binding.launchStatus.text =
                        itemView.context.getString(R.string.common_launch_upcoming)
                    binding.launchStatus.setTextColor(yellowColor)
                }
            }
        }
    }
}
