package com.whz.base.widgets;

import android.app.Activity;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.whz.base.R;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

/**
 * 登录注册页用到的顶部toast
 * Created by chenhao on 2018/7/24.
 */

public  class MyPopupWindow {


    TextView text;
    private Activity mContext;
    private PopupWindow mPopupWindow;


    public MyPopupWindow(Activity context) {
        mContext = context;
        init();
    }

    private void init() {

        View customView = mContext.getLayoutInflater().inflate(R.layout.view_popup_toast,
                null, false);
        text = customView.findViewById(R.id.text);
        //获取屏幕宽度
        DisplayMetrics dm = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        mPopupWindow = new PopupWindow(customView,
                screenWidth, 200);
        mPopupWindow.setAnimationStyle(R.style.top_int_out_toast_style);
        customView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });


    }


    /**
     * 反射获取mLayoutScreen 设置对象为true,全屏效果，覆盖状态栏
     * @param needFullScreen
     */
    public void fitPopupWindowOverStatusBar(boolean needFullScreen) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                Field mLayoutInScreen = PopupWindow.class.getDeclaredField("mLayoutInScreen");
                mLayoutInScreen.setAccessible(true);
                mLayoutInScreen.set(mPopupWindow, needFullScreen);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void show(String s) {
        if (null != mPopupWindow && !mPopupWindow.isShowing()) {
            text.setText(s);
          Observable.timer(2, TimeUnit.SECONDS).subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Observer<Long>() {
              @Override
              public void onSubscribe(Disposable d) {
                  mPopupWindow.showAtLocation(mContext.getWindow().getDecorView(),
                          Gravity.TOP, 0, 0);
              }

              @Override
              public void onNext(Long aLong) {

              }

              @Override
              public void onError(Throwable e) {

              }

              @Override
              public void onComplete() {
                  mPopupWindow.dismiss();
              }
          });

        }
    }
}
