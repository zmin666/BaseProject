package com.zmin.baseproject.ui.activity

import android.content.Intent
import android.os.Bundle
import com.zmin.baseproject.MainActivity
import com.zmin.baseproject.R
import com.zmin.baseproject.databinding.ActivityWelcomeBinding
import com.zmin.mvvm.base.activity.BaseActivity
import com.zmin.mvvm.base.viewmodel.BaseViewModel

/**
 * @author: ZhangMin
 * @date: 2020/11/10 15:36
 * @version: 1.0
 * @desc:
 */
class WelcomeActivity : BaseActivity<BaseViewModel, ActivityWelcomeBinding>() {
    override fun layoutId(): Int = R.layout.activity_welcome

    override fun initView(savedInstanceState: Bundle?) {
        mDataBind.proxy = ProxyClick()
    }

    override fun createObserver() {
    }

    inner class ProxyClick {
        fun toMain() {
            startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }
}