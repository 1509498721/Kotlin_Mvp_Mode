package com.whz.base.utils;

import android.content.Context;
import android.util.TypedValue;



/**
 * @Description: Sp2PxUtil
 * Created by whz  on 2019-06-27
 */

public class Sp2PxUtil {

    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }
}
