package com.zmin.mvvm.base.viewmodel

import androidx.lifecycle.ViewModel
import com.zmin.mvvm.callback.livedataExtand.EventLiveData

/**
 * @author: ZhangMin
 * @date: 2020/11/10 11:33
 * @version: 1.0
 * @desc:  基础BaseViewModel类, 加入了一个常量
 *         因为加载数据的前需要弹窗,加载后需要关闭弹窗.
 */
open class BaseViewModel : ViewModel() {

    val loadingChange: UiLoadingChange by lazy { UiLoadingChange() }

    inner class UiLoadingChange {

        val showDialog by lazy { EventLiveData<String>() }

        val dismissDialog by lazy { EventLiveData<Boolean>() }
    }

}