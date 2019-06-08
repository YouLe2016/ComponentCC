package com.wyl.componentcc

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCResult
import com.wyl.baselibrary.*
import com.wyl.baselibrary.utils.JsonFormat
import com.wyl.baselibrary.utils.logD


class MainActivity : AppCompatActivity(), View.OnClickListener {

    var cc: CC? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btStartA -> {
                // 同步调用A组件(页面跳转)
                CC.obtainBuilder(A)
                    .setActionName(A_SHOW_A_ACTIVITY)
                    .build()
                    .call()
            }
            R.id.btStartAAysnc -> {
                // 异步调用A组件(页面跳转)
                CC.obtainBuilder(A)
                    .setActionName(A_SHOW_A_ACTIVITY)
                    .build()
                    .callAsync { _, result -> showResult(result) }
//                    .callAsyncCallbackOnMainThread { _, result -> showResult(result) }
            }
            R.id.btGetA -> {
                // 同步调用A组件(获取数据)
                val result = CC.obtainBuilder(A)
                    .setActionName(A_GET_INFO)
                    .build()
                    .call()
                showResult(result)
            }
            R.id.btGetAAysnc -> {
                // 异步调用A组件(获取数据)
                CC.obtainBuilder(A)
                    .setActionName(A_GET_INFO)
                    .build()
                    .callAsyncCallbackOnMainThread { _, result -> showResult(result) }
//                    .callAsync { _, result -> showResult(result) }
            }
            R.id.btGetAAysnc3 -> {
                // 异步调用A组件(慢请求3s)
                if (cc != null) {
                    cc!!.cancel()
                    Toast.makeText(this, "取消成功", Toast.LENGTH_SHORT).show()
                    return
                }
                cc = CC.obtainBuilder(A)
                    .setActionName(A_GET_INFO3)
                    .build()
                cc?.callAsync { _, result ->
                    cc = null
                    showResult(result)
                }
                Toast.makeText(this, "正在请求中...再次点击取消请求", Toast.LENGTH_SHORT).show()
            }
            R.id.btLogin -> {
                // 异步调用A组件(获取数据)
                CC.obtainBuilder(A)
                    .setActionName(A_LOGIN)
                    .build()
                    .callAsyncCallbackOnMainThread { _, result -> showResult(result) }
//                    .callAsync { _, result -> showResult(result) }
            }
            else -> { // else is R.id.btGetA
                //同步调用，直接返回结果
                val result = CC.obtainBuilder("ComponentLogin")
                    .setActionName("showActivity")
                    .build()
                    .call()
            }
        }
    }

    private fun showResult(result: CCResult) {
        var text = "result:\n" + JsonFormat.format(result.toString())
        text += "\n\n---------------------\n\n"
        logD(text)
    }

    override fun onDestroy() {
        cc?.cancel()
        super.onDestroy()
    }

}
