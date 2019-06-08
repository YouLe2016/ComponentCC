package com.wyl.modulea

import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCResult
import com.billy.cc.core.component.CCUtil
import com.billy.cc.core.component.IComponent
import com.wyl.baselibrary.*
import com.wyl.baselibrary.utils.JsonFormat
import com.wyl.baselibrary.utils.logD
import com.wyl.baselibrary.utils.logE
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ComponentA : IComponent {
    override fun getName(): String = A

    override fun onCall(cc: CC): Boolean {
        showCC(cc)
        when (cc.actionName) {
            A_SHOW_A_ACTIVITY -> openAActivity(cc)
            A_GET_INFO -> setInfo(cc)
            A_GET_INFO3 -> {
                setInfo3(cc)
                return true
            }
            A_LOGIN -> {
                openLoginActivity(cc)
                return true
            }
            else -> CC.sendCCResult(cc.callId, CCResult.errorUnsupportedActionName())
        }
        return false
    }

    private fun openLoginActivity(cc: CC) {
        CCUtil.navigateTo(cc, LoginActivity::class.java)
    }

    private fun setInfo3(cc: CC) = runBlocking {
        GlobalScope.launch {
            getInfo3(cc)
        }
    }

    private suspend fun getInfo3(cc: CC) {
        val maxStep = 6
        var step = 1
        val delayTime = 500L

        while (step < maxStep) {
            if (cc.isStopped) {
                break
            }
            delay(delayTime)
            step++
        }

        if (!cc.isStopped) {
            CC.sendCCResult(cc.callId, CCResult.success("data", "data from network"))
        } else {
            logE("get data from network stopped. step= $step")
        }
    }

    private fun setInfo(cc: CC) {
        CC.sendCCResult(cc.callId, CCResult.success("username", "小白"))
    }

    private fun openAActivity(cc: CC) {
        CCUtil.navigateTo(cc, AActivity::class.java)
        CC.sendCCResult(cc.callId, CCResult.success())
    }

    private fun showCC(cc: CC) {
        var text = "cc:\n" + JsonFormat.format(cc.toString())
        text += "\n\n---------------------\n\n"
        logD(text)
    }
}