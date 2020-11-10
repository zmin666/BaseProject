package com.zmin.mvvm.until

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Build
import android.preference.PreferenceManager
import android.view.View
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.Utils
import com.kingja.loadsir.core.LoadService
import com.tencent.mmkv.MMKV
import com.zmin.mvvm.R
import java.lang.reflect.InvocationTargetException
import kotlin.math.roundToInt


object SettingUtil {

    /**
     * 获取当前主题颜色
     */
    fun getColor(context: Context): Int {
        val setting = PreferenceManager.getDefaultSharedPreferences(context)
        val defaultColor = ContextCompat.getColor(context, R.color.colorPrimary)
        val color = setting.getInt("color", defaultColor)
        return if (color != 0 && Color.alpha(color) != 255) {
            defaultColor
        } else {
            color
        }
    }

    fun getOneColorStateList(context: Context): ColorStateList {
        val colors = intArrayOf(getColor(context))
        val states = arrayOfNulls<IntArray>(1)
        states[0] = intArrayOf()
        return ColorStateList(states, colors)
    }
}

