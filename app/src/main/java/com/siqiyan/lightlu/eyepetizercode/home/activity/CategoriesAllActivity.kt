package com.siqiyan.lightlu.eyepetizercode.home.activity

import android.support.v7.widget.GridLayoutManager
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.siqiyan.lightlu.eyepetizercode.R
import com.siqiyan.lightlu.eyepetizercode.base.BaseActivity
import com.siqiyan.lightlu.eyepetizercode.home.CategoriesAllContract
import com.siqiyan.lightlu.eyepetizercode.home.adapter.CategoriesAllAdapter
import com.siqiyan.lightlu.eyepetizercode.net.entity.Result
import kotlinx.android.synthetic.main.activity_categories_all.*


/**
 * 全部分类界面
 */

class CategoriesAllActivity : BaseActivity() , CategoriesAllContract.CategoriesAllView{
    override fun initDate() {
        presenter!!.categoriesAll()

    }

    override fun initView() {
        iv_home.setOnClickListener {
            onBackPressed()
        }
        swipeRefreshLayout.refreshHeader = ClassicsHeader(this@CategoriesAllActivity)
        swipeRefreshLayout.setOnRefreshListener { initDate() }
        val layoutManager = GridLayoutManager(this@CategoriesAllActivity, 2)
        recyclerview.layoutManager = layoutManager
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int = getSpanSize(position, datas)
        }
        mAdapter = CategoriesAllAdapter(ArrayList(), this@CategoriesAllActivity)
        recyclerview.adapter = mAdapter
    }

    fun getSpanSize(position: Int, datas: ArrayList<Result.ItemList>?): Int {
        var data: Result.ItemList = datas!![position]
        var item: Map<String, Object> = data.data as Map<String, Object>
        var dataType = item["dataType"].toString()
        return if ("RectangleCard" == dataType) {
            2
        } else {
            1
        }

    }

    override fun getLayoutId(): Int = R.layout.activity_categories_all

    internal var presenter: CategoriesAllContract.CategoriesAllPresenter? = null
    private var mAdapter: CategoriesAllAdapter? = null
    private var datas: ArrayList<Result.ItemList>? = null

    override fun onCategoriesAllSucc(result: Result) {
        datas = result.itemList as ArrayList<Result.ItemList>?
        val start = mAdapter!!.itemCount
        mAdapter!!.clearAll()
        mAdapter!!.notifyItemRangeRemoved(0, start)
        mAdapter!!.addAll(result.itemList as java.util.ArrayList<Result.ItemList>?)
        mAdapter!!.notifyItemRangeInserted(0, result.itemList!!.size)
        swipeRefreshLayout.finishRefresh()

    }

    override fun onCategoriesAllFail(error: Throwable?) {
        swipeRefreshLayout.isLoadmoreFinished = false
        swipeRefreshLayout.finishRefresh()
    }

    override fun setPresenter(prsenter: CategoriesAllContract.CategoriesAllPresenter) {
        this.presenter =prsenter
    }


    override fun onBackPressed() = finish()

}
