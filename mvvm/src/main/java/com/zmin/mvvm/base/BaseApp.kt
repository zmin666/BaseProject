package com.zmin.mvvm.base

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

/**
 * @author: ZhangMin
 * @date: 2020/11/10 14:27
 * @version: 1.0
 * @desc:
 */
class BaseApp : Application(), ViewModelStoreOwner {

    private lateinit var mAppViewModel: ViewModelStore

    private var mFactory: ViewModelProvider.Factory? = null

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModel
    }

    override fun onCreate() {
        super.onCreate()
        mAppViewModel = ViewModelStore()
    }

    fun getAppViewModelProvider(): ViewModelProvider {
        return ViewModelProvider(this, this.getAppFactory())
    }

    private fun getAppFactory(): ViewModelProvider.Factory {
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this)
        }
        return mFactory as ViewModelProvider.Factory
    }

}