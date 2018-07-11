package com.siqiyan.lightlu.eyepetizercode.utils

import android.content.Context

/**
 * 创建日期：2018/7/11 on 22:13
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class DensityUtil {
    companion object {
        fun dip2px(context:Context,dpValue:Float):Int{
            val scale = context.resources.displayMetrics.density
            return (dpValue * scale + 0.5f).toInt()
        }

        fun px2dip(context: Context, pxValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (pxValue / scale + 0.5f).toInt()
        }
    }

}