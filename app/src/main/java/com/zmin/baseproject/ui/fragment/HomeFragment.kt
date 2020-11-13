package com.zmin.baseproject.ui.fragment

import SettingUtil
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ToastUtils
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.yanzhenjie.recyclerview.SwipeRecyclerView
import com.zmin.baseproject.R
import com.zmin.baseproject.app.ext.initFooter
import com.zmin.baseproject.app.ext.loadServiceInit
import com.zmin.baseproject.databinding.FragmentHomeBinding
import com.zmin.baseproject.ui.adapter.AriticleAdapter
import com.zmin.baseproject.ui.weight.recyclerview.SpaceItemDecoration
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


    //界面状态管理者
    private lateinit var loadsir: LoadService<Any>


    //请求数据ViewModel
    private val requestHomeViewModel: RequestHomeViewModel by viewModels()


    override fun layoutId(): Int = R.layout.fragment_home

    override fun initView(savedInstanceState: Bundle?) {
        //状态页配置
        loadsir = LoadSir.getDefault().register(view) {
            //点击重试时触发的操作
            requestHomeViewModel.getHomeData(true)
        }
        loadsir.showSuccess()

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
            addItemDecoration(SpaceItemDecoration(0, ConvertUtils.dp2px(8f), false))
            initFooter(SwipeRecyclerView.LoadMoreListener { requestHomeViewModel.getHomeData(false) })
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
                swipeRefresh.isRefreshing = false
                recyclerView.loadMoreFinish(it.isEmpty, it.hasMore)
                if (it.isSuccess) {
                    if (it.isRefresh) {
                        articleAdapter.setList(it.listData)
                    } else {
                        articleAdapter.addData(it.listData)
                    }
                }
            })
        }

    }
}