package com.zmin.mvvm.network

/**
 * @author: ZhangMin
 * @date: 2020/11/10 17:14
 * @version: 1.0
 * @desc:
 */
abstract class ApiResponse<T> {

    //抽象方法，用户的基类继承该类时，需要重写该方法
    abstract fun isSucces(): Boolean

    abstract fun getResponseData(): T

    abstract fun getResponseCode(): Int

    abstract fun getResponseMsg(): String

}