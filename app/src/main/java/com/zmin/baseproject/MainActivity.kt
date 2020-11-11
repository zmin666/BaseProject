package com.zmin.baseproject

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.zmin.baseproject.databinding.ActivityMainBinding
import com.zmin.baseproject.ui.adapter.ArticleAdapter
import com.zmin.baseproject.viewmodel.request.RequestHomeViewModel
import com.zmin.mvvm.base.activity.BaseActivity
import com.zmin.mvvm.base.viewmodel.BaseViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<BaseViewModel, ActivityMainBinding>() {


    //请求数据ViewModel
    private val requestHomeViewModel: RequestHomeViewModel = RequestHomeViewModel()

    override fun layoutId(): Int = R.layout.activity_main

    private val mAdapter by lazy {
        ArticleAdapter()
    }

    override fun initView(savedInstanceState: Bundle?) {
        mDataBind.proxy = ProxyClick()
        rvList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
        requestHomeViewModel.getHomeData()
    }

    override fun createObserver() {
        requestHomeViewModel.run {
            homeDataState.observe(this@MainActivity, Observer {
                Log.i("....dfadsf.....", "createObserver: ")
                mAdapter.setData(it)
            })
        }
    }

    inner class ProxyClick {
        fun click() = requestHomeViewModel.getHomeData()
    }
}