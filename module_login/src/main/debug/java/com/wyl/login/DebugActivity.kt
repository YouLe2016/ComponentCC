package com.wyl.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.billy.cc.core.component.CC

class DebugActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View) {
        val result = CC.obtainBuilder("ComponentLogin")
            .setActionName("showActivity")
            .build()
            .call()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.debug_activity)

        Toast.makeText(this, "hello CC", Toast.LENGTH_SHORT).show()
        //需要单独安装运行，但不需要入口页面（只需要从主app中调用此组件）时，
        // 可直接finish当前activity
//        finish()


    }
}
