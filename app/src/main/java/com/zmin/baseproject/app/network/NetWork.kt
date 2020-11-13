package com.zmin.baseproject.app.network

import com.zmin.mvvm.network.client.NetworkApi

/**
 * @author: ZhangMin
 * @date: 2020/11/11 14:48
 * @version: 1.0
 * @desc: 获取retrofit
 */
//双重校验锁式-单例 封装NetApiService 方便直接快速调用简单的接口
// NetworkApi 是封装好的Retrofit. 这里获取请求的service
// 这里可以建多个  对应多个请求地址
val apiService: ApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    NetworkApi.INSTANCE.getApi(ApiService::class.java, ApiService.SERVER_URL)
}