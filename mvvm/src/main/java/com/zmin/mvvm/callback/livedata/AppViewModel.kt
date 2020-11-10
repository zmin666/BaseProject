package com.zmin.mvvm.callback.livedata

import com.zmin.mvvm.base.viewmodel.BaseViewModel
import com.zmin.mvvm.callback.livedataExtand.UnPeekLiveData

/**
 * @author: ZhangMin
 * @date: 2020/11/10 14:20
 * @version: 1.0
 * @desc: 全局信息保存
 *        用户信息, 配置设置
 */
class AppViewModel : BaseViewModel() {

    //todo  全局信息保存

    //App的账户信息
    var userinfo = UnPeekLiveData<String>()

    //App主题颜色 中大型项目不推荐以这种方式改变主题颜色，比较繁琐耦合，且容易有遗漏某些控件没有设置主题色
    var appColor = UnPeekLiveData<Int>()

    //App 列表动画
    var appAnimation = UnPeekLiveData<Int>()

    init {
//        //默认值保存的账户信息，没有登陆过则为null
//        userinfo.value = CacheUtil.getUser()
//        //默认值颜色
//        appColor.value = SettingUtil.getColor(appContext)
//        //初始化列表动画
//        appAnimation.value = SettingUtil.getListMode()
    }
}