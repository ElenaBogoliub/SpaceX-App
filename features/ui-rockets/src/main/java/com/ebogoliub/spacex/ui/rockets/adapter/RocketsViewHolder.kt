package com.ebogoliub.spacex.ui.rockets.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ebogoliub.features.ui.rockets.R
import com.ebogoliub.features.ui.rockets.databinding.ItemRocketBinding
import com.ebogoliub.spacex.data.entity.Rocket
import com.ebogoliub.spacex.ui.extentions.bindColor
import com.ebogoliub.spacex.ui.extentions.bindDrawable

class RocketsViewHolder(
    private val binding: ItemRocketBinding,
) : RecyclerView.ViewHolder(binding.root) {

    private val greyColor by bindColor(itemView.context, R.color.grey_1)
    private val greenColor by bindColor(itemView.context, R.color.green_1)
    private val activeBackground by bindDrawable(itemView.context, R.drawable.rocket_status_active_background)
    private val inactiveBackground by bindDrawable(itemView.context, R.drawable.rocket_status_inactive_background)

    val expandView: View = binding.expandView
    val arrow: View = binding.arrow
    val cardContainer: View = binding.cardContainer

    @SuppressLint("SetTextI18n")
    fun bind(rocket: Rocket) {
        binding.title.text = rocket.rocketName
        with(binding.subtitle) {
            if(rocket.active) {
                text = itemView.context.getString(R.string.rocket_active)
                background = activeBackground
                setTextColor(greenColor)
            } else {
                text = itemView.context.getString(R.string.rocket_inactive)
                background = inactiveBackground
                setTextColor(greyColor)
            }
        }
        binding.company.text = "${rocket.company} (${rocket.country})"
        binding.firstFlight.text = rocket.firstFlight
        binding.successRatePercentage.text = "${rocket.successRatePercentage} %"
        binding.costPerLaunch.text = "${rocket.costPerLaunch} $"
    }
}