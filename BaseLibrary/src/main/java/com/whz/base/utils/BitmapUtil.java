package com.whz.base.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import java.io.*;

/**
 * @Description: 图片剪切
 */
public class BitmapUtil {

    /**
     * 保存图片到文件
     *
     * @param path
     * @param data
     */
    public static void savePicture(String path, byte[] data) {
        if (data == null) {
            return;
        }
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        File f = new File(path);
        BufferedOutputStream bos;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(f));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存图片，默认图片名为：时间.png
     */
    public static boolean saveImage(Bitmap bitmap) {
        String timeStamp = (DateFormat.format("yyyy.MM.dd.hh.mm.ss",
                new java.util.Date())).toString();
        String suffixName = ".png";
        File paintpad = new File(SdCardUtil.DEFAULT_PHOTO_PATH);
        try {
            if (!paintpad.exists()) {
                paintpad.mkdirs();
            }
            bitmap.compress(Bitmap.CompressFormat.PNG, 100,
                    new FileOutputStream(SdCardUtil.DEFAULT_PHOTO_PATH + timeStamp + suffixName));
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 保存图片到文件
     *
     * @param path
     * @param bitmap
     */
    public static void savePicture(String path, Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        File f = new File(path);
        BufferedOutputStream bos;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(f));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();
            if (!bitmap.isRecycled()) {
                bitmap.recycle();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成毛玻璃位图
     *
     * @param sentBitmap
     * @param radius
     * @return
     */
    @SuppressLint("NewApi")
    public static Bitmap generationBlur(Bitmap sentBitmap, int radius) {
        Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
        if (radius < 1) {
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
//        Log.e("pix", w + " " + h + " " + pix.length);
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int temp = 256 * divsum;
        int dv[] = new int[temp];
        for (i = 0; i < temp; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }

//        Log.e("pix", w + " " + h + " " + pix.length);
        bitmap.setPixels(pix, 0, w, 0, 0, w, h);
        return (bitmap);
    }

    /**
     * 从asset目录读取图片
     *
     * @param context
     * @param fileName
     * @return
     */
    public static Bitmap getImageFromAssetsDef(Context context, String fileName) {
        return getImageFromAssetsDef(context.getResources(), fileName);
    }

    public static Bitmap getImageFromAssetsDef(Resources resource, String fileName) {
        if (fileName == null) {
            return null;
        }
        Bitmap image = null;
        AssetManager am = resource.getAssets();
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            InputStream is = am.open(fileName);
            options.inPurgeable = true;
            options.inInputShareable = true;
            options.inPreferredConfig = Config.RGB_565;
            image = BitmapFactory.decodeStream(is, null, options);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * 图片读取
     *
     * @param desc
     * @return
     */
    public static Bitmap decodeBitmap(FileDescriptor desc) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = 1;
        try {
            opts.inPurgeable = true;
            opts.inInputShareable = true;
            opts.inPreferredConfig = Config.RGB_565;
            Bitmap bmp = BitmapFactory.decodeFileDescriptor(desc, null, opts);
            return bmp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据路径从sd卡读取位图
     *
     * @param filePath
     * @return
     */
    public static Bitmap decodeBitmapSd(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        Bitmap bitmap = null;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            FileDescriptor fileDescriptor = inputStream.getFD();
            bitmap = decodeBitmap(fileDescriptor);
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("FileNotFound", filePath + "not found");
        } catch (IOException e) {
            Log.e("IOException", filePath + "read error");
        }
        return bitmap;
    }

    /**
     * 按照指定的宽高成比例的读取缩小的图片
     *
     * @param
     * @param width
     * @param height
     * @return
     */
    public static Bitmap decodeResizeBitmapSd(String filePath, int width, int height) {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        Bitmap bitmap = null;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            FileDescriptor fileDescriptor = inputStream.getFD();
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inSampleSize = 1;
            opts.inPurgeable = true;
            opts.inInputShareable = true;
            opts.inJustDecodeBounds = true;
            opts.inPreferredConfig = Config.RGB_565;
            BitmapFactory.decodeFileDescriptor(fileDescriptor, null, opts);
            // 计算缩放比例
            int simpleSize = calculateInSampleSize(opts, width, height);
            opts.inJustDecodeBounds = false;
            opts.inSampleSize = simpleSize;
            // 正式读取图片
            bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor, null, opts);
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("FileNotFound", filePath + "not found");
        } catch (IOException e) {
            Log.e("IOException", filePath + "read error");
        }
        return bitmap;
    }

    /**
     * 精确缩放图片到某个尺寸
     *
     * @param path
     * @param width
     * @param height
     * @return
     */
    public static Bitmap resizeBitmap(String path, int width, int height) {
        Bitmap bitmap = decodeResizeBitmapSd(path, width, height);
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        float scale = width / (bitmapWidth * 1.0f);
        if (scale > 1) {
            return bitmap;
        }
        float scaleHeight = height / (bitmapHeight * 1.0f);
        if (scaleHeight > 1) {
            return bitmap;
        }
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        if (bitmap != newbm) {
            bitmap.recycle();
        }
        return newbm;
    }

    /**
     * 修改图片为圆形
     *
     * @param bitmap
     * @return
     */
    public static Bitmap toCircleCorner(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        Bitmap output = Bitmap
                .createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = bitmap.getWidth() / 2;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        if (output != bitmap) {
            bitmap.recycle();
        }
        return output;
    }

    /**
     * 裁剪图片为圆形
     *
     * @param bitmap
     * @param pixels
     * @return
     */
    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
        if (bitmap == null) {
            return null;
        }
        Bitmap output = Bitmap
                .createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getHeight(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels * bitmap.getWidth() / 128;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        if (output != bitmap) {
            bitmap.recycle();
        }
        return output;
    }

    /**
     * 从resource目录读取图片
     *
     * @param context
     * @param resId
     * @return
     */
    public static Drawable getDrawableFromResource(Context context, int resId) {
        return context.getResources().getDrawable(resId);
    }

    /**
     * 根据宽高计算缩放比
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth,
                                            int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
            final float totalPixels = width * height;
            final float totalReqPixelsCap = reqWidth * reqHeight * 2;
            while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
                inSampleSize++;
            }
        }
        return inSampleSize;
    }

    /**
     * bitmap转为byte[]
     *
     * @param bitmap
     * @return
     */
    public static byte[] bitmapToBytes(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    public static String bitmapToBase64(String file) {
        byte[] iconByte = FileUtil.readFile(file);
        String total = Base64.encodeToString(iconByte, Base64.DEFAULT);
        return total;
    }

    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static Bitmap decodeBitmapStream(InputStream input, int width, int height) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = 1;
        opts.inPurgeable = true;
        opts.inInputShareable = true;
        opts.inJustDecodeBounds = true;
        opts.inPreferredConfig = Config.RGB_565;
        // BitmapFactory.decodeStream(input, null, opts);
        int simpleSize = calculateInSampleSize(opts, width, height);
        opts.inJustDecodeBounds = false;
        opts.inSampleSize = simpleSize;
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, opts);
        if (input != null) {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    public static Bitmap decodeBitmapResourse(Context context, int resourceId) {
        Resources resource = context.getResources();
        return BitmapFactory.decodeResource(resource, resourceId);
    }

    /**
     * 读取图片属性：旋转的角度 三星手机特别注意
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 旋转图片，使图片保持正确的方向。
     *
     * @param bitmap  原始图片
     * @param degrees 原始图片的角度
     * @return Bitmap 旋转后的图片
     */
    public static Bitmap rotateBitmap(Bitmap bitmap, int degrees) {
        if (degrees == 0 || null == bitmap) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(degrees, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        Bitmap bmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),
                matrix, true);
        if (null != bitmap) {
            bitmap.recycle();
        }
        return bmp;
    }

    /**
     * @param @param  view
     * @param @return 设定文件
     * @return Bitmap 返回类型
     * @throws
     * @Title: convertViewToBitmap
     * @Description: View转换为Bitmap
     */
    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    public static byte[] convertViewToByte(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        view.destroyDrawingCache();
        return baos.toByteArray();
    }

    /**
     * 获取视频缩略图
     *
     * @param filePath
     * @return
     */
    public static Bitmap getVideoThumbnail(String filePath) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    /**
     * 获取视频缩略图
     *
     * @param filePath
     * @return
     */
    public static byte[] getThumbByte(String filePath) {
        Bitmap bitmap = null;
        // 获取视频的缩略图
        bitmap = ThumbnailUtils.createVideoThumbnail(filePath, MediaStore.Images.Thumbnails.MINI_KIND);
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, 512, 384,
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

}
