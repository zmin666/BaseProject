package com.zmin.mvvm.base.activity

import android.content.res.Resources
import androidx.databinding.ViewDataBinding
import com.zmin.mvvm.base.viewmodel.BaseViewModel
import com.zmin.mvvm.callback.livedata.AppViewModel
import com.zmin.mvvm.callback.livedata.EventViewModel
import com.zmin.mvvm.ext.dismissLoadingExt
import com.zmin.mvvm.ext.getAppViewModel
import com.zmin.mvvm.ext.showLoadingExt
import me.jessyan.autosize.AutoSizeCompat

/**
 * @author: ZhangMin
 * @date: 2020/11/10 15:20
 * @version: 1.0
 * @desc:
 */
abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmDbActivity<VM, DB>() {

    val appViewModel: AppViewModel by lazy { getAppViewModel<AppViewModel>() }

    val eventViewModel: EventViewModel by lazy { getAppViewModel<EventViewModel>() }

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

    /**
     * 在任何情况下本来适配正常的布局突然出现适配失效，适配异常等问题，只要重写 Activity 的 getResources() 方法
     */
    override fun getResources(): Resources {
        AutoSizeCompat.autoConvertDensityOfGlobal(super.getResources())
        return super.getResources()
    }
}