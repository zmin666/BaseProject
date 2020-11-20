package com.zmin.baseproject.viewmodel.request

import androidx.lifecycle.MutableLiveData
import com.zmin.baseproject.app.network.HttpRequestCoroutine
import com.zmin.baseproject.data.request.GetBodyRequest
import com.zmin.baseproject.data.response.AriticleResponse
import com.zmin.baseproject.data.response.base.BaseResponse
import com.zmin.baseproject.data.response.base.BodyTest
import com.zmin.baseproject.data.response.base.ListDataUiState
import com.zmin.mvvm.Ext.request
import com.zmin.mvvm.base.viewmodel.BaseViewModel
import okhttp3.RequestBody
import retrofit2.http.PartMap


class RequesBodyViewModel : BaseViewModel() {

    //mvvm中ViewModel持有MutableLiveData然后设置value.就可以动态改变view
    //首页文章列表响应数据
    var pageNo = 0
    var bodyDataState: MutableLiveData<BodyTest> = MutableLiveData()

    /**
     * 获取首页文章列表数据
     * @param isRefresh 是否是刷新，即第一页
     */
    fun getBodyData(requestBodyMap: Map<String, RequestBody>) {
        request({ HttpRequestCoroutine.getBodyData(requestBodyMap) }, {
            //请求成功
            bodyDataState.value = it
        }, {

        }, true)
    }

}