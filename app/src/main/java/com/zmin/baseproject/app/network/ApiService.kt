package com.zmin.baseproject.app.network

import com.zmin.baseproject.data.model.bean.AriticleResponse
import com.zmin.baseproject.data.model.bean.base.ApiBaseResponse
import com.zmin.baseproject.data.model.bean.base.ApiPagerResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/23
 * 描述　: 网络API
 */
interface ApiService {

    //https://wanandroid.com/article/list/0/json

    companion object {
        const val SERVER_URL = "https://wanandroid.com/"
        const val SERVER_URL1 = "https://wanandroid.com/"
    }

    /**
     * 获取首页文章数据
     */
    @GET("article/list/{page}/json")
    suspend fun getAritrilList(@Path("page") pageNo: Int): ApiBaseResponse<ApiPagerResponse<ArrayList<AriticleResponse>>>

}