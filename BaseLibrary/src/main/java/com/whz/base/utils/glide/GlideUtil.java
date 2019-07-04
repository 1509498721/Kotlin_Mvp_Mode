package com.whz.base.utils.glide;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.whz.base.utils.LogUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by whz  on 2019-06-27
 */
public class GlideUtil {
    private static final String TAG = "GlideUtil";
    /**
     * Glide的请求管理器类
     */
    @SuppressLint("StaticFieldLeak")
    private static RequestManager mRequestManager;
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    private static RequestOptions options;
    private static DrawableTransitionOptions transitionOptions;
    @SuppressLint("StaticFieldLeak")
    private static RequestBuilder<Bitmap> bitmapRequestBuilder;
    private static RequestOptions requestOptions;

    /**
     * 初始化Glide工具
     *
     * @param context
     */
    public static void init(Context context) {
        mContext = context;
        mRequestManager = GlideApp.with(context.getApplicationContext());
        options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        transitionOptions = new DrawableTransitionOptions()
                .crossFade();
        bitmapRequestBuilder = GlideApp.with(context.getApplicationContext()).asBitmap().dontAnimate();
    }


    public static void load(String url, int placeholder, int error, ImageView imageView) {
        mRequestManager
                .load(url)
                .apply(options.placeholder(placeholder).error(error))
                .into(imageView);
    }


    public static void load(String url, ImageView imageView) {
        mRequestManager
                .load(url)
                .transition(transitionOptions)
                .into(imageView);
    }

