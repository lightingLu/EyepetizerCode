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
import com.siqiyan.lightlu.eyepetizercode.home.FeedContract
import com.siqiyan.lightlu.eyepetizercode.home.adapter.MyMultiTypeAdapter
import com.siqiyan.lightlu.eyepetizercode.home.presenter.FeedPresenter
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import com.siqiyan.lightlu.eyepetizercode.utils.UriUtils
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.feed_fragment.*

/**
 * 创建日期：2018/7/9 on 23:56
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class FeedFragment : BaseFragment() ,FeedContract.FeedView{


        private var presenter: FeedContract.FeedPresenter? = null
        var adapter: MyMultiTypeAdapter? = null
        var isRefresh: Boolean = false
        var data: Long = System.currentTimeMillis()
        override fun getLayoutId(): Int = R.layout.feed_fragment

        override fun initView() {
                Log.e("Fragment", "FeedFragment initView()")
                swipeRefreshLayout.isEnableAutoLoadmore = true
                swipeRefreshLayout.refreshHeader = ClassicsHeader(activity)
                swipeRefreshLayout.refreshFooter = ClassicsFooter(activity)
                swipeRefreshLayout.setOnRefreshListener {
                        isRefresh = true
                        data = System.currentTimeMillis()
                        initDate()
                }

                swipeRefreshLayout.setOnLoadmoreListener {
                        isRefresh = false
                        initDate()
                }

                recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                        override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                                super.onScrollStateChanged(recyclerView, newState)
                                //0 表示停止滑动的状态 SCROLL_STATE_IDLE
                                //1表示正在滚动，用户手指在屏幕上 SCROLL_STATE_TOUCH_SCROLL
                                //2表示正在滑动。用户手指已经离开屏幕 SCROLL_STATE_FLING
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
                val list = ArrayList<Result.ItemList>()
                val layoutManager = LinearLayoutManager(this.activity!!)
                recyclerview.layoutManager = layoutManager
                adapter = MyMultiTypeAdapter(list, activity!!)
                recyclerview.adapter = adapter
                isRefresh = false
        }

        override fun initPresenter() {
                FeedPresenter(this)
        }

        override fun initDate() {
                presenter!!.feed(data)
        }

        override fun setPresenter(presenter: FeedContract.FeedPresenter) {
                this.presenter = presenter
        }

        override fun onFeedSucc(t: Result) {
                if (!TextUtils.isEmpty(t.nextPageUrl)) {
                        data = UriUtils().parseFeedUri(t.nextPageUrl.toString()).data
                }
                swipeRefreshLayout.finishLoadmore()
                swipeRefreshLayout.finishRefresh()
                swipeRefreshLayout.isLoadmoreFinished = TextUtils.isEmpty(t.nextPageUrl)
                if (t.itemList!!.isEmpty()) return
                val start = adapter!!.itemCount
                if (isRefresh) {
                        adapter!!.clearAll()
                        adapter!!.notifyItemRangeRemoved(0, start)
                        adapter!!.addAllData(t.itemList as java.util.ArrayList<Result.ItemList>?)
                        adapter!!.notifyItemRangeInserted(0, t.itemList!!.size)
                } else {
                        adapter!!.addAllData(t.itemList as java.util.ArrayList<Result.ItemList>)
                        adapter!!.notifyItemRangeInserted(start, t.itemList!!.size)
                }
        }

        override fun onFeedFail(error: Throwable?) {
                swipeRefreshLayout.isLoadmoreFinished = false
                swipeRefreshLayout.finishLoadmore()
                swipeRefreshLayout.finishRefresh()
        }

        override fun onDestroyView() {
                Log.e("Fragment", "FeedFragment onDestroy()")
                presenter = null
                recyclerview!!.adapter = null
                adapter = null
                recyclerview!!.addOnScrollListener(null)
                clearFindViewByIdCache()
                super.onDestroyView()
        }
    }
