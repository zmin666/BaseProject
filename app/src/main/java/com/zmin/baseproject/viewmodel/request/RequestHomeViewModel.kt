package com.zmin.baseproject.viewmodel.request

import androidx.lifecycle.MutableLiveData
import com.zmin.baseproject.app.network.HttpRequestCoroutine
import com.zmin.baseproject.data.model.bean.AriticleResponse
import com.zmin.mvvm.base.viewmodel.BaseViewModel
import com.zmin.mvvm.ext.request
import com.zmin.mvvm.model.bean.netbean.ListDataUiState

/**
 * @author: ZhangMin
 * @date: 2020/11/10 17:01
 * @version: 1.0
 * @desc:
 */
class RequestHomeViewModel : BaseViewModel() {

    //首页文章列表数据
    var homeDataState: MutableLiveData<ListDataUiState<AriticleResponse>> = MutableLiveData()

    /**
     * 获取首页文章列表数据
     * @param isRefresh 是否是刷新，即第一页
     */
    fun getHomeData() {
        request({ HttpRequestCoroutine.getHomeData() }, {
            //请求成功
            val listDataUiState =
                ListDataUiState(
                    isSuccess = true,
                    isEmpty = it.isEmpty(),
                    hasMore = it.hasMore(),
                    listData = it.datas
                )
            homeDataState.value = listDataUiState
        }, {
            //请求失败
            val listDataUiState =
                ListDataUiState(
                    isSuccess = false,
                    errMessage = it.errorMsg,
                    listData = arrayListOf<AriticleResponse>()
                )
            homeDataState.value = listDataUiState
        })
    }

}