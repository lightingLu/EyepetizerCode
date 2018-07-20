package com.siqiyan.lightlu.eyepetizercode.home.view

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import com.bumptech.glide.Glide
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.siqiyan.lightlu.eyepetizercode.R
import com.siqiyan.lightlu.eyepetizercode.base.BaseFragment
import com.siqiyan.lightlu.eyepetizercode.home.DiscoverContract
import com.siqiyan.lightlu.eyepetizercode.home.adapter.MyMultiTypeAdapter
import com.siqiyan.lightlu.eyepetizercode.home.presenter.DiscoverPresenter
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.follow_fragment.*

/**
 * 创建日期：2018/7/9 on 23:56
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class DiscoveryFragment : BaseFragment(), DiscoverContract.DiscoverView {
    private var presenter: DiscoverContract.DiscoverPresenter? = null

    override fun onDiscoverySucc(result: Result) {
        val start = adapter!!.itemCount
        adapter!!.clearAll()
        adapter!!.notifyItemRangeRemoved(0, start)
        adapter!!.addAllData(result.itemList as java.util.ArrayList<Result.ItemList>?)
        adapter!!.notifyItemRangeInserted(0, result.itemList!!.size)
        swipeRefreshLayout.finishRefresh()
        swipeRefreshLayout.isLoadmoreFinished = TextUtils.isEmpty(result.nextPageUrl)
    }

    override fun onDiscoveryFail(error: Throwable?) {
        swipeRefreshLayout.isLoadmoreFinished = false
        swipeRefreshLayout.finishLoadmore()
        swipeRefreshLayout.finishRefresh()
    }

    override fun setPresenter(prsenter2: DiscoverContract.DiscoverPresenter) {
        this.presenter = prsenter2
    }

    var adapter: MyMultiTypeAdapter? = null
    var isRefresh: Boolean = false

    override fun initDate() {
        presenter!!.discover()

    }

    override fun initView() {
        swipeRefreshLayout.isEnableAutoLoadmore = true
        swipeRefreshLayout.refreshHeader = ClassicsHeader(activity)
        swipeRefreshLayout.refreshFooter = ClassicsFooter(activity)
        swipeRefreshLayout.setOnRefreshListener {
            isRefresh = true
            initDate()
        }
        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                //0 表示停止滑动的状态 SCROLL_STATE_IDLE
                //1表示正在滚动，用户手指在屏幕上 SCROLL_STATE_TOUCH_SCROLL
                //2表示正在滑动。用户手指已经离开屏幕 SCROLL_STATE_FLING
                when (newState) {
                    0 -> {
                        Glide.with(activity!!.applicationContext).resumeRequests()
                    }
                    1 -> {
                        Glide.with(activity!!.applicationContext).resumeRequests()
                    }
                    2 -> {
                        Glide.with(activity!!.applicationContext).pauseRequests()
                    }

                }

            }

        })
        var list = ArrayList<Result.ItemList>()
        val layoutManager = LinearLayoutManager(this.activity)
        recyclerview.layoutManager = layoutManager
        adapter = MyMultiTypeAdapter(list, activity!!)
        recyclerview.adapter = adapter

    }

    override fun initPresenter() {
        DiscoverPresenter(this)

    }

    override fun getLayoutId() = R.layout.discoverrsy_fragment

    override fun onDestroyView() {
        presenter = null
        recyclerview!!.adapter = null
        adapter = null
        recyclerview!!.addOnScrollListener(null)
        clearFindViewByIdCache()
        Log.e("Fragment", "DiscoveryFragment onDestroy()")
        super.onDestroyView()
    }
}
