package com.siqiyan.lightlu.eyepetizercode.home.view

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import com.bumptech.glide.Glide
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.siqiyan.lightlu.eyepetizercode.R
import com.siqiyan.lightlu.eyepetizercode.base.BaseFragment
import com.siqiyan.lightlu.eyepetizercode.home.contract.RankListDetailContract
import com.siqiyan.lightlu.eyepetizercode.home.adapter.MyMultiTypeAdapter
import com.siqiyan.lightlu.eyepetizercode.home.presenter.RankListDetailPresenter
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import com.siqiyan.lightlu.eyepetizercode.utils.UriUtils
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.ranklist_fragment.*

@SuppressLint("ValidFragment")
/**
 * 创建日期：2018/7/23 on 00:20
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class RankListFragment (path: String, map: HashMap<String, String>?) : BaseFragment(), RankListDetailContract.RankListView {
    private var presenter: RankListDetailContract.RankListPresenter? = null
    var adapter: MyMultiTypeAdapter? = null
    private var isRefresh: Boolean = false
    private var path: String? = path
    private var map: HashMap<String, String>? = map

    override fun onRankListSucc(t: Result) {
        swipeRefreshLayout.finishLoadmore()
        swipeRefreshLayout.finishRefresh()
        swipeRefreshLayout.isLoadmoreFinished = TextUtils.isEmpty(t.nextPageUrl)
        if (!TextUtils.isEmpty(t.nextPageUrl)) {
            var categoriesTagListParse = UriUtils().parseCategoriesTagListUri(t.nextPageUrl.toString())
            map!!.clear()
            map = categoriesTagListParse.map
        }
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

    override fun onRankListFail(error: Throwable?) {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.isLoadmoreFinished = false
            swipeRefreshLayout.finishLoadmore()
        }
    }

    override fun setPresenter(prsenter: RankListDetailContract.RankListPresenter) {
        this.presenter=prsenter
    }

    override fun initDate() {
        presenter!!.rankListVideo(path!!,map!!)
    }

    override fun initPresenter() {
        RankListDetailPresenter(this)
    }

    override fun initView() {
        Log.e("Fragment", "CategoryFragment initView()")
        swipeRefreshLayout.isEnableRefresh = false
        swipeRefreshLayout.refreshHeader = ClassicsHeader(activity) as RefreshHeader?
        swipeRefreshLayout.refreshFooter = ClassicsFooter(activity)

        swipeRefreshLayout.isEnableRefresh = false
        swipeRefreshLayout.setOnLoadmoreListener {
            isRefresh = false
            presenter!!.rankListVideo(path.toString(), this!!.map!!)
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

    override fun getLayoutId(): Int = R.layout.ranklist_fragment

    override fun onDestroyView() {
        recyclerview!!.adapter = null
        adapter = null
        presenter = null
        recyclerview!!.addOnScrollListener(null)
        clearFindViewByIdCache()
        super.onDestroyView()
    }

}