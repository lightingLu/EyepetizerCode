package com.siqiyan.lightlu.eyepetizercode.home.view

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import com.bumptech.glide.Glide
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.siqiyan.lightlu.eyepetizercode.R
import com.siqiyan.lightlu.eyepetizercode.base.BaseFragment
import com.siqiyan.lightlu.eyepetizercode.event.CurrentTagEvent
import com.siqiyan.lightlu.eyepetizercode.event.RxBus
import com.siqiyan.lightlu.eyepetizercode.home.contract.CategoryContract
import com.siqiyan.lightlu.eyepetizercode.home.adapter.MyMultiTypeAdapter
import com.siqiyan.lightlu.eyepetizercode.home.presenter.CategoryPresenter
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import com.siqiyan.lightlu.eyepetizercode.utils.UriUtils
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.category_fragment.*

@SuppressLint("ValidFragment")
/**
 * 创建日期：2018/7/9 on 23:56
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class CategoryFragment(id:String): BaseFragment(), CategoryContract.CategoryView {

        private var presenter: CategoryContract.CategoryPresenter? = null
        var adapter: MyMultiTypeAdapter? = null
        var isRefresh: Boolean = false
        var category_id = id
        var start_num: Int = 0
        var num: Int = 10
        override fun getLayoutId(): Int = R.layout.category_fragment

        override fun initEvent() {
                RxBus.default!!.toObservable(CurrentTagEvent::class.java)
                        .subscribe { currentTagEvent ->
                                if (currentTagEvent.tag != null && currentTagEvent.tag.equals(category_id)) {
                                        if (currentTagEvent.isForceRefresh) {
                                                isRefresh = true
                                                swipeRefreshLayout.autoRefresh(0)
                                        } else {
                                                if (adapter != null && adapter!!.itemCount == 0) {
                                                        start_num = 0
                                                        num = 10
                                                        presenter!!.category(category_id.toInt(), start_num, num)
                                                }
                                        }
                                }
                        }
        }

        override fun initView() {
                Log.e("Fragment", "CategoryFragment initView()")
                swipeRefreshLayout.isEnableAutoLoadmore = true
                swipeRefreshLayout.refreshHeader = ClassicsHeader(activity)
                swipeRefreshLayout.refreshFooter = ClassicsFooter(activity)
                swipeRefreshLayout.setOnRefreshListener {
                        isRefresh = true
                        start_num = 0
                        num = 10
                        presenter!!.category(category_id.toInt(), start_num, num)
                }
                swipeRefreshLayout.isEnableRefresh = true

                swipeRefreshLayout.setOnLoadmoreListener {
                        isRefresh = false
                        presenter!!.category(category_id.toInt(), start_num, num)
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
                recyclerview.layoutManager = layoutManager as RecyclerView.LayoutManager?
                adapter = MyMultiTypeAdapter(list, activity!!)
                recyclerview.adapter = adapter
                isRefresh = false
        }

        override fun initPresenter() {
                CategoryPresenter(this)
        }

        override fun initDate() = Unit

        override fun setPresenter(presenter: CategoryContract.CategoryPresenter) {
                this.presenter = presenter
        }

        override fun onCategorySucc(t: Result) {
                if (swipeRefreshLayout != null) {
                        swipeRefreshLayout!!.finishLoadmore()
                        swipeRefreshLayout!!.finishRefresh()
                        swipeRefreshLayout!!.isLoadmoreFinished = TextUtils.isEmpty(t.nextPageUrl)
                }
                if (!TextUtils.isEmpty(t.nextPageUrl)) {
                        start_num = UriUtils().parseCategoryUri(t.nextPageUrl.toString()).start
                        num = UriUtils().parseCategoryUri(t.nextPageUrl.toString()).num
                }
                if (t.itemList!!.isEmpty()) return
                val start = adapter!!.itemCount
                if (isRefresh) {
                        adapter!!.clearAll()
                        adapter!!.notifyItemRangeRemoved(0, start)
                        adapter!!.addAllData(t.itemList as java.util.ArrayList<Result.ItemList>?)
                        adapter!!.notifyItemRangeInserted(0, t.itemList!!.size)
                        start_num = 0
                        num = 10
                } else {
                        adapter!!.addAllData(t.itemList as java.util.ArrayList<Result.ItemList>)
                        adapter!!.notifyItemRangeInserted(start, t.itemList!!.size)
                }
        }

        override fun onCategoryFail(error: Throwable?) {
                if (swipeRefreshLayout != null) {
                        swipeRefreshLayout!!.isLoadmoreFinished = false
                        swipeRefreshLayout!!.finishLoadmore()
                        swipeRefreshLayout!!.finishRefresh()
                }
        }

        override fun onDestroyView() {
                recyclerview!!.adapter = null
                adapter = null
                presenter = null
                recyclerview!!.addOnScrollListener(null)
                clearFindViewByIdCache()
                Log.e("Fragment", "CategoryFragment onDestroy() " + category_id)
                super.onDestroyView()
        }
    }
