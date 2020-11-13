package com.zmin.baseproject.app.ext

import com.chad.library.adapter.base.BaseQuickAdapter
import com.yanzhenjie.recyclerview.SwipeRecyclerView
import com.zmin.baseproject.ui.weight.recyclerview.DefineLoadMoreView
import com.zmin.mvvm.base.BaseApp


//设置适配器的列表动画
fun BaseQuickAdapter<*, *>.setAdapterAnimation(mode: Int) {
    //等于0，关闭列表动画 否则开启
    if (mode == 0) {
        this.animationEnable = false
    } else {
        this.animationEnable = true
        this.setAnimationWithDefault(BaseQuickAdapter.AnimationType.values()[mode - 1])
    }
}


fun SwipeRecyclerView.initFooter(loadmoreListener: SwipeRecyclerView.LoadMoreListener): DefineLoadMoreView {
    val footerView = DefineLoadMoreView(BaseApp.application)
    //给尾部设置颜色
    footerView.setLoadViewColor(SettingUtil.getOneColorStateList(BaseApp.application))
    //设置尾部点击回调
    footerView.setmLoadMoreListener(SwipeRecyclerView.LoadMoreListener {
        footerView.onLoading()
        loadmoreListener.onLoadMore()
    })
    this.run {
        //添加加载更多尾部
        addFooterView(footerView)
        setLoadMoreView(footerView)
        //设置加载更多回调
        setLoadMoreListener(loadmoreListener)
    }
    return footerView
}