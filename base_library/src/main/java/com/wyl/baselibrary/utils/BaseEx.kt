package com.wyl.baselibrary.utils

import android.util.Log
import com.wyl.baselibrary.BuildConfig

fun Any.logD(msg: Any?, tag: String? = null) {
    if (BuildConfig.DEBUG) {
        Log.d(tag ?: javaClass.simpleName, msg.toString())
    }
    // Activity.componentName.shortClassName == javaClass.simpleName
}

fun Any.logE(msg: Any?, tag: String? = null) {
    if (BuildConfig.DEBUG) {
        Log.e(tag ?: javaClass.simpleName, msg.toString())
    }
    // Activity.componentName.shortClassName == javaClass.simpleName
}