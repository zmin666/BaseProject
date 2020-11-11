package com.zmin.baseproject.app.network

import com.zmin.baseproject.data.model.bean.AriticleResponse
import com.zmin.baseproject.data.model.bean.base.ApiBaseResponse
import com.zmin.baseproject.data.model.bean.base.ApiPagerResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

/**
 * @author: ZhangMin
 * @date: 2020/11/10 17:48
 * @version: 1.0
 * @desc:  网络请求模块
 */

val HttpRequestCoroutine: HttpRequestManger by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    HttpRequestManger()
}


class HttpRequestManger {

    suspend fun getHomeData(): ApiBaseResponse<ApiPagerResponse<ArrayList<AriticleResponse>>> {
        return withContext(Dispatchers.IO) {
            val data = async { apiService.getAritrilList(0) }
            data.await()
        }
    }
}