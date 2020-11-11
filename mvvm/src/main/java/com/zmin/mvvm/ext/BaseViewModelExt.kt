package com.zmin.mvvm.ext

import androidx.lifecycle.viewModelScope
import com.zmin.mvvm.base.viewmodel.BaseViewModel
import com.zmin.mvvm.network.AppException
import com.zmin.mvvm.network.BaseResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


/**
 * @author: ZhangMin
 * @date: 2020/11/10 17:12
 * @version: 1.0
 * @desc:   BaseViewModel拓展类
 */


fun <T> BaseViewModel.request(
    block: suspend () -> BaseResponse<T>,
    success: (T) -> Unit,
    error: (AppException) -> Unit = {},
    isShowDialog: Boolean = false,
    loadingMessage: String = "请求网络中..."
): Job {
    //如果需要弹窗 通知Activity/fragment弹窗
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
//                e.message?.loge()
//                //失败回调
//                error(ExceptionHandle.handleException(e))
            }
        }.onFailure {
            //网络请求异常 关闭弹窗
            loadingChange.dismissDialog.postValue(false)
//            //打印错误消息
//            it.message?.loge()
//            //失败回调
//            error(ExceptionHandle.handleException(it))
        }
    }
}

suspend fun <T> executeResponse(
    response: BaseResponse<T>,
    success: suspend CoroutineScope.(T) -> Unit
) {
    coroutineScope {
        if (response.isSucces()) {
            success(response.getResponseData())
        } else throw AppException(
            response.getResponseCode(),
            response.getResponseMsg(),
            response.getResponseMsg()
        )
    }
}
