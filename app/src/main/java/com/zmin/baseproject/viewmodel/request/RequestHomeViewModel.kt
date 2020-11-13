package com.zmin.baseproject.viewmodel.request

import androidx.lifecycle.MutableLiveData
import com.zmin.baseproject.app.network.HttpRequestCoroutine
import com.zmin.baseproject.data.response.AriticleResponse
import com.zmin.baseproject.data.response.base.ListDataUiState
import com.zmin.mvvm.Ext.request
import com.zmin.mvvm.base.viewmodel.BaseViewModel

/**
 * @author: ZhangMin
 * @date: 2020/11/10 17:01
 * @version: 1.0
 * @desc: ViewModel数据模型 --> 里面封装可观察数据 MutableLiveData
 * BaseViewModel里面封装了一个 加载前状态,加载完状态.
 * RequestHomeViewModel 网络响应回来的数据, 当然也是可观察的.
 * 这个类会去调用HttpRequestCoroutine网络请求数据
 */
class RequestHomeViewModel : BaseViewModel() {

    //mvvm中ViewModel持有MutableLiveData然后设置value.就可以动态改变view
    //首页文章列表响应数据
    var pageNo = 0
    var homeDataState: MutableLiveData<ListDataUiState<AriticleResponse>> = MutableLiveData()

    /**
     * 获取首页文章列表数据
     * @param isRefresh 是否是刷新，即第一页
     */
    fun getHomeData(isRefresh: Boolean) {
        if (isRefresh) {
            pageNo = 0;
        }
        request({ HttpRequestCoroutine.getHomeData(pageNo) }, {
            //请求成功
            pageNo++
            val listDataUiState =
                ListDataUiState(
                    isSuccess = true,
                    isRefresh = isRefresh,
                    isEmpty = it.isEmpty(),
                    hasMore = it.hasMore(),
                    isFirstEmpty = isRefresh && it.isEmpty(),
                    listData = it.datas
                )
            //数据得到后, 赋值给liveData
            // homeDataState在observer后更新ui
            homeDataState.value = listDataUiState
        }, {
            //请求失败
            val listDataUiState =
                ListDataUiState(
                    isSuccess = false,
                    errMessage = it.errorMsg,
                    isRefresh = isRefresh,
                    listData = arrayListOf<AriticleResponse>()
                )
            //数据得到后, 赋值给liveData
            // homeDataState在observer后更新ui
            homeDataState.value = listDataUiState
        }, true)
    }

}