package com.whz.base.widgets

import android.text.Editable
import android.text.TextWatcher

/**
 *Created by whz  on 2019-06-24
 */
open class DefaultTextWatcher : TextWatcher {
    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}