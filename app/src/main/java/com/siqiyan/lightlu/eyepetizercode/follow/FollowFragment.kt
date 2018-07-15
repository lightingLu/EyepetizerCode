package com.siqiyan.lightlu.eyepetizercode.follow

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.bumptech.glide.Glide
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.siqiyan.lightlu.eyepetizercode.R
import com.siqiyan.lightlu.eyepetizercode.base.BaseFragment
import com.siqiyan.lightlu.eyepetizercode.home.adapter.MyMultiTypeAdapter
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import com.siqiyan.lightlu.eyepetizercode.search.SearchActivity
import kotlinx.android.synthetic.main.follow_fragment.*

/**
 * 创建日期：2018/7/8 on 22:13
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class FollowFragment :BaseFragment() {
    private var presenter: FollowContract.FollowPresenter? = null
    var adapter: MyMultiTypeAdapter? = null
    var isRefresh: Boolean = false
    var start_num: Int = 0
    var num: Int = 10
    var follow: Boolean = false
    var startId: Int = 0

    override fun initDate() {
    }

    override fun initView() {
        swipeRefreshLayout.refreshHeader=ClassicsHeader(activity)
        swipeRefreshLayout.refreshFooter=ClassicsFooter(activity)
        tv_bar_title.typeface= Typeface.createFromAsset(activity!!.assets,"fonts/Lobster-1.4.otf")
        swipeRefreshLayout.isEnableAutoLoadmore=true
        swipeRefreshLayout.setOnRefreshListener {
            isRefresh=true
            start_num=0
            follow=false
            startId=0
            initDate()
        }
        swipeRefreshLayout.setOnLoadmoreListener{
            isRefresh=false
            initDate()
        }
        recyclerview.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                when (newState) {
                    2 -> {
                        Glide.with(activity!!.applicationContext).pauseRequests()
                    }
                    0 -> {
                        Glide.with(activity!!.applicationContext).resumeRequests()
                    }
                    1 -> {
                        Glide.with(activity!!.applicationContext).resumeRequests()
                    }
                }


            }

        })
        iv_search.setOnClickListener {
            var intent = Intent(activity, SearchActivity::class.java)
            startActivity(intent)
            activity!!.overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_in_top)
        }

        val list = ArrayList<Result.ItemList>()
        val layoutManager = LinearLayoutManager(this.activity!!)
        recyclerview.layoutManager = layoutManager
        adapter = MyMultiTypeAdapter(list, activity!!)
        recyclerview.adapter = adapter
        isRefresh = false
    }


    override fun getLayoutId()= R.layout.follow_fragment
}