package com.zmin.mvvm.network

/**
 * @author: ZhangMin
 * @date: 2020/11/10 17:21
 * @version: 1.0
 * @desc:  自定义错误异常
 */
class AppException : Exception {
    var errorMsg: String = "请求失败, 请稍后再试"
    var errCode: Int = 0
    var errorLog: String = ""

    constructor(error: Error, e: Throwable?) {
        errCode = error.getKey()
    }

    constructor(errCode: Int, errorMsg: String?, errorLog: String? = "") : super(errorMsg) {
        this.errorMsg = errorMsg ?: "请求失败, 请稍后再试"
        this.errCode = errCode
        this.errorLog = errorLog ?: this.errorMsg
    }
}