    public static void loadInBackground(String url, ImageView imageView) {
        mRequestManager
                .load(url)
                .transition(transitionOptions)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        imageView.setBackground(resource);
                    }
                });
    }

    public static void loadFile(String url, ImageView imageView) {
        mRequestManager
                .load(new File(url))
                .transition(transitionOptions)
                .into(imageView);
    }

    public static void loadDefault(String res, int placeholder, ImageView imageView) {
        mRequestManager
                .load(res)
                .apply(options.placeholder(placeholder))
                .transition(transitionOptions)
                .into(imageView);
    }

    public static void load(int res, ImageView imageView) {
        mRequestManager
                .load(res)
                .apply(options.centerCrop())
                .transition(transitionOptions)
                .into(imageView);
    }

    /**
     * 带缓冲Key读取
     *
     * @param res
     * @param key
     * @param imageView
     */
    public static void loadWithKey(String res, String key, int defaultImg, ImageView imageView) {
        mRequestManager
                .load(res)
                .apply(options.signature(new ObjectKey(key)).placeholder(defaultImg).error(defaultImg))
                .into(imageView);
    }

    //加载圆型的图片
    public static void loadCirclePicture(String url, int placeholder, int error, ImageView imageView) {
        requestOptions = RequestOptions.circleCropTransform();
        mRequestManager
                .load(url).apply(requestOptions.placeholder(placeholder).error(error)).into(imageView);
    }

    //加载圆型的图片
    public static void loadCirclePicture(String url, ImageView imageView) {
        requestOptions = RequestOptions.circleCropTransform();
        mRequestManager
                .load(url).apply(requestOptions).into(imageView);
    }

    //加载圆型的图片
    public static void loadCirclePicture(int url, ImageView imageView) {
        requestOptions = RequestOptions.circleCropTransform();
        mRequestManager
                .load(url).apply(requestOptions).into(imageView);
    }

    //加载圆型的图片
    public static void loadCircleBackground(int url, ImageView imageView) {
        requestOptions = RequestOptions.circleCropTransform();
        mRequestManager
                .load(url).apply(requestOptions).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                imageView.setBackground(resource);
            }
        });
    }


    //加载圆角的图片
    public static void loadRoundPicture(String url, ImageView imageView) {
        requestOptions = RequestOptions.bitmapTransform(new GlideRoundTransform());
        mRequestManager.load(url).apply(requestOptions)
                .transition(transitionOptions)
                .into(imageView);
    }

    //加载圆角的图片
    public static void loadRoundPicture(int url, int placeholder, ImageView imageView) {
        requestOptions = RequestOptions.bitmapTransform(new GlideRoundTransform());
        mRequestManager
                .load(url).apply(requestOptions)
                .into(imageView);
    }


    //加载圆角的图片
    public static void loadRoundPicture(String url, int placeholder, ImageView imageView) {
        requestOptions = RequestOptions.bitmapTransform(new GlideRoundTransform());
        mRequestManager.load(url).apply(requestOptions)
                .into(imageView);
    }

    //加载圆角的图片(带半径）
    public static void loadRoundPicture(String url, ImageView imageView, int radius) {
        requestOptions = RequestOptions.bitmapTransform(new GlideRoundTransform(radius));
        mRequestManager.load(url).apply(requestOptions)
                .transition(transitionOptions)
                .into(imageView);
    }

    //加载圆角的图片(带半径）
    public static void loadRoundPicture(int url, ImageView imageView, int radius) {
        requestOptions = RequestOptions.bitmapTransform(new GlideRoundTransform(radius));
        mRequestManager.load(url).apply(requestOptions)
                .transition(transitionOptions)
                .into(imageView);
    }

    public static void loadRoundBackground(String url, ImageView imageView, int radius) {
        requestOptions = RequestOptions.bitmapTransform(new GlideRoundTransform(radius));
        mRequestManager.load(url).apply(requestOptions)
                .transition(transitionOptions)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        imageView.setBackground(resource);
                    }
                });
    }


    public static void loadRoundBackground(int url, ImageView imageView, int radius) {
        requestOptions = RequestOptions.bitmapTransform(new GlideRoundTransform(radius));
        mRequestManager.load(url).apply(requestOptions)
                .transition(transitionOptions)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        imageView.setBackground(resource);
                    }
                });
    }


    //高斯模糊图片(带半径）
    public static void loadBlurPicture(int url, ImageView imageView, int blurRadius) {
        requestOptions = RequestOptions.bitmapTransform(new BlurImageTransform(blurRadius));
        mRequestManager.load(url).apply(requestOptions)
                .transition(transitionOptions)
                .into(imageView);
    }

    //高斯模糊图片(带半径）
    public static void loadBlurPicture(String url, ImageView imageView, int blurRadius) {
        requestOptions = RequestOptions.bitmapTransform(new BlurImageTransform(blurRadius));
        mRequestManager.load(url).apply(requestOptions)
                .transition(transitionOptions)
                .into(imageView);
    }

    //高斯模糊图片
    public static void loadBlurPicture(String url, ImageView imageView) {
        requestOptions = RequestOptions.bitmapTransform(new BlurImageTransform());
        mRequestManager.load(url).apply(requestOptions)
                .transition(transitionOptions)
                .into(imageView);
    }

    //高斯模糊图片
    public static void loadBlurPicture(int url, ImageView imageView) {
        requestOptions = RequestOptions.bitmapTransform(new BlurImageTransform());
        mRequestManager.load(url).apply(requestOptions)
                .transition(transitionOptions)
                .into(imageView);
    }

    //读取Bitmap
    public static void load(Bitmap bitmap, ImageView imageView) {
        mRequestManager.load(bitmap).into(imageView);
    }

    //读取到Bitmap
    public static void loadIntoBitmap(String url, SimpleTarget<Bitmap> target) {
        bitmapRequestBuilder.load(url).into(target);
    }

    public static void loadIntoDrawable(String url, SimpleTarget<Drawable> target) {
        mRequestManager.load(url).into(target);
    }

    //读取到Bitmap
    public static void loadIntoBitmap(int url, SimpleTarget<Bitmap> target) {
        bitmapRequestBuilder.load(url).into(target);
    }


    //通过Glide下载图片到系统相册(需要获取权限)
    public static void saveInDCIM(String url) {
        mRequestManager.asBitmap().load(url).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                try {
                    savePicture(resource);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }




    /**
     * 获取视频第一帧图片
     *
     * @param videoUrl
     * @param imageView
     */
    public static void getNetVideoBitmap(String videoUrl, ImageView imageView) {
        Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> e) throws Exception {
                Map<String, String> map = new HashMap<>();
                MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                Bitmap bitmap = null;
                try {
                    //根据url获取缩略图
                    retriever.setDataSource(videoUrl, map);
                    //获得第一帧图片
                    bitmap = retriever.getFrameAtTime();
                    e.onNext(bitmap);
                } catch (IllegalArgumentException ee) {
                    ee.printStackTrace();
                } finally {
                    retriever.release();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        bitmapRequestBuilder.load(bitmap).into(imageView);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }




    //储存到本地操作
    private static void savePicture(Bitmap bitmap) throws IOException {

        String filePath = Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DCIM + "/" + System.currentTimeMillis() + ".png";
        File saveFile = new File(filePath);
        saveFile.createNewFile();
        Log.e("Glide-DownLoad", saveFile.getPath());
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(saveFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, fos);
        try {
            if (fos != null) {
                fos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Glide工具类是否已经初始化
     *
     * @return 已初始化则返回true
     */
    public static boolean isInit() {
        if (mContext == null || mRequestManager == null) {
            LogUtil.i(TAG, TAG + "not init");
            return false;
        }
        return true;
    }

    public static void clearCache() {
        Glide.get(mContext.getApplicationContext()).clearDiskCache();
        Glide.get(mContext.getApplicationContext()).clearMemory();
    }

    public static void pause(Context context) {
        Glide.with(context).pauseRequests();
    }

    public static void resume(Context context) {
        Glide.with(context).resumeRequests();
    }

}
