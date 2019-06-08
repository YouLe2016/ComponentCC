package com.wyl.login

import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCResult
import com.billy.cc.core.component.CCUtil
import com.billy.cc.core.component.IComponent

class ComponentLogin : IComponent {
    override fun getName(): String = "ComponentLogin"

    override fun onCall(cc: CC): Boolean {
        when (cc.actionName) {
            "showActivity" //响应actionName为"showActivity"的组件调用
            -> {
                //跳转到页面：ActivityA
                CCUtil.navigateTo(cc, LoginActivity::class.java)
                //返回处理结果给调用方
                CC.sendCCResult(cc.callId, CCResult.success())
                //同步方式实现（在return之前听过CC.sendCCResult()返回组件调用结果），return false
                return false
            }
            else -> {
                //其它actionName当前组件暂时不能响应，可以通过如下方式返回状态码为-12的CCResult给调用方
                CC.sendCCResult(cc.callId, CCResult.errorUnsupportedActionName())
                return false
            }
        }
    }
}