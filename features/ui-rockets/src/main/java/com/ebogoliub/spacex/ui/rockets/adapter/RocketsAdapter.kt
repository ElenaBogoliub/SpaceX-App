package com.ebogoliub.spacex.ui.rockets.adapter

import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ebogoliub.features.ui.rockets.R
import com.ebogoliub.features.ui.rockets.databinding.ItemRocketBinding
import com.ebogoliub.spacex.data.entity.Rocket
import com.ebogoliub.spacex.ui.extentions.dp
import com.ebogoliub.spacex.ui.extentions.getDimenFromRes
import com.ebogoliub.spacex.ui.extentions.screenWidth

class RocketsAdapter(val context: Context) : ListAdapter<Rocket, RocketsViewHolder>(
    object : DiffUtil.ItemCallback<Rocket>() {
        override fun areItemsTheSame(oldItem: Rocket, newItem: Rocket): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Rocket, newItem: Rocket): Boolean =
            oldItem == newItem
    }
) {

    private lateinit var recyclerView: RecyclerView
    private var expandedItem: Long? = null
    private val originalWidth = context.screenWidth - 48.dp
    private val expandedWidth = context.screenWidth - 24.dp
    private val originalHeight: Float = context.getDimenFromRes(R.dimen.rocket_item_original_height)
    private val expandedHeight: Float = context.getDimenFromRes(R.dimen.rocket_item_expanded_height)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketsViewHolder =
        RocketsViewHolder(
            ItemRocketBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )

    override fun onBindViewHolder(holder: RocketsViewHolder, position: Int) {
        val item = getItem(position)
        val model = item.id

        holder.bind(item)

        expandItem(holder, model == expandedItem, animate = false)
        holder.cardContainer.setOnClickListener { expandItemIfNeeded(holder, model) }
    }

    private fun expandItemIfNeeded(holder: RocketsViewHolder, model: Long) {
        when (expandedItem) {
            null -> {
                expandItem(holder, expand = true, animate = true)
                expandedItem = model
            }
            model -> {
                expandItem(holder, expand = false, animate = true)
                expandedItem = null
            }
            else -> {
                val expandedModelPosition = currentList.indexOfFirst { it.id == expandedItem }
                val oldViewHolder =
                    recyclerView.findViewHolderForAdapterPosition(expandedModelPosition) as? RocketsViewHolder
                if (oldViewHolder != null) expandItem(oldViewHolder, expand = false, animate = true)

                expandItem(holder, expand = true, animate = true)
                expandedItem = model
            }
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    private fun expandItem(holder: RocketsViewHolder, expand: Boolean, animate: Boolean) {
        if (animate) {
            val animator = getValueAnimator(
                expand, ANIMATION_DURATION, AccelerateDecelerateInterpolator()
            ) { progress -> setExpandProgress(holder, progress) }

            if (expand) animator.doOnStart { holder.expandView.isVisible = true }
            else animator.doOnEnd { holder.expandView.isVisible = false }

            animator.start()
        } else {

            holder.expandView.isVisible = expand
            setExpandProgress(holder, if (expand) 1f else 0f)
        }
    }

    private fun setExpandProgress(holder: RocketsViewHolder, progress: Float) {
        with(holder) {
            cardContainer.layoutParams.height =
                (originalHeight + (expandedHeight - originalHeight) * progress).toInt()
            cardContainer.layoutParams.width =
                (originalWidth + (expandedWidth - originalWidth) * progress).toInt()
            cardContainer.requestLayout()
            arrow.rotation = 90 * progress
        }
    }

    companion object {
        private const val ANIMATION_DURATION = 300L
    }
}

inline fun getValueAnimator(
    forward: Boolean = true,
    duration: Long,
    interpolator: TimeInterpolator,
    crossinline updateListener: (progress: Float) -> Unit
): ValueAnimator {
    val a =
        if (forward) ValueAnimator.ofFloat(0f, 1f)
        else ValueAnimator.ofFloat(1f, 0f)
    a.addUpdateListener { updateListener(it.animatedValue as Float) }
    a.duration = duration
    a.interpolator = interpolator
    return a
}