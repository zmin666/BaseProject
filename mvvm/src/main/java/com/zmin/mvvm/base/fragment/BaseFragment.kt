package com.zmin.mvvm.base.fragment

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.zmin.mvvm.base.viewmodel.BaseViewModel
import com.zmin.mvvm.callback.livedata.AppViewModel
import com.zmin.mvvm.callback.livedata.EventViewModel
import com.zmin.mvvm.ext.dismissLoadingExt
import com.zmin.mvvm.ext.getAppViewModel
import com.zmin.mvvm.ext.hideSoftKeyboard
import com.zmin.mvvm.ext.showLoadingExt

/**
 * @author: ZhangMin
 * @date: 2020/11/10 14:09
 * @version: 1.0
 * @desc:
 */
abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmDbFragment<VM, DB>() {

    // 定义一个app全局ViewModel 存放账户信息,基本配置信息
    val appViewModel: AppViewModel by lazy { getAppViewModel<AppViewModel>() }

    // 定义一个app全局ViewModel 全局通知
    val eventViewModel: EventViewModel by lazy { getAppViewModel<EventViewModel>() }

    /**
     * 当前Fragment绑定的视图布局
     */
    abstract override fun layoutId(): Int


    abstract override fun initView(savedInstanceState: Bundle?)

    /**
     * 懒加载 只有当前fragment视图显示时才会触发该方法
     */
    override fun lazyLoadData() {}

    /**
     * 创建LiveData观察者 Fragment执行onViewCreated后触发
     */
    override fun createObserver() {}

    /**
     * Fragment执行onViewCreated后触发
     */
    override fun initData() {

    }

    /**
     * 打开等待框
     */
    override fun showLoading(message: String) {
        showLoadingExt(message)
    }

    /**
     * 关闭等待框
     */
    override fun dismissLoading() {
        dismissLoadingExt()
    }

    override fun onPause() {
        super.onPause()
        hideSoftKeyboard(activity)
    }
}