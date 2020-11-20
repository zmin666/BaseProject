package com.zmin.baseproject.app.network

import com.zmin.baseproject.data.request.GetBodyRequest
import com.zmin.baseproject.data.response.AriticleResponse
import com.zmin.baseproject.data.response.base.BaseResponse
import com.zmin.baseproject.data.response.base.ApiPagerResponse
import com.zmin.baseproject.data.response.base.BodyTest
import okhttp3.RequestBody
import retrofit2.http.*

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
    suspend fun getAritrilList(@Path("page") pageNo: Int): BaseResponse<ApiPagerResponse<ArrayList<AriticleResponse>>>


    /**
     * 获取身体检测数据
     */
    @Multipart
    @JvmSuppressWildcards
    @POST("https://api-cn.faceplusplus.com/humanbodypp/v1/skeleton")
    suspend fun getBodyData(@PartMap requestBodyMap: Map<String, RequestBody>): BaseResponse<BodyTest>
}