package com.ebogoliub.spacex.ui.lists

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EndlessScrollListener(
    var loadMoreThreshold: Int,
    private val onLoadMore: OnLoadMoreListener
) : RecyclerView.OnScrollListener() {
    private var scrollStateReset = true

    var isEnabled: Boolean = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (!isEnabled) {
            return
        }
        val layoutManager = recyclerView.layoutManager ?: return
        val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
        val remainingItemCount = layoutManager.itemCount - lastVisibleItem

        if (scrollStateReset && remainingItemCount <= loadMoreThreshold) {
            scrollStateReset = false
            recyclerView.post {
                if (isEnabled) {
                    onLoadMore()
                }
            }
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE || newState == RecyclerView.SCROLL_STATE_DRAGGING) {
            scrollStateReset = true
        }
    }
}

fun RecyclerView.LayoutManager.findLastVisibleItemPosition(): Int = when (this) {
    is LinearLayoutManager -> findLastVisibleItemPosition()
    else -> throw UnsupportedOperationException("Unsupported layout manager $javaClass")
}

typealias OnLoadMoreListener = () -> Unit
