package com.zmin.baseproject.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zmin.baseproject.ui.fragment.*

/**
 * @author: ZhangMin
 * @date: 2020/11/12 14:55
 * @version: 1.0
 * @desc: 主页viewpager的适配器
 */
class MainViewpagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                return HomeFragment()
            }
            1 -> {
                return ProjectFragment()
            }
            2 -> {
                return SquareFragment()
            }
            3 -> {
                return WechatFragment()
            }
            4 -> {
                return MineFragment()
            }
            else -> {
                return HomeFragment()
            }
        }
    }
}