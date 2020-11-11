package com.zmin.baseproject.app.network.net

import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * @author: ZhangMin
 * @date: 2020/11/10 17:59
 * @version: 1.0
 * @desc: 网络请求构建器基类
 */
abstract class BaseNetworkApi {

    private val okHttpClient: OkHttpClient
        get() {
            var builder = RetrofitUrlManager.getInstance().with(OkHttpClient.Builder())
            builder = setHttpClientBuilder(builder)
            return builder.build()
        }

    abstract fun setHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder

    fun <T> getApi(serviceClass: Class<T>, baseUrl: String): T {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
        return setRetrofitBuilder(retrofitBuilder).build().create(serviceClass)
    }

    abstract fun setRetrofitBuilder(retrofitBuilder: Retrofit.Builder): Retrofit.Builder
}