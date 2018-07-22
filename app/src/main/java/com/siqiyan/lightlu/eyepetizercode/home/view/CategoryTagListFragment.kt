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
import com.siqiyan.lightlu.eyepetizercode.home.CategoryTabListContract
import com.siqiyan.lightlu.eyepetizercode.home.adapter.MyMultiTypeAdapter
import com.siqiyan.lightlu.eyepetizercode.home.presenter.CategoriesTagListPresenter
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import com.siqiyan.lightlu.eyepetizercode.utils.UriUtils
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.categor_tag_fragment.*

@SuppressLint("ValidFragment")
/**
 * 创建日期：2018/7/22 on 19:28
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class CategoryTagListFragment(id: String, path: String):BaseFragment() ,CategoryTabListContract.CategoriesTagListView{
    private var presenter: CategoryTabListContract.CategoriesTagListPresenter? = null
    var adapter: MyMultiTypeAdapter? = null
    private var isRefresh: Boolean = false
    private var category_id = id
    private var path: String? = path
    private var map: HashMap<String, String>? = HashMap()

    override fun onCategoriesTagSucc(result: Result) {

        swipeRefreshLayout.finishLoadmore()
        swipeRefreshLayout.finishRefresh()
        swipeRefreshLayout.isLoadmoreFinished = TextUtils.isEmpty(result.nextPageUrl)

        if (!TextUtils.isEmpty(result.nextPageUrl)) {
            var categoriesTagListParse = UriUtils().parseCategoriesTagListUri(result.nextPageUrl.toString())
            map!!.clear()
            map = categoriesTagListParse.map
            if (result.itemList!!.isEmpty()) {
                return
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

    }

    override fun onCategoriesTagFail(error: Throwable?) {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.isLoadmoreFinished = false
            swipeRefreshLayout.finishLoadmore()
        }
    }

    override fun setPresenter(prsenter: CategoryTabListContract.CategoriesTagListPresenter) {

    this.presenter=prsenter
    }

    override fun initDate() {
        map!!.put("id", category_id)
        presenter!!.categoriesTagList(path!!,map!!)
    }

    override fun initPresenter() {
        CategoriesTagListPresenter(this)
    }

    override fun initView() {
        Log.e("Fragment", "CategoryFragment initView()")
        swipeRefreshLayout.isEnableRefresh = false
        swipeRefreshLayout.refreshHeader = ClassicsHeader(activity) as RefreshHeader?
        swipeRefreshLayout.refreshFooter = ClassicsFooter(activity)

        swipeRefreshLayout.isEnableRefresh = false
        swipeRefreshLayout.setOnLoadmoreListener {
            isRefresh = false
            presenter!!.categoriesTagList(path.toString(), this!!.map!!)
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
        val layoutManager = LinearLayoutManager(activity!!.applicationContext)
        recyclerview.layoutManager = layoutManager
        adapter = MyMultiTypeAdapter(list, activity!!.applicationContext)
        recyclerview.adapter = adapter
        isRefresh = false
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


    override fun getLayoutId(): Int = R.layout.categor_tag_fragment
}