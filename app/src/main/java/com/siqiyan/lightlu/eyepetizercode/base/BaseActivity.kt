package com.siqiyan.lightlu.eyepetizercode.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * 创建日期：2018/7/8 on 21:06
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
abstract class  BaseActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
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