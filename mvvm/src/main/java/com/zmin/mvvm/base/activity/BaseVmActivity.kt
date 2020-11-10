package com.zmin.mvvm.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zmin.mvvm.base.viewmodel.BaseViewModel
import com.zmin.mvvm.ext.getVmClass

/**
 * @author: ZhangMin
 * @date: 2020/11/10 14:58
 * @version: 1.0
 * @desc:
 */
abstract class BaseVmActivity<VM : BaseViewModel> : AppCompatActivity() {

    private var isUserDb = false

    lateinit var mViewModel: VM

    abstract fun layoutId(): Int

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun showLoading(message: String = "请求网络中...")

    abstract fun dismissLoading()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isUserDb) {
            initDataBind()
        } else {
            setContentView(layoutId())
        }
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        mViewModel = createViewModel()
        registerUiChange()
        initView(savedInstanceState)
        createObserver()
        //todo 网络情况监听
    }

    abstract fun createObserver()

    private fun registerUiChange() {
        mViewModel.UiLoadingChange().showDialog.observe(this, Observer {
            showLoading()
        })

        mViewModel.UiLoadingChange().dismissDialog.observe(this, Observer {
            dismissLoading()
        })
    }

    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClass(this))
    }

    open fun initDataBind() {}

    fun userDataBinding(isUserDb: Boolean) {
        this.isUserDb = isUserDb
    }
}