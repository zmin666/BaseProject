package com.zmin.mvvm.callback.livedataExtand

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.*


/**
 *
 * 如此可确保：
 * 1.一条消息能被多个观察者消费
 * 2.延迟期结束，不再能够收到旧消息的推送
 * 3.并且旧消息在延迟期结束时能从内存中释放，避免内存溢出等问题
 * 4.让非入侵设计成为可能，遵循开闭原则
 * 用于限制从 Activity/Fragment 推送数据，推送数据务必通过唯一可信源来分发，
 * 如果这样说还不理解，详见：
 * https://xiaozhuanlan.com/topic/6719328450 和 https://xiaozhuanlan.com/topic/0168753249
 *
 * 继承MutableLiveData 加入一些变量控制, 防止数据倒灌问题
 *
 */
open class EventLiveData<T> : MutableLiveData<T>() {
    private var isCleaning = false
    private var hasHandled = true
    private var isDelaying = false
    protected var DELAY_TO_CLEAR_EVENT = 1000
    private val mTimer = Timer()
    private var mTask: TimerTask? = null
    protected var isAllowNullValue = false
    protected var isAllowToClear = true
    override fun observe(
        owner: LifecycleOwner,
        observer: Observer<in T>
    ) {
        super.observe(owner, Observer {
            if (isCleaning) {
                hasHandled = true
                isDelaying = false
                isCleaning = false
                return@Observer
            }
            if (!hasHandled) {
                hasHandled = true
                isDelaying = true
                observer.onChanged(it)
            } else if (isDelaying) {
                observer.onChanged(it)
            }
        })
    }

    /**
     * UnPeekLiveData 主要用于表现层的 页面转场 和 页面间通信 场景下的非粘性消息分发，
     * 出于生命周期安全等因素的考虑，不建议使用 observeForever 方法，
     *
     *
     * 对于数据层的工作，如有需要，可结合实际场景使用 MutableLiveData 或 kotlin flow。
     *
     * @param observer
     */
    override fun observeForever(observer: Observer<in T?>) {
        throw IllegalArgumentException("Do not use observeForever for communication between pages to avoid lifecycle security issues")
    }

    /**
     * 重写的 setValue 方法，默认不接收 null
     * 可通过 Builder 配置允许接收
     * 可通过 Builder 配置消息延时清理的时间
     *
     *
     * override setValue, do not receive null by default
     * You can configure to allow receiving through Builder
     * And also, You can configure the delay time of message clearing through Builder
     *
     * @param value
     */
    override fun setValue(value: T?) {
        if (!isCleaning && !isAllowNullValue && value == null) {
            return
        }
        hasHandled = false
        isDelaying = false
        super.setValue(value)
        if (mTask != null) {
            mTask!!.cancel()
            mTimer.purge()
        }
        if (value != null) {
            mTask = object : TimerTask() {
                override fun run() {
                    clear()
                }
            }
            mTimer.schedule(mTask, DELAY_TO_CLEAR_EVENT.toLong())
        }
    }

    private fun clear() {
        if (isAllowToClear) {
            isCleaning = true
            super.postValue(null)
        } else {
            hasHandled = true
            isDelaying = false
        }
    }

}