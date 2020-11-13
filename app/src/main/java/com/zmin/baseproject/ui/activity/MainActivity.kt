package com.zmin.baseproject.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import com.blankj.utilcode.util.ToastUtils
import com.tencent.bugly.beta.Beta
import com.zmin.baseproject.R
import com.zmin.baseproject.databinding.ActivityMainBinding
import com.zmin.mvvm.base.activity.BaseActivity
import com.zmin.mvvm.base.viewmodel.BaseViewModel


/**
 * @author: ZhangMin
 * @date: 2020/11/12 14:27
 * @version: 1.0
 * @desc: 主页面, 这里使用navigation管理多个fragment
 */
class MainActivity : BaseActivity<BaseViewModel, ActivityMainBinding>() {

    var exitTime: Long = 0

    override fun layoutId(): Int = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        //配置bugly
      //  Beta.checkUpgrade(false, true)
        //返回键
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val navController =
                    Navigation.findNavController(this@MainActivity, R.id.host_fragment)
                if (navController.currentDestination != null && navController.currentDestination!!.id != R.id.mainfragment) {
                    navController.navigateUp()
                } else {
                    if (System.currentTimeMillis() - exitTime > 2000) {
                        ToastUtils.showShort("再按一次退出程序")
                        exitTime = System.currentTimeMillis()
                    } else {
                        finish()
                    }
                }
            }
        })
    }

    override fun createObserver() {

    }
}