package com.example.triolingo_mobile.Units;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GridLayoutManagerWithOffset extends GridLayoutManager {

    private int mOffset;

    public GridLayoutManagerWithOffset(Context context, int spanCount, int offset) {
        super(context, spanCount);
        mOffset = offset;
    }

    public GridLayoutManagerWithOffset(Context context, int spanCount, int orientation, boolean reverseLayout, int offset) {
        super(context, spanCount, orientation, reverseLayout);
        mOffset = offset;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            int row = getPosition(view) / getSpanCount();
            int column = getPosition(view) % getSpanCount();
            int left = column * view.getWidth() - row * mOffset;
            int top = view.getTop();
            int right = left + view.getWidth();
            int bottom = view.getBottom();
            view.layout(left, top, right, bottom);
        }
    }
}