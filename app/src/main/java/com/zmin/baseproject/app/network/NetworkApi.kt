package com.zmin.baseproject.app.network

import com.google.gson.GsonBuilder
import com.zmin.baseproject.app.network.net.BaseNetworkApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * @author: ZhangMin
 * @date: 2020/11/10 17:54
 * @version: 1.0
 * @desc:
 */

//双重校验锁式-单例 封装NetApiService 方便直接快速调用简单的接口
val apiService: ApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    NetworkApi.INSTANCE.getApi(ApiService::class.java, ApiService.SERVER_URL)
}

class NetworkApi : BaseNetworkApi() {

    companion object { //单例模式
        val INSTANCE: NetworkApi by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkApi()
        }
    }


    //配置OKHttpClient  网络的 缓存信息, 日志, 超时时间等
    override fun setHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        return builder.apply {
//            //设置缓存配置 缓存最大10M
//            cache(Cache(File(appContext.cacheDir, "cxk_cache"), 10 * 1024 * 1024))
//            //添加Cookies自动持久化
//            cookieJar(cookieJar)
//            //示例：添加公共heads 注意要设置在日志拦截器之前，不然Log中会不显示head信息
//            addInterceptor(MyHeadInterceptor())
//            //添加缓存拦截器 可传入缓存天数，不传默认7天
//            addInterceptor(CacheInterceptor())
//            // 日志拦截器
//            addInterceptor(LogInterceptor())
            //超时时间 连接、读、写
            connectTimeout(10, TimeUnit.SECONDS)
            readTimeout(5, TimeUnit.SECONDS)
            writeTimeout(5, TimeUnit.SECONDS)
        }
    }


    // 配置retrofit的内容
    override fun setRetrofitBuilder(retrofitBuilder: Retrofit.Builder): Retrofit.Builder {
        return retrofitBuilder.apply {
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        }
    }
}