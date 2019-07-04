package com.whz.main.widgets;

import android.content.Context;
import android.support.annotation.AnimRes;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.whz.main.R;

import java.util.List;

/**
 * PackageName: com.example.ewhale.hokogo.widget
 * Author : jiaqi Ye
 * Date : 2018/8/29
 * Time : 16:37
 */
public class TextViewBanner extends RelativeLayout {

    private ViewFlipper mViewFlipper;
    private int mInterval = 3000;
    private int animDuration = 1500;
    @AnimRes
    private int inAnimResId = R.anim.anim_right_in;
    @AnimRes
    private int outAnimResId = R.anim.anim_left_out;
    private List<String> mDatas;
    private boolean isStarted;
    private boolean isDetachedFromWindow;
    private int mTextColor = 0xff333333;
    private int mTextSize = 18;
    private int mGravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
    private TextBannerItemClickListener mListener;

    public TextViewBanner(Context context) {
        this(context,null);
    }

    public TextViewBanner(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public TextViewBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        mViewFlipper = new ViewFlipper(getContext());
        mViewFlipper.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        addView(mViewFlipper);
        mViewFlipper.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mViewFlipper.getDisplayedChild();//当前显示的子视图的索引位置
                if (mListener!=null){
                    mListener.onItemClick(mDatas.get(position),position);
                }
            }
        });
    }

    public void setTextBannerItemClickListener(TextBannerItemClickListener mListener) {
        this.mListener = mListener;
    }

    /**设置数据集合*/
    public void setData(List<String> data){
        this.mDatas = data;
        if (mDatas.size()>0){
            mViewFlipper.removeAllViews();
            for (int i = 0; i < mDatas.size(); i++) {
                TextView textView = new TextView(getContext());
                textView.setText(mDatas.get(i));
                //任意设置你的文字样式，在这里
                textView.setSingleLine(true);
                textView.setTextColor(mTextColor);
                textView.setTextSize(mTextSize);
                textView.setGravity(mGravity);
                mViewFlipper.addView(textView,i);//添加子view,并标识子view位置
            }
        }

    }


    public interface TextBannerItemClickListener {
        void onItemClick(String data, int position);
    }


    /**
     * 设置进入动画和离开动画
     *
     * @param inAnimResId  进入动画的resID
     * @param outAnimResID 离开动画的resID
     */
    private void setInAndOutAnimation(@AnimRes int inAnimResId, @AnimRes int outAnimResID) {
        Animation inAnim = AnimationUtils.loadAnimation(getContext(), inAnimResId);
        inAnim.setDuration(animDuration);
        mViewFlipper.setInAnimation(inAnim);

        Animation outAnim = AnimationUtils.loadAnimation(getContext(), outAnimResID);
        outAnim.setDuration(animDuration);
        mViewFlipper.setOutAnimation(outAnim);
    }

    /**
     * 设置延时间隔
     */
    private AnimRunnable mRunnable = new AnimRunnable();
    private class AnimRunnable implements Runnable {

        @Override
        public void run() {
            if (isStarted){
                setInAndOutAnimation(inAnimResId, outAnimResId);
                mViewFlipper.showNext();//手动显示下一个子view。
                postDelayed(this,mInterval + animDuration);
            }else {
                stopViewAnimator();
            }

        }
    }

    /**暂停动画*/
    public void stopViewAnimator(){
        if (isStarted){
            removeCallbacks(mRunnable);
            isStarted = false;
        }
    }

    /**开始动画*/
    public void startViewAnimator(){
        if (!isStarted){
            if (!isDetachedFromWindow){
                isStarted = true;
                postDelayed(mRunnable,mInterval);
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isDetachedFromWindow = true;
        stopViewAnimator();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        isDetachedFromWindow = false;
        startViewAnimator();

    }


}
