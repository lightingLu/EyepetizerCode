package com.siqiyan.lightlu.eyepetizercode.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * 创建日期：2018/7/8 on 21:12
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
abstract class BaseFragment :Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       var view:View =inflater!!.inflate(getLayoutId(),null,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPresenter()
        initEvent()
        initView()
        initDate()
    }

    abstract fun initDate()

    abstract fun initView()

    internal open  fun initEvent()= Unit

    internal open fun initPresenter()= Unit

    abstract fun getLayoutId(): Int
}