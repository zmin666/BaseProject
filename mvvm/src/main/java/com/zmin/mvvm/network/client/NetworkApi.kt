package com.zmin.mvvm.network.client

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.google.gson.GsonBuilder
import com.zmin.mvvm.base.BaseApp
import com.zmin.mvvm.network.interceptor.CommonHeadInterceptor
import me.hgj.jetpackmvvm.network.interceptor.logging.LogInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author: ZhangMin
 * @date: 2020/11/11 14:48
 * @version: 1.0
 * @desc:
 */
class NetworkApi  : BaseNetworkApi() {

    companion object { //单例模式
        val INSTANCE: NetworkApi by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkApi()
        }
    }

    //配置OKHttpClient  网络的 缓存信息, 日志, 超时时间等
    override fun setHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        return builder.apply {
            //设置缓存配置 缓存最大10M
//           cache(Cache(File(BaseApp.application.cacheDir, "cxk_cache"), 10 * 1024 * 1024))
            //添加Cookies自动持久化
//            cookieJar(cookieJar)
            //示例：添加公共heads 注意要设置在日志拦截器之前，不然Log中会不显示head信息
            addInterceptor(CommonHeadInterceptor())
            //添加缓存拦截器 可传入缓存天数，不传默认7天
//            addInterceptor(CacheInterceptor())
            // 日志拦截器
            addInterceptor(LogInterceptor())
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

    //App打开一个web，web如何知道我们是否登录了呢？我们可以借助Cookie，
    // 在调用Http请求登陆成功将对应的登陆信息写入到Cookie中，web读取这些信息就知道我们是否登录了
    val cookieJar: PersistentCookieJar by lazy {
        PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(BaseApp.application))
    }
}