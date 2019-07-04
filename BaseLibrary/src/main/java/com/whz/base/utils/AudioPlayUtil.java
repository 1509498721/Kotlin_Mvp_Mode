package com.whz.base.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.RawRes;
import android.util.Log;
import com.github.piasy.rxandroidaudio.PlayConfig;
import com.github.piasy.rxandroidaudio.RxAudioPlayer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.io.File;

/**
 * @Description URl
 * Created by whz  on 2019-06-27
 */
public class AudioPlayUtil {

    private static AudioPlayUtil instance = null;
    private static RxAudioPlayer mAudioPlayer;

    private AudioPlayUtil(){}

    public static AudioPlayUtil getInstance() {
        if(instance == null){
            mAudioPlayer = RxAudioPlayer.getInstance();
            instance  = new AudioPlayUtil();
        }
        return instance;
    }


    public static MediaPlayer getPlayer() {
        return mAudioPlayer.getMediaPlayer();
    }

    private onAudioProgress mProgress;

    /**
     *  Url 读取
     * @param url
     * @param progress
     */
    public void play(String url, onAudioProgress progress){
        mProgress = progress;
        if(mAudioPlayer!= null){
            mAudioPlayer.play(PlayConfig.url(url).build()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Boolean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Boolean aBoolean) {
                    Log.e("AudioPlayer","Media is prepare");
                    mProgress.onPrepare();
                }

                @Override
                public void onError(Throwable e) {
                    mProgress.onError(e);
                }

                @Override
                public void onComplete() {
                    mAudioPlayer.stopPlay();
                    mProgress.onCompletion();
                }
            });
        }
    }

    /**
     * File 文件播放
     * @param file
     * @param progress
     */
    public void play(File file, onAudioProgress progress){
        mProgress = progress;
        if(mAudioPlayer!= null){
            mAudioPlayer.play(PlayConfig.file(file).looping(false).build()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Boolean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Boolean aBoolean) {
                    Log.e("AudioPlayer","Media is prepare");
                    mProgress.onPrepare();
                }

                @Override
                public void onError(Throwable e) {
                    mProgress.onError(e);
                }

                @Override
                public void onComplete() {
                    mAudioPlayer.stopPlay();
                    mProgress.onCompletion();
                }
            });
        }
    }


    /**
     * raw 本地资源读取
     * @param context
     * @param audioResource
     * @param progress
     */

    public void play(Context context, @RawRes int audioResource, onAudioProgress progress){
        mProgress = progress;
        if(mAudioPlayer!= null){
            mAudioPlayer.play(PlayConfig.res(context,audioResource).looping(false).build()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Boolean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Boolean aBoolean) {
                    Log.e("AudioPlayer","Media is prepare");
                    mProgress.onPrepare();
                }

                @Override
                public void onError(Throwable e) {
                    mProgress.onError(e);
                }

                @Override
                public void onComplete() {
                    mAudioPlayer.stopPlay();
                    mProgress.onCompletion();
                }
            });
        }
    }



    public interface onAudioProgress{
        void onError(Throwable e);

        void onPrepare();

        void onCompletion();

    }


}
