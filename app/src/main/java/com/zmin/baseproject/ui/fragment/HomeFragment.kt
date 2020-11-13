package com.zmin.baseproject.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.zmin.baseproject.R
import com.zmin.baseproject.databinding.FragmentHomeBinding
import com.zmin.baseproject.ui.adapter.AriticleAdapter
import com.zmin.baseproject.viewmodel.request.RequestHomeViewModel
import com.zmin.mvvm.base.fragment.BaseFragment
import com.zmin.mvvm.base.viewmodel.BaseViewModel
import kotlinx.android.synthetic.main.include_list.*
import kotlinx.android.synthetic.main.include_toolbar.*

/**
 * @author: ZhangMin
 * @date: 2020/11/12 14:31
 * @version: 1.0
 * @desc:
 */
class HomeFragment : BaseFragment<BaseViewModel, FragmentHomeBinding>() {

    //适配器
    //todo  这个适配器要加强学习
    private val articleAdapter: AriticleAdapter by lazy { AriticleAdapter(arrayListOf(), true) }

    //请求数据ViewModel
    private val requestHomeViewModel: RequestHomeViewModel by viewModels()


    override fun layoutId(): Int = R.layout.fragment_home

    override fun initView(savedInstanceState: Bundle?) {
        toolbar.run {
            title = "首页"
            inflateMenu(R.menu.home_menu) //搜索框
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.home_search -> {
                        ToastUtils.showShort("点击了搜索框")
                    }
                }
                true
            }
        }

        recyclerView.run {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = articleAdapter
            isNestedScrollingEnabled = true
        }

        swipeRefresh.run {
            //设置主题颜色
            setColorSchemeColors(SettingUtil.getColor(context))
            setOnRefreshListener {
                requestHomeViewModel.getHomeData(true)
            }
        }
    }

    override fun lazyLoadData() {
        //请求文章列表数据
        requestHomeViewModel.getHomeData(true)
    }

    override fun createObserver() {
        requestHomeViewModel.run {
            homeDataState.observe(viewLifecycleOwner, Observer {
                if (it.isSuccess) {
                    if (it.isFirstEmpty) {
                        articleAdapter.setList(it.listData)
                    } else {
                        articleAdapter.addData(it.listData)
                    }
                }
            })
        }

    }
}