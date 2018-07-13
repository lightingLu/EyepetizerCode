package com.siqiyan.lightlu.eyepetizercode.notify

import android.content.Intent
import android.graphics.Typeface
import com.siqiyan.lightlu.eyepetizercode.R
import com.siqiyan.lightlu.eyepetizercode.base.BaseFragment
import com.siqiyan.lightlu.eyepetizercode.search.SearchActivity
import kotlinx.android.synthetic.main.notify_fragment.*

/**
 * 创建日期：2018/7/8 on 22:13
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class NotifyFragment :BaseFragment() {
    override fun initDate() =iv_search.setOnClickListener({
        v ->  var intent =Intent(activity,SearchActivity::class.java)
        startActivity(intent)
    })

    override fun initView() {
        tv_bar_title.typeface = Typeface.createFromAsset(activity!!.assets, "fonts/Lobster-1.4.otf")

    }


    override fun getLayoutId()= R.layout.notify_fragment
}