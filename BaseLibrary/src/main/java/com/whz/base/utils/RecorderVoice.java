package com.whz.base.utils;

import android.media.MediaRecorder;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.format.Time;

import java.io.File;
import java.io.IOException;
import java.util.Date;



/**
 * @Description 语音录制 MediaRecorder
 * Created by whz  on 2019-06-27
 */
public class RecorderVoice {
    MediaRecorder recorder;
    static final String PREFIX = "voice";
    static final String EXTENSION = ".amr";
    private boolean isRecording = false;
    private long startTime;
    private double recordDb;
    private int BASE = 1;
    private int SPACE = 100;// 间隔取样时间
    public static final int MAX_LENGTH = 1000 * 60 * 10;// 最大录音时长1000*10;
    private String voiceFilePath = null;
    private String voiceFileName = null;
    private File file;
    private Handler handler;
    private CallBack callBack;
    private MyCountDownTimer count;

    public RecorderVoice(Handler paramHandler) {
        this.handler = paramHandler;
    }

    /**
     * 开始录制音频
     * @param fileName 录制的文件名称
     * @return
     */
    public String startRecording(String fileName, SixtySecondCallBack sixtySecondCallBack) {
        this.file = null;
        try {
            if (this.recorder != null) {
                this.recorder.release();
                this.recorder = null;
            }
            this.recorder = new MediaRecorder();
            this.recorder.setAudioSource(1);
            this.recorder.setOutputFormat(3);
            this.recorder.setAudioEncoder(1);
            this.recorder.setAudioChannels(1);
            this.recorder.setAudioSamplingRate(8000);
            this.recorder.setAudioEncodingBitRate(64);
            this.voiceFileName = getVoiceFileName(fileName);
            this.voiceFilePath = getVoiceFilePath();
            this.file = new File(this.voiceFilePath);
            this.recorder.setOutputFile(this.file.getAbsolutePath());
//            this.recorder.setMaxDuration(MAX_LENGTH);
            this.recorder.prepare();
            this.isRecording = true;
            this.recorder.start();
            count = new MyCountDownTimer(sixtySecondCallBack);
            count.start();
//            updateMicStatus();
        } catch (IOException localIOException) {
            System.out.println( "prepare() failed");
        }
        new Thread(new Runnable() {
            public void run() {
                try {
                    while (RecorderVoice.this.isRecording) {
                        Message localMessage = new Message();
                        localMessage.what = (RecorderVoice.this.recorder.getMaxAmplitude() * 13 / 32767);
                        RecorderVoice.this.handler.sendMessage(localMessage);
                        SystemClock.sleep(100L);
                    }
                } catch (Exception localException) {
                    System.out.println(localException.toString());
                }
            }
        }).start();
        this.startTime = new Date().getTime();
        return this.file == null ? null : this.file.getAbsolutePath();
    }


    private final Handler mHandler = new Handler();
    private Runnable mUpdateMicStatusTimer = new Runnable() {
        public void run() {
            updateMicStatus();
        }
    };

    /**
     * 更新分贝值
     */
    private void updateMicStatus() {
        if (recorder != null) {
            double ratio = (double)recorder.getMaxAmplitude() /BASE;
            double db = 0;// 分贝
            if (ratio > 1)
                db = 20 * Math.log10(ratio);
            System.out.println("分贝值：" + db);
            recordDb = db;
            callBack.callBack(recordDb);
            mHandler.postDelayed(mUpdateMicStatusTimer, SPACE);
        }
    }

    public void discardRecording() {
        if (this.recorder != null) {
            try {
                this.recorder.stop();
                this.recorder.release();
                this.recorder = null;
                if ((this.file != null) && (this.file.exists()) && (!this.file.isDirectory()))
                    this.file.delete();
            } catch (IllegalStateException localIllegalStateException) {
            } catch (RuntimeException localRuntimeException) {
            }
            this.isRecording = false;
        }
    }

    /**
     * 结束录制音频
     * @return 返回音频的秒数
     */
    public int stopRecoding() {
        if(null != count){
            count.cancel();
        }
        if (this.recorder != null) {
            this.isRecording = false;
            this.recorder.stop();
            this.recorder.release();
            this.recorder = null;
            if ((this.file == null) || (!this.file.exists()) || (!this.file.isFile()))
                return -1011;
            if (this.file.length() == 0L) {
                this.file.delete();
                return -1011;
            }
            int i = (int) (new Date().getTime() - this.startTime) / 1000;
            return i;
        }
        return 0;
    }

    protected void finalize()
            throws Throwable {
        super.finalize();
        if (this.recorder != null)
            this.recorder.release();
    }

    public String getVoiceFileName(String paramString) {
        Time localTime = new Time();
        localTime.setToNow();
        return paramString + localTime.toString().substring(0, 15) + ".amr";
    }

    public boolean isRecording() {
        return this.isRecording;
    }


    /**
     * 获得录制文件的路径
     *
     * @return
     */
    public String getVoiceFilePath() {
        return SdCardUtil.DEFAULT_RECORD_PATH + this.voiceFileName;
    }

    /**
     * 获得录音时的分贝值：默认为0
     * @return
     */
    public double getRecordDb(){
        return recordDb;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public interface CallBack {
        void callBack(Object returnData);
    }

    public interface SixtySecondCallBack{
        void callBack(int second);
    }


    public class MyCountDownTimer extends CountDownTimer {

        /**
         * @Fields count : 倒计时
         */
        private SixtySecondCallBack callBack;

        public MyCountDownTimer(long millisInFuture, long countDownInterval, SixtySecondCallBack callBack) {
            super(millisInFuture, countDownInterval);
            this.callBack = callBack;
        }

        public MyCountDownTimer(SixtySecondCallBack callBack) {
            super(60 * 1000, 1000);
            this.callBack = callBack;
        }

        @Override
        public void onTick(long millisUntilFinished) {
        }

        @Override
        public void onFinish() {
            callBack.callBack(60*1000);
        }
    }
}