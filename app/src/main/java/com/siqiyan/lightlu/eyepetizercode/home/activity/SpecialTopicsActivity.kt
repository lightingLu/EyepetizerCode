package com.siqiyan.lightlu.eyepetizercode.home.activity

import android.net.Uri
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.siqiyan.lightlu.eyepetizercode.R
import com.siqiyan.lightlu.eyepetizercode.base.BaseActivity
import com.siqiyan.lightlu.eyepetizercode.home.adapter.MyMultiTypeAdapter
import com.siqiyan.lightlu.eyepetizercode.home.contract.SpecialTopicsContract
import com.siqiyan.lightlu.eyepetizercode.home.presenter.SpecialTopicsPresenter
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import kotlinx.android.synthetic.main.activity_special_topics.*

/**
 * 近期专题界面
 */

class SpecialTopicsActivity : BaseActivity(),SpecialTopicsContract.SpecialTopicsView {

    private var presenter: SpecialTopicsContract.SpecialTopicsPresenter? = null
    private var isRefresh: Boolean = true
    var adapter: MyMultiTypeAdapter? = null
    private var start = 0
    private var num = 10

    override fun initPresenter() {
        SpecialTopicsPresenter(this)
    }


    override fun initDate() {
        presenter!!.specialTopics(start,num)
    }

    override fun initView() {
        val title = intent.getStringExtra("title")
        tv_bar_title.text=title
        iv_home.setOnClickListener({
            onBackPressed()
        })

        swipeRefreshLayout.refreshHeader=ClassicsHeader(this)
        swipeRefreshLayout.refreshFooter=ClassicsFooter(this)
        swipeRefreshLayout.setOnRefreshListener {
            isRefresh=true
            num=10
            start=0
            presenter!!.specialTopics(start,num)
        }

        swipeRefreshLayout.setOnLoadmoreListener {
            isRefresh=false
            presenter!!.specialTopics(start,num)
        }
        var list =ArrayList<Result.ItemList>()
        var layoutManager =LinearLayoutManager(this)
        recyclerview.layoutManager=layoutManager
        adapter = MyMultiTypeAdapter(list,this)
        recyclerview.adapter=adapter
    }

    override fun onBackPressed() = finish()


    override fun getLayoutId(): Int = R.layout.activity_special_topics

    override fun onSpecialTopicsSucc(result: Result) {
        swipeRefreshLayout.finishLoadmore()
        swipeRefreshLayout.finishRefresh()
        swipeRefreshLayout.isLoadmoreFinished=TextUtils.isEmpty(result.nextPageUrl)
        if (!TextUtils.isEmpty(result.nextPageUrl)){
            var uri = Uri.parse(result.nextPageUrl.toString())
            start = uri.getQueryParameter("start").toInt()
            num = uri.getQueryParameter("num").toInt()
        }

        if (result.itemList!!.isEmpty()) return
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

    override fun onSpecialTopicsFail(throwable: Throwable?) {
        if (swipeRefreshLayout!=null){
            swipeRefreshLayout.isLoadmoreFinished = false
            swipeRefreshLayout.finishLoadmore()
        }
    }

    override fun setPresenter(prsenter: SpecialTopicsContract.SpecialTopicsPresenter) {
        this.presenter=prsenter
    }
}
