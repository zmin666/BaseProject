package com.zmin.mvvm.network.interceptor

import com.blankj.utilcode.util.DeviceUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * @author: ZhangMin
 * @date: 2020/11/11 14:31
 * @version: 1.0
 * @desc:  公用的头部参数信息.
 * 因为这是在依赖中, 可以配置, 手机信息之类与业务无关的参数
 * */
class CommonHeadInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        builder.addHeader("device", "Android").build()
        builder.addHeader("model",  DeviceUtils.getModel()).build()
        builder.addHeader("system",  DeviceUtils.getSDKVersionName()).build()
        return chain.proceed(builder.build())
    }
}