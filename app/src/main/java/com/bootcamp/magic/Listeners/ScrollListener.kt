package com.bootcamp.magic.Listeners

import android.util.Log
import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ScrollListener(
    val layoutManager: LinearLayoutManager,
    val loadMore: ((visibleItemCount: Int, totalItemCount: Int, firstVisibleItemPosition: Int) -> Unit)
) : RecyclerView.OnScrollListener() {

    var isScrolling = false
    private var currentItems: Int = 0
    private var totalItems: Int = 0
    private var scrollOutItems: Int = 0


    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
            isScrolling = true

        }
        super.onScrollStateChanged(recyclerView, newState)
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val findFirstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        loadMore(visibleItemCount,totalItemCount,findFirstVisibleItemPosition)
    }


}