package com.example.imagecaching.presentation

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GridItemDecorator(private val padding: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        when (val layoutManager = parent.layoutManager) {
            is GridLayoutManager -> {
                val layoutParams = view.layoutParams as GridLayoutManager.LayoutParams
                val isAxisEndSpan = (layoutParams.spanIndex + 1) % layoutManager.spanCount == 0

                val adapterPos = parent.getChildAdapterPosition(view)
                val adapterSize = parent.adapter?.itemCount ?: 0
                val isCrossAxisEndSpan = adapterPos > (adapterSize - layoutManager.spanCount)

                outRect.left = padding
                outRect.right = if (isAxisEndSpan) padding else 0
                outRect.top = padding
                outRect.bottom = if (isCrossAxisEndSpan) padding else 0
            }
        }
    }
}