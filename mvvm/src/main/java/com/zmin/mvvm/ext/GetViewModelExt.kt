package com.zmin.mvvm.ext

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.zmin.mvvm.base.BaseApp
import com.zmin.mvvm.base.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType

/**
 * @author: ZhangMin
 * @date: 2020/11/10 11:40
* @version: 1.0
* @desc: 获取ViewModel 拓展方法
*/


/**
 * 获取当前类绑定的泛型ViewModel-class
 */
@Suppress("UNCHECKED_CAST")
fun <VM> getVmClass(obj: Any): VM {
    //获取父类,得到构造方法,取第一个构造方法类. 转成ViewModel
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}



inline fun <reified VM : BaseViewModel> Fragment.getAppViewModel(): VM {
    (this.requireActivity().application as? BaseApp).let {
        if (it == null) {
            throw NullPointerException("你的Application没有继承框架自带的BaseApp类，暂时无法使用getAppViewModel该方法")
        } else {
            return it.getAppViewModelProvider().get(VM::class.java)
        }
    }
}


/**
 * 在Activity中得到Application上下文的ViewModel
 */
inline fun <reified VM : BaseViewModel> AppCompatActivity.getAppViewModel(): VM {
    (this.application as? BaseApp).let {
        if (it == null) {
            throw NullPointerException("你的Application没有继承框架自带的BaseApp类，暂时无法使用getAppViewModel该方法")
        } else {
            return it.getAppViewModelProvider().get(VM::class.java)
        }
    }
}
