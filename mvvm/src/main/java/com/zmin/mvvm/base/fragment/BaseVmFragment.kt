package com.zmin.mvvm.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zmin.mvvm.base.viewmodel.BaseViewModel
import com.zmin.mvvm.ext.getVmClass

/**
 * @author: ZhangMin
 * @date: 2020/11/10 10:40
 * @version: 1.0
 * @desc:
 */
abstract class BaseVmFragment<VM : BaseViewModel> : Fragment() {

    private var isFirst: Boolean = true

    lateinit var mViewModel: VM

    lateinit var mActivity: AppCompatActivity


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId(), container, false)
    }

    abstract fun layoutId(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isFirst = true
        mViewModel = createViewModel()
        initView(savedInstanceState)
        createObserver()
        onVisible()
        registerDefUIChange()
        initData()
    }

    override fun onResume() {
        super.onResume()
        onVisible()
    }

    abstract fun initData()

    private fun registerDefUIChange() {
        mViewModel.UiLoadingChange().showDialog.observe(viewLifecycleOwner, Observer {
            showLoading()
        })

        mViewModel.UiLoadingChange().dismissDialog.observe(viewLifecycleOwner, Observer {
            dismissLoading()
        })

    }

    abstract fun showLoading(message: String = "请求网络中...")

    abstract fun dismissLoading()

    /**
     * 是否需要懒加载?
     * 只有第一次进入的时候 要等跳转动画完成后再加载
     * 延迟加载0.12秒加载 避免fragment跳转动画和渲染ui同时进行，出现些微的小卡顿
     */
    private fun onVisible() {
        if (lifecycle.currentState == Lifecycle.State.STARTED && isFirst) {
            view?.postDelayed({
                lazyLoadData()
                //todo 网络变化监听
            }, 120)
        }
    }

    abstract fun lazyLoadData()

    abstract fun createObserver()

    abstract fun initView(savedInstanceState: Bundle?)

    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClass(this))
    }
}