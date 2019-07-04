package com.whz.base.utils

import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.webkit.*
import android.widget.Toast

/**
 *Created by whz  on 2019-06-27
 */

class WebViewAlbum constructor(activity: Activity) : WebChromeClient() {
    private val CHOOSE_REQUEST_CODE = 0x9001
    private val mActivity: Activity=activity
    private var uploadFile: ValueCallback<Uri>? = null//定义接受返回值
    private var uploadFiles: ValueCallback<Array<Uri>>? = null

    override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
        Toast.makeText(mActivity,message,Toast.LENGTH_SHORT).show()
        result!!.confirm()
        return true
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onPermissionRequest(request: PermissionRequest) {
        //super.onPermissionRequest(request);//必须要注视掉
        request.grant(request.resources)
    }

    // For Android 3.0+
    fun openFileChooser(uploadMsg: ValueCallback<Uri>, acceptType: String) {
        this.uploadFile = uploadFile
        openFileChooseProcess()
    }

    // For Android < 3.0
    fun openFileChooser(uploadMsgs: ValueCallback<Uri>) {
        this.uploadFile = uploadFile
        openFileChooseProcess()
    }

    // For Android  > 4.1.1
    //    @Override
    fun openFileChooser(uploadMsg: ValueCallback<Uri>, acceptType: String, capture: String) {
        this.uploadFile = uploadFile
        openFileChooseProcess()
    }

    // For Android  >= 5.0
    override fun onShowFileChooser(webView: WebView,
                                   filePathCallback: ValueCallback<Array<Uri>>,
                                   fileChooserParams: WebChromeClient.FileChooserParams): Boolean {
        this.uploadFiles = filePathCallback
        openFileChooseProcess()
        return true
    }

    private fun openFileChooseProcess() {
        val i = Intent(Intent.ACTION_GET_CONTENT)
        i.addCategory(Intent.CATEGORY_OPENABLE)
        i.type = "image/*"
        mActivity.startActivityForResult(Intent.createChooser(i, "Choose"), CHOOSE_REQUEST_CODE)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                CHOOSE_REQUEST_CODE -> {
                    if (null != uploadFile) {
                        val result = if (data == null || resultCode != Activity.RESULT_OK)
                            null
                        else
                            data.data
                        uploadFile!!.onReceiveValue(result)
                        uploadFile = null
                    }
                    if (null != uploadFiles) {
                        val result = if (data == null || resultCode != Activity.RESULT_OK)
                            null
                        else
                            data.data
                        uploadFiles!!.onReceiveValue(arrayOf<Uri>(result!!))
                        uploadFiles = null
                    }
                }
                else -> {
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            if (null != uploadFile) {
                uploadFile!!.onReceiveValue(null)
                uploadFile = null
            }
            if (null != uploadFiles) {
                uploadFiles!!.onReceiveValue(null)
                uploadFiles = null
            }
        }
    }
}