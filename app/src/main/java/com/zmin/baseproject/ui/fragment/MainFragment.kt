package com.zmin.baseproject.ui.fragment

import android.os.Bundle
import com.zmin.baseproject.R
import com.zmin.baseproject.databinding.FragmentMainBinding
import com.zmin.baseproject.ui.adapter.MainViewpagerAdapter
import com.zmin.mvvm.base.fragment.BaseFragment
import com.zmin.mvvm.base.viewmodel.BaseViewModel
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * @author: ZhangMin
 * @date: 2020/11/12 14:31
 * @version: 1.0
 * @desc:
 */
class MainFragment : BaseFragment<BaseViewModel, FragmentMainBinding>() {
    override fun layoutId(): Int = R.layout.fragment_main

    override fun initView(savedInstanceState: Bundle?) {
        mainViewPager.apply {
            isUserInputEnabled = false
            offscreenPageLimit = 5
            adapter = MainViewpagerAdapter(this@MainFragment)
        }

        btNavigation.apply { //底部导航页面
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_main -> {
                        mainViewPager.setCurrentItem(0, false)
                        true
                    }
                    R.id.menu_project -> {
                        mainViewPager.setCurrentItem(1, false)
                        true
                    }
                    R.id.menu_system -> {
                        mainViewPager.setCurrentItem(2, false)
                        true
                    }
                    R.id.menu_public -> {
                        mainViewPager.setCurrentItem(3, false)
                        true
                    }
                    R.id.menu_me -> {
                        mainViewPager.setCurrentItem(4, false)
                        true
                    }
                    else -> true
                }
            }
            itemIconTintList = SettingUtil.getColorStateList(SettingUtil.getColor(context))
            itemTextColor = SettingUtil.getColorStateList(context)
            setTextSize(12F)
        }
    }
}