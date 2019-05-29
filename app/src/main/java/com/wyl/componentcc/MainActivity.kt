package com.wyl.componentcc

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.billy.cc.core.component.CC
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            //同步调用，直接返回结果
            funciton1()
//            //或 异步调用，不需要回调结果
//            val callId = CC.obtainBuilder("ComponentLogin")
//                .setActionName("showActivity")
//                .build()
//                .callAsync()
//            //或 异步调用，在子线程执行回调
//            val callId = CC.obtainBuilder("ComponentLogin")
//                .setActionName("showActivity")
//                .build()
//                .callAsync { cc, result ->
//                    //此onResult在子线程中运行
//                }
//            //或 异步调用，在主线程执行回调
//            val callId = CC.obtainBuilder("ComponentLogin")
//                .setActionName("login")
//                .build()
//                .callAsyncCallbackOnMainThread { cc, result ->
//                    //此onResult在主线程中运行
//                    val toast = "login " + if (result.isSuccess) "success" else "failed"
//                    Toast.makeText(this@MainActivity, toast, Toast.LENGTH_SHORT).show()
//                }
        }
    }

    private fun funciton1() {
        val result = CC.obtainBuilder("ComponentLogin")
            .setActionName("showActivity")
            .build()
            .call()
    }

}
