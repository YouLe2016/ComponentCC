package com.wyl.modulea

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.billy.cc.core.component.CC
import com.billy.cc.core.component.CCResult
import com.billy.cc.core.component.CCUtil
import com.wyl.baselibrary.bean.User

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var callId: String
    lateinit var etName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_login_activity)

        callId = CCUtil.getNavigateCallId(this)
        etName = findViewById(R.id.etName)
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.btLogin -> {
                if (login()) {
                    finish()
                }
            }
            else -> {
            }
        }
    }

    override fun onDestroy() {
        sendLoginResult()
        super.onDestroy()
    }

    private fun sendLoginResult() {
        if (UserStateManager.loginUser == null) {
            CC.sendCCResult(callId, CCResult.error("login cancel."))
        } else {
            CC.sendCCResult(callId, CCResult.success(UserStateManager.KEY_USER, UserStateManager.loginUser))
        }
    }

    private fun login(): Boolean {
        val username = etName.text.toString().trim()
        return if (username.isEmpty()) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show()
            false
        } else {
            UserStateManager.loginUser = User(1, username)
            true
        }
    }
}
