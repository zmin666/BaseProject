package com.zmin.baseproject.ui.fragment

import android.os.Bundle
import com.zmin.baseproject.R
import com.zmin.baseproject.databinding.ActivityMainBinding
import com.zmin.baseproject.databinding.FragmentHomeBinding
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
class HomeFragment : BaseFragment<BaseViewModel, FragmentHomeBinding>() {
    override fun layoutId(): Int = R.layout.fragment_home

    override fun initView(savedInstanceState: Bundle?) {

    }
}