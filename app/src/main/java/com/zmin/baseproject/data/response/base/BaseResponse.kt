package com.zmin.baseproject.data.response.base

import com.zmin.mvvm.network.ApiResponse

/**
 * @author: ZhangMin
 * @date: 2020/11/10 17:49
 * @version: 1.0
 * @desc:  服务器返回数据的基类 可以自己在这里包装一层
 */
data class BaseResponse<T>(var errorCode: Int, var errorMsg: String, var data: T) : ApiResponse<T>() {

    // 这里是示例，wanandroid 网站返回的 错误码为 0 就代表请求成功，请你根据自己的业务需求来改变
    override fun isSucces() = errorCode == 0

    override fun getResponseCode() = errorCode

    override fun getResponseData() = data

    override fun getResponseMsg() = errorMsg
}