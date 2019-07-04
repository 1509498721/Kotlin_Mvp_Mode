package com.whz.base.utils;

import android.app.Activity;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.whz.base.R;

import java.util.List;

/**
 * @Description 拍照
 * Created by whz  on 2019-06-27
 */
public class PictureUtils {

    /**
     * 单独拍照
     *
     * @param activity
     */
    public static void openCamera(Activity activity, int requestCode) {
        PictureSelector.create(activity)
                .openCamera(PictureMimeType.ofImage())
                .theme(R.style.picturestyle)
                .enableCrop(true)// 是否裁剪 true or false
                .freeStyleCropEnabled(false)// 裁剪框是否可拖拽 true or false
                .circleDimmedLayer(true)// 是否圆形裁剪 true or false
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .selectionMode(PictureConfig.SINGLE)
                .forResult(requestCode);
    }

    /**
     * 单独拍照(自定义裁剪宽高)
     *
     * @param activity
     */
    public static void openCameraWithCropRatio(Activity activity, int width, int height, int requestCode) {
        PictureSelector.create(activity)
                .openCamera(PictureMimeType.ofImage())
                .theme(R.style.picturestyle)
                .compress(true)
                .enableCrop(true)// 是否裁剪 true or false
                .freeStyleCropEnabled(false)// 裁剪框是否可拖拽 true or false
                .circleDimmedLayer(true)// 是否圆形裁剪 true or false
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .withAspectRatio(1,1)
                .cropWH(width, height)
                .selectionMode(PictureConfig.SINGLE)
                .forResult(requestCode);
    }

    /**
     * 不裁剪
     *
     * @param activity
     * @param requestCode
     */
    public static void openCameraNoCrop(Activity activity, int requestCode) {
        PictureSelector.create(activity)
                .openCamera(PictureMimeType.ofImage())
                .theme(R.style.picturestyle)
                .enableCrop(false)// 是否裁剪 true or false
                .selectionMode(PictureConfig.SINGLE)
                .minimumCompressSize(300)
                .compress(true)// 是否压缩 true or false
                .forResult(requestCode);
    }

    /**
     * 单独选择一张图片
     *
     * @param activity
     */
    public static void openAluamOne(Activity activity, int requestCode) {
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                .theme(R.style.picturestyle)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .imageSpanCount(4)// 每行显示个数 int
                .enableCrop(true)// 是否裁剪 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .circleDimmedLayer(true)// 是否圆形裁剪 true or false
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .isCamera(false)// 是否显示拍照按钮 true or false
                .forResult(requestCode);
    }

    /**
     * 单独选择一张图片(自定义裁剪宽高)
     *
     * @param activity
     */
    public static void openAluamOneWithCropRatio(Activity activity, int width, int height, int requestCode) {
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                .theme(R.style.picturestyle)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .imageSpanCount(4)// 每行显示个数 int
                .enableCrop(true)// 是否裁剪 true or false
                .compress(true)
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .circleDimmedLayer(true)// 是否圆形裁剪 true or false
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .isCamera(false)// 是否显示拍照按钮 true or false
                .cropWH(width,height)
                .forResult(requestCode);
    }

    /**
     * 单独选择一张图片不裁剪
     *
     * @param activity
     */
    public static void openAluamOneNoCrop(Activity activity, int requestCode) {
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                .theme(R.style.picturestyle)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .imageSpanCount(4)// 每行显示个数 int
                .enableCrop(false)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .minimumCompressSize(300)
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .isCamera(false)// 是否显示拍照按钮 true or false
                .forResult(requestCode);
    }

    /**
     * 选择多张图片
     *
     * @param activity
     */
    public static void openAluamMore(List<LocalMedia> list, Activity activity, int requestCode, int size) {
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                .theme(R.style.picturestyle)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .maxSelectNum(size)// 最大图片选择数量 int
                .selectionMedia(list)// 是否传入已选图片 List<LocalMedia> list
                .imageSpanCount(3)// 每行显示个数 int
                .enableCrop(false)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .isCamera(true)// 是否显示拍照按钮 true or false
                .forResult(requestCode);
    }


    /**
     * 选择视频
     *
     * @param activity
     */
    public static void openVideo(Activity activity, int requestCode) {
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofVideo())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                .theme(R.style.picturestyle)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .maxSelectNum(1)// 最大图片选择数量 int
                .imageSpanCount(3)// 每行显示个数 int
                .enableCrop(false)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .videoMaxSecond(10)
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .isCamera(false)// 是否显示拍照按钮 true or false
                .forResult(requestCode);
    }





    //包括裁剪和压缩后的缓存，要在上传成功后调用，注意：需要系统sd卡权限
    public static void clearCache(Activity activity) {
        PictureFileUtils.deleteCacheDirFile(activity);
    }

}
