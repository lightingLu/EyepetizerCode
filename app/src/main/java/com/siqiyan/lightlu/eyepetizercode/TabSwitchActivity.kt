package com.siqiyan.lightlu.eyepetizercode

import com.siqiyan.lightlu.eyepetizercode.base.BaseActivity
import com.siqiyan.lightlu.eyepetizercode.home.CategoriesContract
import com.siqiyan.lightlu.eyepetizercode.home.adapter.TabSwitchAdapter
import com.siqiyan.lightlu.eyepetizercode.home.presenter.CategoriesPresenter
import com.siqiyan.lightlu.eyepetizercode.net.entity.Categories
import java.util.ArrayList

/**
 * 创建日期：2018/7/16 on 00:22
 * @author ludaguang
 * @version 1.0
 * 类说明：
 */
class TabSwitchActivity :BaseActivity(),CategoriesContract.CategoriesView {
    override fun onCategoriesSucc(result: List<Categories>) {
        adapter?.()
        adapter?.addAll(result as ArrayList<Categories>)
        adapter?.notifyDataSetChanged()
    }

    override fun onCategoriesFail(error: Throwable?) {
    }

    override fun setPresenter(prsenter: CategoriesContract.CategoriesPresenter) {
        this.presenter=prsenter
    }

    private var presenter: CategoriesContract.CategoriesPresenter? = null
    var adapter: TabSwitchAdapter? = null
    override fun initDate() {
        presenter!!.categories()
    }

    override fun initView() {
    }

    override fun getLayoutId(): Int =R.layout.tab_switch_activity


    override fun initPresenter() {
        CategoriesPresenter(this)
    }

}