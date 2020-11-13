package com.zmin.baseproject.app.network

import com.zmin.baseproject.data.response.AriticleResponse
import com.zmin.baseproject.data.response.base.BaseResponse
import com.zmin.baseproject.data.response.base.ApiPagerResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

/**
 * @author: ZhangMin
 * @date: 2020/11/10 17:48
 * @version: 1.0
 * @desc:  网络请求模块
 */


//顶部声明
val HttpRequestCoroutine: HttpRequestManger by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    HttpRequestManger()
}


class HttpRequestManger {

    //suspend 协程方法标识 一般用于等待操作, 切换线程; async创建协程, await挂起
    //BaseResponse封装网络请求方法 里面的泛型其实只是data.
    //ApiPagerResponse<ArrayList<AriticleResponse>>   --> 这个只是返回数据中的 data数据. 不是所有数据哦.
    suspend fun getHomeData(): BaseResponse<ApiPagerResponse<ArrayList<AriticleResponse>>> {
        return withContext(Dispatchers.IO) {
            val data = async { apiService.getAritrilList(0) }
            data.await()
        }
    }
}