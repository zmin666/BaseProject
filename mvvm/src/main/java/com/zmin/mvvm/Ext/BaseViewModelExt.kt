package com.zmin.mvvm.Ext

import androidx.lifecycle.viewModelScope
import com.zmin.mvvm.base.viewmodel.BaseViewModel
import com.zmin.mvvm.network.error.AppException
import com.zmin.mvvm.network.ApiResponse
import com.zmin.mvvm.network.interceptor.logInterceptor.until.loge
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import me.hgj.jetpackmvvm.network.ExceptionHandle


/**
 * @author: ZhangMin
 * @date: 2020/11/10 17:12
 * @version: 1.0
 * @desc:   BaseViewModel拓展类
 */


/**
 * 这个拓展吧网络请求数据后, 做了一些常规操作.
 * 1. 先显示loading 弹框
 * 2. 开始网络请求
 * 3.  隐藏loading  弹窗
 * 4.  比如网络出错, 服务器出错. --> 错误的回调
 * 5.  有数据回来, 判断数据是否正常  --> 正常回调 或者异常回调
 *
 * 以前写代码的时候这些都需要些, 现在这些被封装了, 大大减少的代码量.
 */
fun <T> BaseViewModel.request(
    block: suspend () -> ApiResponse<T>,
    success: (T) -> Unit,
    error: (AppException) -> Unit = {},
    isShowDialog: Boolean = false,
    loadingMessage: String = "请求网络中..."
): Job {
    //如果需要弹窗 通知Activity/fragment弹窗
    // viewModelScope.launch
    return viewModelScope.launch {
        runCatching {
            if (isShowDialog) {
                loadingChange.showDialog.postValue(loadingMessage)
            }
            //请求体
            block()
        }.onSuccess {
            //网络请求成功 关闭弹窗
            loadingChange.dismissDialog.postValue(false)
            runCatching {
                //校验请求结果码是否正确，不正确会抛出异常走下面的onFailure
                executeResponse(it) { t -> success(t) }
            }.onFailure { e ->
                //打印错误消息
                e.message?.loge()
                //失败回调
                error(ExceptionHandle.handleException(e))
            }
        }.onFailure {
            //网络请求异常 关闭弹窗
            loadingChange.dismissDialog.postValue(false)
//            //打印错误消息
            it.message?.loge()
            //失败回调
            error(ExceptionHandle.handleException(it))
        }
    }
}

suspend fun <T> executeResponse(
    response: ApiResponse<T>,
    success: suspend CoroutineScope.(T) -> Unit
) {
    coroutineScope {
        if (response.isSucces()) {
            success(response.getResponseData())  //这里才是正的执行成功的方法
        } else throw AppException(  //如果没有返回正确的码, 这里会做个异常
            response.getResponseCode(),
            response.getResponseMsg(),
            response.getResponseMsg()
        )
    }
}
