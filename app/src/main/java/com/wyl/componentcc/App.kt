package com.wyl.componentcc

import android.app.Application
import com.billy.cc.core.component.CC

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initCC()
    }

    private fun initCC() {
        // 以下三个设置默认为false
        CC.enableDebug(BuildConfig.DEBUG)       // 开启debug模式
        CC.enableVerboseLog(BuildConfig.DEBUG)  // 开启CC调用日志跟踪
        CC.enableRemoteCC(BuildConfig.DEBUG)    // 开启跨app组件调用
    }
}