package com.whz.base.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

/**
 * 创建快捷方式，发送到桌面
 * 快捷方式Util
 * Created by whz  on 2019-06-27
 */
public class ShortcutUtil {
    private static ShortcutUtil shortcutUtil;
    private Context context;
    /**
     * 添加快捷Action
     */
    public static final String ACTION_ADD_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
    /**
     * 移除快捷Action
     */
    public static final String ACTION_REMOVE_SHORTCUT = "com.android.launcher.action.UNINSTALL_SHORTCUT";

    private ShortcutUtil(Context context) {
        this.context = context;
    }

    public static ShortcutUtil getInstance(Context context) {
        if (shortcutUtil == null) {
            synchronized (ShortcutUtil.class) {
                if (shortcutUtil == null) {
                    shortcutUtil = new ShortcutUtil(context);
                }
            }
        }
        return shortcutUtil;
    }

    /**
     * 添加快捷方式
     *
     * @param name   快捷方式名称
     * @param clazz  点击快捷方式启动的activity
     * @param iconID 快捷方式图标id
     */
    public void addShortcut(String name, Class clazz, int iconID) {
        Intent addShortcutIntent = new Intent(ACTION_ADD_SHORTCUT);

        // 不允许重复创建
        addShortcutIntent.putExtra("duplicate", false);// 经测试不是根据快捷方式的名字判断重复的
        // 应该是根据快链的Intent来判断是否重复的,即Intent.EXTRA_SHORTCUT_INTENT字段的value
        // 但是名称不同时，虽然有的手机系统会显示Toast提示重复，仍然会建立快链
        // 屏幕上没有空间时会提示
        // 注意：重复创建的行为MIUI和三星手机上不太一样，小米上似乎不能重复创建快捷方式
        // 名字
        addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);

        // 图标
        Parcelable icon = Intent.ShortcutIconResource.fromContext(context,
                iconID);
        addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);

        // 设置关联程序
        Intent launcherIntent = new Intent("kupai_shortcut");
        launcherIntent.setClass(context, clazz);
        launcherIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, launcherIntent);

        // 发送广播
        context.sendBroadcast(addShortcutIntent);
    }
}
