package com.siqiyan.lightlu.eyepetizercode.search

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import com.bumptech.glide.Glide
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.siqiyan.lightlu.eyepetizercode.R
import com.siqiyan.lightlu.eyepetizercode.base.BaseFragment
import com.siqiyan.lightlu.eyepetizercode.home.adapter.MyMultiTypeAdapter
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import com.siqiyan.lightlu.eyepetizercode.utils.UriUtils
import kotlinx.android.synthetic.main.search_fragment.*

/**
 * 创建日期：2018/7/8 on 22:13
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class SearchFragment :BaseFragment(),SearchContract.SearchView {
    private lateinit var presenter: SearchContract.SearchPresenter
    var adapter: MyMultiTypeAdapter? = null
    var isRefresh: Boolean = false
    var start: Int = 0
    var num: Int = 10
    var search: String? = null

    companion object {
        fun newInstance(index:String):SearchFragment{
            val fragment = SearchFragment()
            val bundle = Bundle()
            bundle.putString("search", index)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onSearchSucc(result: Result) {

        if (!TextUtils.isEmpty(result.nextPageUrl)) {
            var parse: UriUtils.SearchParse = UriUtils().parseSearchUri(result.nextPageUrl.toString())
            start = parse.start
            num = parse.num
            search = parse.query
        }
        swipeRefreshLayout.finishLoadmore()
        swipeRefreshLayout.finishRefresh()
        swipeRefreshLayout.isLoadmoreFinished = TextUtils.isEmpty(result.nextPageUrl)
        if (result.itemList!!.isEmpty()) {
            ll_empty.visibility = View.VISIBLE
            swipeRefreshLayout.visibility = View.GONE
            return
        } else {
            ll_empty.visibility = View.GONE
            swipeRefreshLayout.visibility = View.VISIBLE
        }
        val start = adapter!!.itemCount
        if (isRefresh) {
            adapter!!.clearAll()
            adapter!!.notifyItemRangeRemoved(0, start)
            adapter!!.addAllData(result.itemList as java.util.ArrayList<Result.ItemList>?)
            adapter!!.notifyItemRangeInserted(0, result.itemList!!.size)
        } else {
            adapter!!.addAllData(result.itemList as java.util.ArrayList<Result.ItemList>)
            adapter!!.notifyItemRangeInserted(start, result.itemList!!.size)
        }
    }

    override fun setPresenter(prsenter: SearchContract.SearchPresenter) {
        this.presenter=prsenter
    }

    override fun initDate() {
        if (!TextUtils.isEmpty(search)) {
            presenter.search(start, num, this!!.search.toString())
        }
    }

    override fun initView() {
        search = arguments!!.getString("search")
        swipeRefreshLayout.isEnableAutoLoadmore = true
        swipeRefreshLayout.refreshHeader = ClassicsHeader(activity) as RefreshHeader?
        swipeRefreshLayout.refreshFooter = ClassicsFooter(activity)
        swipeRefreshLayout.isEnableRefresh = false

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
        ll_empty.visibility = View.GONE
        swipeRefreshLayout.visibility = View.VISIBLE

    }

    override fun initEvent() {
    }

    override fun initPresenter() {
        SearchPresenter(this)
    }

    override fun getLayoutId(): Int = R.layout.search_fragment
}