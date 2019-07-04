package com.whz.base.widgets;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * PackageName: com.example.ken.hokogo.widget
 * Author : jiaqi Ye
 * Date : 2018/7/28
 * Time : 16:59
 */

/**
 * 偶数有效
 */
public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {


    //leftRight为横向间的距离 topBottom为纵向间距离
    private int leftRight;
    private int topBottom;

    public GridSpaceItemDecoration(int leftRight, int topBottom) {
        this.leftRight = leftRight;
        this.topBottom = topBottom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);


            outRect.bottom = topBottom;
            outRect.top = topBottom;
            outRect.left = leftRight;
            outRect.right = leftRight;


    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }


}
