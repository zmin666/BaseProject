package com.zmin.mvvm.base.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.zmin.mvvm.base.viewmodel.BaseViewModel

/**
 * @author: ZhangMin
 * @date: 2020/11/10 15:10
 * @version: 1.0
 * @desc:
 */
abstract class BaseVmDbActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmActivity<VM>() {

    lateinit var mDataBind: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        userDataBinding(true)
        super.onCreate(savedInstanceState)
    }

    override fun initDataBind() {
        mDataBind = DataBindingUtil.setContentView(this, layoutId())
        mDataBind.lifecycleOwner = this
    }
}