package com.siqiyan.lightlu.eyepetizercode.utils

/**
 * 创建日期：2018/7/16 on 00:34
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean
    fun onItemDismiss(position: Int)
}